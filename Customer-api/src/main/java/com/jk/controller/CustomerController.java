package com.jk.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jk.dto.LoginDTO;
import com.jk.dto.RegisterDTO;
import com.jk.dto.ResetPwdDTO;
import com.jk.service.ForgetPwdService;
import com.jk.service.LoginService;
import com.jk.service.RegisterService;
import com.jk.service.ResetPwdService;

import jakarta.mail.MessagingException;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

	private RegisterService registerService;
	private LoginService loginService;
	private ForgetPwdService forgetPwdService;
	private ResetPwdService resetPwdService;

	public CustomerController(RegisterService registerService, LoginService loginService,
			ForgetPwdService forgetPwdService, ResetPwdService resetPwdService) {
		this.registerService = registerService;
		this.loginService = loginService;
		this.forgetPwdService = forgetPwdService;
		this.resetPwdService = resetPwdService;
	}

	@PostMapping(value = "/register", consumes = { "application/xml", "application/json" })
	public String register(@RequestBody RegisterDTO registerDTO) {
		return registerService.register(registerDTO);
	}

	@PostMapping(value = "/login", consumes = { "application/xml", "application/json" })
	public LoginDTO login(@RequestBody LoginDTO loginDTO) {
		return loginService.login(loginDTO);
	}

	@PostMapping("/forgetpassword/{email}")
	public boolean forgetPassword(@PathVariable String email) throws MessagingException, IOException {
		return forgetPwdService.checkEmailValid(email) ? forgetPwdService.sendMail(email) : false;
	}

	@PostMapping("/reset-pwd/{email}")
	public String resetPwd(@RequestBody ResetPwdDTO resetPwdDTO, @PathVariable String email) {
		return resetPwdService.resetPwd(resetPwdDTO, email);
	}
}
