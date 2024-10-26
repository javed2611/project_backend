package com.jk.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@Service
public class RazorPayService {

	@Autowired
	private RazorpayClient client;
	public String createQrCodeOrder(int amount) throws RazorpayException {
		JSONObject orderRequest = new JSONObject();
		orderRequest.put("amount", amount * 100);
		orderRequest.put("currency", "INR");
		orderRequest.put("payment_capture", 1);
		
		Order order = client.Orders.create(orderRequest);
		return order.get("id");
	}
}
