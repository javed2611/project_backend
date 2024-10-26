package com.jk.repositry;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jk.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
	public List<Order> findByEmail(String email);

	public Order findByRazorPayOrderId(String razorPayOrderId);
}
