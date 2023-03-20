package com.us.utility;

import java.io.File;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.us.constants.ErrorConstants;
import com.us.exception.UsException;

import jakarta.mail.internet.MimeMessage;


@Component
public class MailUtility {

	@Autowired
	private JavaMailSender emailSender;

	@Autowired(required=true)
	private Environment environment;

	public void sendSimpleMessage(String to, String subject, String text) throws Exception {
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom(environment.getRequiredProperty("email.from"));
			message.setTo(to);
			message.setSubject(subject);
			message.setText(text);
			emailSender.send(message);
		} catch (Exception e) {
			throw new UsException(ErrorConstants.INTERNAL_SERVER_ERROR.toString(), "Error sending email");
		}
	}

	public void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment)
			throws Exception {

		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setFrom(environment.getRequiredProperty("email.from"));
		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(text);

		FileSystemResource file = new FileSystemResource(new File(pathToAttachment));
		helper.addAttachment("Invoice", file);
		emailSender.send(message);
	}
}