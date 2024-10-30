package com.jk.utils;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Component
public class EmailUtils {

	@Autowired
	private JavaMailSender javaMailSender;
	@Autowired
	private EmailTemplateLoader emailTemplateLoader;

	public boolean sendResetPasswordMail(String to, String name, String resetLink)
			throws MessagingException, IOException {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

		String template = emailTemplateLoader.loadTemplete("classpath:templates/reset-password.html");
		String body = emailTemplateLoader.replacePlaceHolders(template, name, resetLink);
		helper.setTo(to);
		helper.setSubject("Reset Password Request");
		helper.setText(body, true);
		javaMailSender.send(message);
		return true;
	}
}
