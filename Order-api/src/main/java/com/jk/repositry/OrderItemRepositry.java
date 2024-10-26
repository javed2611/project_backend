package com.jk.repositry;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jk.entity.OrderItem;

public interface OrderItemRepositry extends JpaRepository<OrderItem, Long> {

}
