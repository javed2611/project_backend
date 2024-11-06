package com.jk.service;

import java.io.File;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.jk.dto.EmailDetails;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	public String sendMailWithAttachment(EmailDetails details) {
		if (StringUtils.isNotEmpty(details.getAttachment())) {
			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper;

			try {
				helper = new MimeMessageHelper(message, true);
				helper.setTo(details.getRecipient());
				helper.setText(details.getMsgBody());
				helper.setSubject(details.getSubject());
				FileSystemResource file = new FileSystemResource(new File(details.getAttachment()));
				helper.addAttachment(file.getFilename(), file);
				javaMailSender.send(message);
				return "Mail Sent";
			} catch (MessagingException e) {
				return "Error!!! Cannot send mail";
			}
		} else {
			return "Attachment not found";
		}
	}
}
