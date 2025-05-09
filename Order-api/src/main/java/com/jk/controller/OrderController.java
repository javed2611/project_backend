package com.jk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jk.dto.OrderResponse;
import com.jk.dto.PaymentCallbackDTO;
import com.jk.dto.PurchaseDTO;
import com.jk.entity.Order;
import com.jk.service.OrderService;
import com.razorpay.RazorpayException;

@CrossOrigin("http://localhost:4200/")
@RestController
@RequestMapping("/api/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@PostMapping(value = "/create-order", produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" })
	public ResponseEntity<OrderResponse> createOrder(@RequestBody PurchaseDTO purchaseDTO) throws RazorpayException {
		OrderResponse orderResponse = orderService.createOrder(purchaseDTO);
		return new ResponseEntity<>(orderResponse, HttpStatus.CREATED);
	}

	@PostMapping("/payment-verification")
	public boolean verifyPayment(@RequestBody PaymentCallbackDTO paymentCallbackDTO, Model model) {
		boolean isPaymentConfirmed = orderService.verifyPaymentAndUpdateOrderStatus(paymentCallbackDTO);
		return isPaymentConfirmed;
	}
	
	 @GetMapping("/getOrderDetails/{email}")
	    public List<Order> getOrdersByEmail(@PathVariable String email){
	        return orderService.getOrderDetails(email);
	    }

}
