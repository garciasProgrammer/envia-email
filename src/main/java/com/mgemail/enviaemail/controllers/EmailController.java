package com.mgemail.enviaemail.controllers;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mgemail.enviaemail.dtos.EmailDto;
import com.mgemail.enviaemail.models.EmailModel;
import com.mgemail.enviaemail.services.EmailService;

import ch.qos.logback.core.joran.util.beans.BeanUtil;

@RestController
public class EmailController {

	@Autowired
	EmailService emailService;
	
	@PostMapping("/email-sending")
	public ResponseEntity<EmailModel> sendingEmail(@RequestBody @Valid EmailDto emailDto){
		
		EmailModel emailModel = new EmailModel();
		BeanUtils.copyProperties(emailDto, emailModel);
		emailService.sendEmail(emailModel);
		return new  ResponseEntity<>(emailModel, HttpStatus.CREATED);
		
	}
	
}
