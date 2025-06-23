package com.nt.utils;

import org.apache.tomcat.util.http.MimeHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Component
public class EmailUtil {

	@Autowired
	private JavaMailSender mailsender;
	
	public  boolean SendEmail(String to,String subject,String body)  {
	
		// creating the MimeMessage
		MimeMessage message=mailsender.createMimeMessage();
		
		// creating the MimeMessageHelper class
		MimeMessageHelper helper = new MimeMessageHelper(message);
		try {
		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(body, true);
		
		mailsender.send(message);
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
