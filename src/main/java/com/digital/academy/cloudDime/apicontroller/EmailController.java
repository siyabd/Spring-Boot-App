package com.digital.academy.cloudDime.apicontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.digital.academy.cloudDime.implementation.EmailServices;

@RestController
@RequestMapping("cloudDime/getotp")
public class EmailController {
	
	@Autowired
	private EmailServices emailServices;
	
	@GetMapping("getotp")
	public String sendOTP(@RequestParam String email,@RequestParam String otp) {
		//String emai,String subject,String mailBody
		
		String message = ""+otp;
		emailServices.sendEmail(email, "Password Reset : OTP", "toger");
		
		return null;
	}

}
