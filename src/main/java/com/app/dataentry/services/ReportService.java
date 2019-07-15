package com.app.dataentry.services;

import java.io.ByteArrayOutputStream;

import com.app.dataentry.report.PageList;

public interface ReportService {
	
	ByteArrayOutputStream generateReport(PageList pageList);
}
