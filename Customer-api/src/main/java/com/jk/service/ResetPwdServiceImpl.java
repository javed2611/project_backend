package com.jk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jk.dto.ResetPwdDTO;
import com.jk.entity.Customer;
import com.jk.repo.CustomerRepo;

@Service
public class ResetPwdServiceImpl implements ResetPwdService {

	@Autowired
	private CustomerRepo customerRepo;

	@Override
	public String resetPwd(ResetPwdDTO resetPwdDTO, String email) {
		Customer customer = customerRepo.findByEmail(email);
		if (customer == null) {
			return "Please enter the valid email";
		}
		if (resetPwdDTO.getPwd().equals(resetPwdDTO.getCnfmPwd())) {
			customer.setPwd(resetPwdDTO.getCnfmPwd());
			customerRepo.save(customer);
			return "Password changed!! Try to login again";
		} else {
			return "Password not matching";
		}
	}

}
