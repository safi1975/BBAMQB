package com.app.dataentry.services;

import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class SmsServiceImpl {
	
	public String sendSms(String phone) {
		Random r = new Random();
		int number = r.nextInt(9999);
		String code = String.format("%04d", number);
		return code;
	}
}
