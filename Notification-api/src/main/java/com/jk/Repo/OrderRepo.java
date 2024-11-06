package com.jk.Repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jk.entity.Order;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {
	List<Order> findByOrderStatus(String orderStatus);
	List<Order> findByOrderStatusNot(String orderStatus);
	List<Order> findByDeilveryDate(LocalDate deilveryDate);
}
