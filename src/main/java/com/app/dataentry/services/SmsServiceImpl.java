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
		String url = "https://demo.digitalsms.biz/api/?apikey=2e14610766c9e30a455142acb0e1a11b&mobile="
				+ phone + "&msg=Your OTP to login is " + code + "-BBAMQB";
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

		String url = "https://demo.digitalsms.biz/api/?apikey=2e14610766c9e30a455142acb0e1a11b&mobile="
				+ phone + "&msg=User " + operatorName
				+ " has logged into the Portal. - BBAMQB";

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getForEntity(url, String.class);
	}

	@Override
	@Async
	public void sendClientRecordAddedSMS(String phone) {
		String url = "https://demo.digitalsms.biz/api/?apikey=2e14610766c9e30a455142acb0e1a11b&mobile="
			+ phone + "&msg=Your Name has been entered in our Database BBAMQB";

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
