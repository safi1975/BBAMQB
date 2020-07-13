package com.app.dataentry.services;

public interface SmsService {

	String sendSms(String phone, String code);

	String generateCode();

}
