package com.jk.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jk.entity.Order;
import com.jk.service.WatiService;

@Component
public class PaymentReminder {

	@Autowired
	private WatiService watiService;

	public void sendPaymentReminder(Order order) {
//		watiService.sendPaymentReminder(order.getCustomer().getPhno(), order.getCustomer().getName(), order.getOrderTrackingNum());
	}
}
