package com.app.dataentry.services;

import java.util.Random;

import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SmsServiceImpl implements SmsService {

	@Override
	public String sendSms(String phone, String code) {
		String url = "https://api.aoc-portal.com/v1/sms?apikey=sTaSeMhUPBVBfCmjfKbjZ5o6IuDD4P&type=TRANS&text=OTP to login to Data Entry Portal is "+ code +". NUVANTECH&to=" + phone + "&sender=NUVANT";
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
		return "Code sent to: " + phone;
	}

	@Override
	@Async
	public void sendOperatorLoggedInSMS(String phone, String operatorName) {

		String url = "https://api.aoc-portal.com/v1/sms?apikey=sTaSeMhUPBVBfCmjfKbjZ5o6IuDD4P&type=TRANS&text=User "+ operatorName +" has logged into the Data Entry portal. NUVANTECH&to=" + phone + "&sender=NUVANT";

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getForEntity(url, String.class);
	}

	@Override
	@Async
	public void sendClientRecordAddedSMS(String phone) {
		String url = "https://api.aoc-portal.com/v1/sms?apikey=sTaSeMhUPBVBfCmjfKbjZ5o6IuDD4P&type=TRANS&text=Your Name has been entered in our Database BBAMQB - NUVANTECH&to=" + phone + "&sender=NUVANT";

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getForEntity(url, String.class);
	}

	@Override
	public String generateCode() {
		Random r = new Random();
		int number = r.nextInt(9999);
		String code = String.format("%04d", number);
		return code;
	}
}
