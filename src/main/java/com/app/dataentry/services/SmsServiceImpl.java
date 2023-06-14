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
				+ phone + "&msg=Your%20OTP%20to%20login%20is%20" + code + "-BBAMQB";
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
		return "Code sent to: " + phone;
	}

	@Override
	@Async
	public void sendOperatorLoggedInSMS(String phone, String operatorName) {

		String url = "https://demo.digitalsms.biz/api/?apikey=2e14610766c9e30a455142acb0e1a11b&mobile="
				+ phone + "&msg=User%20" + operatorName
				+ "%20has%20logged%20into%20the%20Portal.%20BBAMQB";

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getForEntity(url, String.class);
	}

	@Override
	@Async
	public void sendClientRecordAddedSMS(String phone) {
		String url = "https://demo.digitalsms.biz/api/?apikey=2e14610766c9e30a455142acb0e1a11b&mobile="
			+ phone + "&msg=Your%20Name%20has%20been%20entered%20in%20our%20Database.%20BBAMQB";

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
