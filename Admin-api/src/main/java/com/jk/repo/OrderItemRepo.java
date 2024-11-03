package com.jk.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jk.dto.OrderItemDTO;
import com.jk.entity.OrderItem;

@Repository
public interface OrderItemRepo extends JpaRepository<OrderItem, Long> {

	@Query("SELECT new com.jk.dto.OrderItemDTO(oi.id, oi.prodName, oi.imageUrl, oi.quantity, oi.unitPrice) "
			+ "FROM OrderItem oi WHERE oi.order.id = :orderId")
	List<OrderItemDTO> findByOrderOrderId(Integer orderId);
}
