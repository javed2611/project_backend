package com.jk.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jk.Repo.OrderRepo;
import com.jk.constants.OrderStatus;
import com.jk.entity.Order;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepo orderRepo;

	private LocalDate today = LocalDate.now();

	@Override
	public Order updateOrder(Order order) {
		return orderRepo.save(order);
	}

	@Override
	public void deleteInvoiceFromLocalStorage(String orderTrackingNumber) {
		try {
			Files.deleteIfExists(Paths.get(getInvoiceName(orderTrackingNumber)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Order> getOrdersEligibleForDelivery() {
		return orderRepo.findByDeilveryDate(today);
	}

	@Override
	public List<Order> getUnconfimedOrders() {
		return orderRepo.findByOrderStatusNot(OrderStatus.DELIVERED.toString());
	}

	@Override
	public String getInvoiceName(String orderTrackingNumber) {
		return "invoices/invoice" + "-" + orderTrackingNumber + ".pdf";
	}

}
