package com.app.dataentry.services;

import org.junit.Test;

public class SmsServiceImplTest {
	
	@Test
	public void sendSmsTest() {
		SmsService smsService = new SmsServiceImpl();
		String msg = smsService.sendSms("111", "12");
		System.out.println(msg);
	}

}
