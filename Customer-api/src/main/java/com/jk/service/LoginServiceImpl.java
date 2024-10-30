package com.jk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jk.dto.LoginDTO;
import com.jk.entity.Customer;
import com.jk.repo.CustomerRepo;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private CustomerRepo customerRepo;
	
	@Override
	public LoginDTO login(LoginDTO loginDTO) {
		Customer customer = customerRepo.findByEmailAndPwd(loginDTO.getEmail(), loginDTO.getPwd());
		LoginDTO dto = new LoginDTO();
		if (customer != null) {
			dto.setEmail(loginDTO.getEmail());
			dto.setPwd(loginDTO.getPwd());
			return dto;
		}
		return dto;
	}
}