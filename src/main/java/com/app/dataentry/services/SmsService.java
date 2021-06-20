package com.app.dataentry.services;

public interface SmsService {

	String sendSms(String phone, String code);
	String sendOperatorLoggedInSMS(String phone, String operatorName);
	String sendClientRecordAddedSMS(String phone);

	String generateCode();

}
