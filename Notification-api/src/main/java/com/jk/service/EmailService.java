package com.jk.service;

import com.jk.dto.EmailDetails;

public interface EmailService {
	public String sendMailWithAttachment(EmailDetails details);
}
