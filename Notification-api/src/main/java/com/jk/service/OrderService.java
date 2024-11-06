package com.jk.service;

import java.util.List;

import com.jk.entity.Order;

public interface OrderService {
	public Order updateOrder(Order order);

	public void deleteInvoiceFromLocalStorage(String orderTrackingNumber);

	public List<Order> getOrdersEligibleForDelivery();

	public List<Order> getUnconfimedOrders();

	public String getInvoiceName(String orderTrackingNumber);

}
