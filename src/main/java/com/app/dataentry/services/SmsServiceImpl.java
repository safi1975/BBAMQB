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
		String url = "http://vas.mobilogi.com/api.php?username=bbamqb&password=D4rkh0lme&route=1&sender=BBAMQB&mobile[]="
				+ phone + "&message[]=Your OTP to login is " + code + "-BBAMQB&templateid=1207162306397172384";
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
		if (response.getBody().contains("msgid")) {
			return "Code sent to: " + phone;
		} else {
			return "Could not send code";
		}
	}

	@Override
	@Async
	public void sendOperatorLoggedInSMS(String phone, String operatorName) {

		String url = "http://vas.mobilogi.com/api.php?username=bbamqb&password=D4rkh0lme&route=1&sender=BBAMQB&mobile[]="
				+ phone + "&message[]=User " + operatorName
				+ " has logged into the Portal - BBAMQB&templateid=1207162417108069794";

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getForEntity(url, String.class);
	}

	@Override
	@Async
	public void sendClientRecordAddedSMS(String phone) {
		String url = "http://vas.mobilogi.com/api.php?username=bbamqb&password=D4rkh0lme&route=1&sender=BBAMQB&mobile[]="
				+ phone
				+ "&message[]=Aapke Hisse ki Qurbani humare pass darj ho gayi hai - BBAMQB&templateid=1207162417094736115";

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
