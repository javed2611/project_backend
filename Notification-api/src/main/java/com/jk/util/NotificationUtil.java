package com.jk.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jk.dto.EmailDetails;
import com.jk.entity.Order;
import com.jk.service.EmailService;
import com.jk.service.OrderService;

@Component
public class NotificationUtil {

	@Autowired
	private PdfGenerator pdfGenerator;

	@Autowired
	private OrderService orderService;

	@Autowired
	private EmailService emailService;

	public void sendDeliveryNotification(Order order) {
		String invoiceUrl = pdfGenerator.pdfGenerator(order);
		order.setInvoice(invoiceUrl);
		orderService.updateOrder(order);

		EmailDetails emailDetails = new EmailDetails(order.getEmail(),
				"Hi Your order has been delivered successfully, Please find the invoice for your reference",
				"Your JKInfotech order #" + order.getOrderTrackingNum(),
				"invoices" + invoiceUrl.substring(invoiceUrl.lastIndexOf("/"), invoiceUrl.length()));

		emailService.sendMailWithAttachment(emailDetails);
//		watiService.sendDeliveryNotification(order.getCustomer().getPhno(), order.getCustomer().getName(),
//				order.getOrderTrackingNum());
		orderService.deleteInvoiceFromLocalStorage(order.getOrderTrackingNum());
	}
}
