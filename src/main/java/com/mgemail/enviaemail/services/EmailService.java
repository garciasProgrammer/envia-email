package com.mgemail.enviaemail.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.mgemail.enviaemail.enums.StatusEmail;
import com.mgemail.enviaemail.models.EmailModel;
import com.mgemail.enviaemail.repositories.EmailRepository;

@Service
public class EmailService {

	@Autowired
	EmailRepository emailRepository;
	@Autowired
	JavaMailSender javaMailSender;

	public EmailModel sendEmail(EmailModel emailModel) {
		
		emailModel.setSendDateEmail(LocalDateTime.now());
		try {
			SimpleMailMessage msg = new SimpleMailMessage();
			msg.setFrom(emailModel.getEmailFrom());
			msg.setTo(emailModel.getEmailTo());
			msg.setSubject(emailModel.getSubject());
			msg.setText(emailModel.getText());
			
			javaMailSender.send(msg);
			
			emailModel.setStatusEmail(StatusEmail.SENT);
		} catch (Exception e) {
			emailModel.setStatusEmail(StatusEmail.ERROR);
		}finally {
			return emailRepository.save(emailModel);
		}
	}
}
