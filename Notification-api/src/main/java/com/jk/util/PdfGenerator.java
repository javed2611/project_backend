package com.jk.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jk.entity.Order;
import com.jk.entity.OrderItem;

@Component
public class PdfGenerator {

    @Value("${pdfDir}")
    private String pdfDir;

    @Value("${reportFileName}")
    private String reportFileName;

    @Value("${localDateFormat}")
    private String localDateFormat;

    @Value("${logoImgPath}")
    private String logoImgPath;

    @Value("${logoImgScale}")
    private Float[] logoImgScale;

    @Value("${currencySymbol:}")
    private String currencySymbol;

    @Value("${table_noOfColumns}")
    private int noOfColumns;

    @Value("${table.columnNames}")
    private List<String> columnNames;

    private static Font COURIER = new Font(Font.FontFamily.COURIER, 20, Font.BOLD);
    private static Font COURIER_SMALL = new Font(Font.FontFamily.COURIER, 16, Font.BOLD);
    private static Font COURIER_SMALL_FOOTER = new Font(Font.FontFamily.COURIER, 12, Font.BOLD);

    private static final Path root = Paths.get("invoices");

    @Autowired
    private S3Util s3util;

    public String pdfGenerator(Order order) {
        try {
            if (!Files.exists(root))
                Files.createDirectories(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Document document = new Document();

        try {
            if (Objects.nonNull(order)) {
                String fileName = getPdfNameWithDate(order.getOrderTrackingNum());
                FileOutputStream os = new FileOutputStream(fileName);
                PdfWriter.getInstance(document, os);
                document.open();
                addLogo(document);
                addHeader(document);
                addCustomerDetails(document, order);
                addOrderInfo(document, order);
                createTable(document, noOfColumns, order);
                addFooter(document);
                document.close();
                // move file to s3 bucket
                String invoiceUrl = s3util.saveFileInBucket(new File(fileName));
                return invoiceUrl;
            }
        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void addLogo(Document document) {
        try {
            Image img = Image.getInstance(root + "/" + logoImgPath);
            img.scalePercent(logoImgScale[0], logoImgScale[1]);
            img.setAlignment(Element.ALIGN_RIGHT);
            document.add(img);
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }

    private void addHeader(Document document) throws DocumentException {
        Paragraph header = new Paragraph();
        header.add(new Paragraph("JK Infotech", COURIER));
        header.add(new Paragraph("Mumbra", COURIER_SMALL));
        header.add(new Paragraph("Contact: 7021784840", COURIER_SMALL));
        header.add(new Paragraph("Email: javed.qsp@gmail.com", COURIER_SMALL));
        header.add(new Paragraph("Invoice Date: " + LocalDate.now(), COURIER_SMALL));
        header.setAlignment(Element.ALIGN_LEFT);
        document.add(header);
    }

    private void addCustomerDetails(Document document, Order order) throws DocumentException {
        Paragraph customerDetails = new Paragraph();
        customerDetails.add(new Paragraph("Bill To:", COURIER));
        customerDetails.add(new Paragraph(order.getCustomer().getName(), COURIER_SMALL));
        customerDetails.add(new Paragraph(order.getAddress().toString(), COURIER_SMALL));
        customerDetails.add(new Paragraph(order.getCustomer().getPhno(), COURIER_SMALL));
        customerDetails.add(new Paragraph(order.getCustomer().getEmail(), COURIER_SMALL));
        document.add(customerDetails);
    }

    private void addOrderInfo(Document document, Order order) throws DocumentException {
        Paragraph p1 = new Paragraph();
        p1.add(new Paragraph("Order Number : " + order.getOrderTrackingNum(), COURIER));
        p1.add(new Paragraph("Order Date : " + order.getCreateDate(), COURIER_SMALL));
        document.add(p1);
    }

    private void createTable(Document document, int noOfColumns, Order order) throws DocumentException {
        PdfPTable table = new PdfPTable(noOfColumns);
        table.setWidthPercentage(100);
        
        // Adding column headers
        for (String columnName : columnNames) {
            PdfPCell cell = new PdfPCell(new Phrase(columnName));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            table.addCell(cell);
        }

        // Adding order items
        for (OrderItem item : order.getItems()) {
            table.addCell(item.getProdName());
            table.addCell(String.valueOf(item.getQuantity()));
            table.addCell(currencySymbol + item.getUnitPrice());
            BigDecimal netPrice = BigDecimal.valueOf(item.getUnitPrice()).multiply(BigDecimal.valueOf(item.getQuantity())).setScale(2, RoundingMode.HALF_UP);
            table.addCell(currencySymbol + netPrice);
        }

        // Total Price Row
        table.addCell("");
        table.addCell("");
        table.addCell("Total:");
        BigDecimal totalPrice = BigDecimal.valueOf(order.getTotalPrice()).setScale(2, RoundingMode.HALF_UP);
        table.addCell(currencySymbol + totalPrice);
        
        document.add(table);
    }

    private void addFooter(Document document) throws DocumentException {
        Paragraph footer = new Paragraph();
        footer.add(new Paragraph("Thank you for your purchase!", COURIER_SMALL_FOOTER));
        footer.add(new Paragraph("Return Policy: You can return any item within 30 days.", COURIER_SMALL_FOOTER));
        footer.setAlignment(Element.ALIGN_CENTER);
        document.add(footer);
    }

    private String getPdfNameWithDate(String orderTrackingNumber) {
        return pdfDir + reportFileName + "-" + orderTrackingNumber + ".pdf";
    }
}
