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
				+ phone + "&message[]=Your OTP to login is " + code + "-NUVANTECH&templateid=1207165597510893709";
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
				+ " has logged into the Portal. - NUVANTECH&templateid=1207165597506529864";

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getForEntity(url, String.class);
	}

	@Override
	@Async
	public void sendClientRecordAddedSMS(String phone) {
		String url = "http://sms.studyleagueitsolutions.com/app/smsapi/index.php?key=46267AE827EDC7&campaign=13263&routeid=7&type=text&contacts="
			+ phone + "&senderid=BAZMEB&msg=Aapke Hisse ki Qurbani humare pass darj ho gayi hai from BAZMEB (BBAMQB)&template_id=1707165089316074752&pe_id=1701165054053082556";

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
