package com.digital.academy.cloudDime.implementation;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class EmailServices {
	
	private JavaMailSender javaMailSender; 
	
	@Autowired
	public EmailServices(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
	
	public void sendEmail(String emai,String subject,String mailBody) {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(emai);
		mail.setSubject(subject);
		mail.setText(mailBody);
		mail.setFrom("CloudDime_Team");
		mail.setSentDate(Date.valueOf(LocalDate.now()));
		javaMailSender.send(mail);
		
	}

}
