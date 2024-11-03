package com.jk.service;

import java.util.List;

import com.jk.dto.OrderItemDTO;
import com.jk.dto.ReportDTO;
import com.jk.entity.Order;

public interface ReportService {

	public List<Order> filterOrders(ReportDTO reportDTO);
	public List<OrderItemDTO> getOrderItems(Integer orderId);
}
