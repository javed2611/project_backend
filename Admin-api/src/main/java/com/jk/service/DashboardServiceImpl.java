package com.jk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jk.dto.DashboardDTO;
import com.jk.repo.CustomerRepo;
import com.jk.repo.OrderRepo;
import com.jk.repo.ProductRepo;

@Service
public class DashboardServiceImpl implements DashboardSerivce {

	@Autowired
	private CustomerRepo customerRepo;
	@Autowired
	private OrderRepo orderRepo;
	@Autowired
	private ProductRepo productRepo;

	@Override
	public DashboardDTO fetchDashboard() {
		DashboardDTO dashboardDTO = new DashboardDTO();
		dashboardDTO.setProductCount(productRepo.count());
		dashboardDTO.setCustomersCount(customerRepo.count());
		dashboardDTO.setOrdersCount(orderRepo.count());
		dashboardDTO.setAmountCollected(orderRepo.findTotalAmount());
		return dashboardDTO;
	}

}
