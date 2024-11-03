package com.jk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.jk.dto.OrderItemDTO;
import com.jk.dto.ReportDTO;
import com.jk.entity.Order;
import com.jk.repo.OrderItemRepo;
import com.jk.repo.OrderRepo;
import com.jk.specification.ReportSpeicification;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private OrderRepo orderRepo;

	@Autowired
	private OrderItemRepo orderItemRepo;
	@Override
	public List<Order> filterOrders(ReportDTO reportDTO) {
		Specification<Order> specification = Specification
				.where(ReportSpeicification.hasCustomerEmail(reportDTO.getCustomerEmail()))
				.and(ReportSpeicification.hasStartDate(reportDTO.getStartDate()))
				.and(ReportSpeicification.hasEndDate(reportDTO.getEndDate()));
		return orderRepo.findAll(specification);
	}
	public List<OrderItemDTO> getOrderItems(Integer orderId) {
        List<OrderItemDTO> orderItemList = orderItemRepo.findByOrderOrderId(orderId);
        System.out.println(orderItemList);
        return orderItemList;
    }

}
