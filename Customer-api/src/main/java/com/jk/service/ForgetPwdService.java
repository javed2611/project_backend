package com.jk.service;

import java.io.IOException;

import jakarta.mail.MessagingException;

public interface ForgetPwdService {
	public boolean sendMail(String email) throws MessagingException, IOException;

	public boolean checkEmailValid(String email);
}
