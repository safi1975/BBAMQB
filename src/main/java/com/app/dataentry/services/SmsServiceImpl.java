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
		String url = "http://vas.mobilogi.com/api.php?username=bbamqb&password=D4rkh0lme&route=1&sender=NUVANT&mobile[]=" +
			 phone + "&message[]=OTP to login to Data Entry Portal is "+ code +". NUVANTECH&templateid=1707171644837732653";
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
		return "Code sent to: " + phone;
	}

	@Override
	@Async
	public void sendOperatorLoggedInSMS(String phone, String operatorName) {

		String url = "http://vas.mobilogi.com/api.php?username=bbamqb&password=D4rkh0lme&route=1&sender=NUVANT&mobile[]="
			+ phone + " &message[]=User "+ operatorName +" has logged into the Data Entry portal. NUVANTECH&templateid=1707171568566975291";

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getForEntity(url, String.class);
	}

	@Override
	@Async
	public void sendClientRecordAddedSMS(String phone) {
		String url = "http://vas.mobilogi.com/api.php?username=bbamqb&password=D4rkh0lme&route=1&sender=NUVANT&mobile[]="
			+ phone + "&message[]=Your Name has been entered in our Database BBAMQB - NUVANTECH&templateid=1707171569763280386";

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
