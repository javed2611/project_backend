package com.jk.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.jk.dto.RegisterDTO;
import com.jk.entity.Customer;
import com.jk.repo.CustomerRepo;

@Service
public class RegisterServiceImpl implements RegisterService {

	private CustomerRepo customerRepo;

	public RegisterServiceImpl(CustomerRepo customerRepo) {
		this.customerRepo = customerRepo;
	}

	private Customer customer;

	@Override
	public String register( RegisterDTO registerDTO) {
		customer = customerRepo.findByEmail(registerDTO.getEmail());
		System.out.println(registerDTO);
		if (customer != null) {
			System.out.println(customer);
			return "User already Registered";
		} else {
			customer = new Customer();
			BeanUtils.copyProperties(registerDTO, customer);
			System.out.println(customer);
			customerRepo.save(customer);
			return "Successfully registered";
		}
	}

}
