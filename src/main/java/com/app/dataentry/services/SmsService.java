package com.app.dataentry.services;

public interface SmsService {

	String sendSms(String phone, String code);

	void sendOperatorLoggedInSMS(String phone, String operatorName);

	void sendClientRecordAddedSMS(String phone);

	String generateCode();

}
