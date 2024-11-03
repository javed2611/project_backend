package com.jk.service;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.jk.entity.Customer;
import com.jk.repo.CustomerRepo;
import com.jk.utils.EmailUtils;

import jakarta.mail.MessagingException;

@Service
public class ForgetPwdServiceImpl implements ForgetPwdService {

	private CustomerRepo customerRepo;
	private EmailUtils emailUtils;

	public ForgetPwdServiceImpl(CustomerRepo customerRepo, EmailUtils emailUtils) {
		this.customerRepo = customerRepo;
		this.emailUtils = emailUtils;
	}

	@Override
	public boolean sendMail(String email) throws MessagingException, IOException {
		String url = "http://localhost:4200/reset-pwd/" + email;
		Customer customer = customerRepo.findByEmail(email);
		String name = customer.getName();
		return emailUtils.sendResetPasswordMail(email, name, url);
	}

	@Override
	public boolean checkEmailValid(String email) {
		return customerRepo.findByEmail(email) != null;
	}

}
