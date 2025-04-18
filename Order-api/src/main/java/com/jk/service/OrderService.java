package com.jk.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.jk.dto.OrderItemDTO;
import com.jk.dto.OrderResponse;
import com.jk.dto.PaymentCallbackDTO;
import com.jk.dto.PurchaseDTO;
import com.jk.entity.Address;
import com.jk.entity.Customer;
import com.jk.entity.Order;
import com.jk.entity.OrderItem;
import com.jk.repositry.AddressRepository;
import com.jk.repositry.CustomerRepositry;
import com.jk.repositry.OrderItemRepositry;
import com.jk.repositry.OrderRepository;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private AddressRepository addressRepo;

	@Autowired
	private CustomerRepositry customerRepositry;

	@Autowired
	private OrderItemRepositry orderItemRepositry;

	private RazorpayClient client;

	@Value("${razorpay.key.id}")
	private String keyId;

	@Value("${razorpay.key.secret}")
	private String keySecret;

	public OrderResponse createOrder(PurchaseDTO purchaseDTO) throws RazorpayException {
		// Order Creation for razorpay
		JSONObject orderRequest = new JSONObject();
		orderRequest.put("amount", (int)(purchaseDTO.getOrder().getTotalPrice() * 100));
		orderRequest.put("currency", "INR");
		orderRequest.put("receipt", purchaseDTO.getCustomer().getEmail());

		// initiate the razorpay client
		this.client = new RazorpayClient(keyId, keySecret);
		com.razorpay.Order razorPayOrder = client.Orders.create(orderRequest);
		Customer customer = customerRepositry.findByEmail(purchaseDTO.getCustomer().getEmail());
		if (customer == null) {
//			ObjectMapper obj = new ObjectMapper();
//			customer = obj.convertValue(purchaseDTO.getCustomer(), Customer.class);
//			BeanUtils.copyProperties(purchaseDTO.getCustomer(), customer);
//			customerRepositry.save(customer);
			 customer = new Customer();
	            customer.setName(purchaseDTO.getCustomer().getName());
	            customer.setEmail(purchaseDTO.getCustomer().getEmail());
	            customer.setPhno(purchaseDTO.getCustomer().getPhno()); // Assuming there's a phone number field
	            customerRepositry.save(customer);
		}
		Address address = new Address();
		address.setCustomer(customer);
		address.setStreet(purchaseDTO.getAddress().getStreet());
		address.setCity(purchaseDTO.getAddress().getCity());
		address.setState(purchaseDTO.getAddress().getState());
		address.setZipCode(purchaseDTO.getAddress().getZipCode());
		addressRepo.save(address);

		Order order = new Order();
		String orderTrackingNumber = generateOrderTrackingId();
		order.setOrderTrackingNum(orderTrackingNumber);
		order.setRazorPayOrderId(razorPayOrder.get("id"));
		order.setOrderStatus(razorPayOrder.get("status"));
		order.setTotalPrice(purchaseDTO.getOrder().getTotalPrice());
		order.setQuantity(purchaseDTO.getOrder().getQuantity());
		order.setEmail(customer.getEmail());
		order.setCustomer(customer);
		order.setAddress(address);
		orderRepository.save(order);

		List<OrderItemDTO> orderItemDTOs = purchaseDTO.getOrderItems();
		for (OrderItemDTO orderItemDTO : orderItemDTOs) {
			OrderItem orderItem = new OrderItem();
			BeanUtils.copyProperties(orderItemDTO, orderItem);
			orderItem.setOrder(order);
			orderItemRepositry.save(orderItem);
		}
		OrderResponse orderResponse = new OrderResponse();
		orderResponse.setOrderTrackingNumber(orderTrackingNumber);
		orderResponse.setOrderStatus(razorPayOrder.get("status"));
		orderResponse.setRazorpayOrderId(razorPayOrder.get("id"));
		return orderResponse;
	}

	public boolean verifyPaymentAndUpdateOrderStatus(PaymentCallbackDTO paymentCallbackDTO) {
		Order order = orderRepository.findByRazorPayOrderId(paymentCallbackDTO.getRazorpayOrderId());
		System.out.println("RazorPayOrderId :" + order.getRazorPayOrderId());
		boolean isPaymentConfirmed = false;
		if (order != null) {
			try {
				// Verify the payment signature
				boolean isValid = verifySignature(paymentCallbackDTO);

				if (isValid) {
					// Update order status and save the order
					order.setOrderStatus("Confirmed");
					order.setDeilveryDate(deliveryDate());
					order.setRazorPayPaymentId(paymentCallbackDTO.getRazorpayPaymentId());
					orderRepository.save(order); // Save updated order to the database
					isPaymentConfirmed = true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return isPaymentConfirmed;
	}

	private boolean verifySignature(PaymentCallbackDTO paymentCallbackDTO) throws RazorpayException {
		String generatedSignature = HmacSHA256(
				paymentCallbackDTO.getRazorpayOrderId() + "|" + paymentCallbackDTO.getRazorpayPaymentId(), keySecret);
		return generatedSignature.equals(paymentCallbackDTO.getRazorpaySignature());
	}

	private String HmacSHA256(String data, String key) throws RazorpayException {
		try {
			javax.crypto.Mac mac = javax.crypto.Mac.getInstance("HmacSHA256");
			javax.crypto.spec.SecretKeySpec secretKeySpec = new javax.crypto.spec.SecretKeySpec(key.getBytes(),
					"HmacSHA256");
			mac.init(secretKeySpec);
			byte[] hash = mac.doFinal(data.getBytes());
			return new String(org.apache.commons.codec.binary.Hex.encodeHex(hash));
		} catch (Exception e) {
			throw new RazorpayException("Failed to calculate signature.", e);
		}
	}

	private LocalDate deliveryDate() {

		LocalDate currentDate = LocalDate.now();
		LocalDate futureDate = currentDate.plusDays(2);
		return futureDate;
	}
	public List<Order> getOrderDetails(String email){
        return orderRepository.findByEmail(email);
    }

	public String generateOrderTrackingId() {
		// Get the current timestamp
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String timestamp = sdf.format(new Date());

		// Generate a random UUID for uniqueness
		String randomUUID = UUID.randomUUID().toString().substring(0, 5).toUpperCase();

		// Combine timestamp and UUID to form the tracking ID
		return "OD_" + timestamp + "_" + randomUUID;
	}

}
