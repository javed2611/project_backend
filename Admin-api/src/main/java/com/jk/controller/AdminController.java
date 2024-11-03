package com.jk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jk.dto.DashboardDTO;
import com.jk.dto.OrderItemDTO;
import com.jk.dto.ReportDTO;
import com.jk.entity.Order;
import com.jk.service.DashboardSerivce;
import com.jk.service.ReportService;

@CrossOrigin("http://localhost:4200/")
@RestController
@RequestMapping(value = "/api/admin")
public class AdminController {
	@Autowired
	private ReportService reportService;
	@Autowired
	private DashboardSerivce dashboardSerivce;

	@GetMapping("/dashboard")
	public ResponseEntity<DashboardDTO> getDashboardData() {
		DashboardDTO dashboardDTO = dashboardSerivce.fetchDashboard();
		return new ResponseEntity<DashboardDTO>(dashboardDTO, HttpStatus.OK);
	}

	@PostMapping("/filter")
	public List<Order> filterOrder(@RequestBody ReportDTO reportDTO) {
		return reportService.filterOrders(reportDTO);
	}

	@GetMapping(value = "/orderItems")
	public List<OrderItemDTO> fetchOrderItems(@RequestParam Integer orderId) {
		List<OrderItemDTO> orderItems = reportService.getOrderItems(orderId);
		return orderItems;
	}

}
