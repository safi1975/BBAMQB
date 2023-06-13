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
		String url = "https://demo.digitalsms.biz/api/?apikey=fd5585a5b7be884960fdcbb936917ebb&mobile="
				+ phone + "&msg=Your OTP to login is " + code + "-NUVANTECH";
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

		String url = "https://demo.digitalsms.biz/api/?apikey=fd5585a5b7be884960fdcbb936917ebb&mobile="
				+ phone + "&message=User " + operatorName
				+ " has logged into the Portal. - NUVANTECH";

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getForEntity(url, String.class);
	}

	@Override
	@Async
	public void sendClientRecordAddedSMS(String phone) {
		String url = "https://demo.digitalsms.biz/api/?apikey=fd5585a5b7be884960fdcbb936917ebb&mobile="
			+ phone + "&message=Your Name has been entered in our Database BBAMQB - NUVANTECH";

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
