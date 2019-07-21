package com.app.dataentry.services;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.app.dataentry.report.Page;
import com.app.dataentry.report.PageList;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class ReportServiceImpl implements ReportService {
	
	@Override
	public ByteArrayOutputStream generateReport(PageList pages) {		
		
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(pages.getPageList());

        Map<String, Object> parameters = new HashMap<>();
        try {       		
               JasperPrint fillReport = JasperFillManager.fillReport(this.getClass().getClassLoader().getResourceAsStream("reports/report_template.jasper"), parameters, beanColDataSource);
               ByteArrayOutputStream output = new ByteArrayOutputStream(); 
               JasperExportManager.exportReportToPdfStream(fillReport, output);
                return output;
        } catch (JRException e) {
               e.printStackTrace();
        }
        return null;
	}

	@Override
	public byte[] generateReportCSV(PageList pages) {
		
		ArrayList<Page> pagesArray = pages.getPageList();
		
		Map<String, String> rows = new LinkedHashMap<>();
		
		StringBuffer buffer = new StringBuffer();
		
		for (int i = 0; pagesArray.size() > i; i++) {
			Page page = pagesArray.get(i);
			if (!StringUtils.isEmpty(page.getPhone1())) {
				rows.put(String.valueOf(i+1), page.getPhone1());
			}
			if (!StringUtils.isEmpty(page.getPhone2())) {
				rows.put(String.valueOf(i+2), page.getPhone2());
			}
			if (!StringUtils.isEmpty(page.getPhone3())) {
				rows.put(String.valueOf(i+3), page.getPhone3());
			}
			if (!StringUtils.isEmpty(page.getPhone4())) {
				rows.put(String.valueOf(i+4), page.getPhone4());
			}
		}
		
		try (CSVPrinter printer = new CSVPrinter(buffer, CSVFormat.DEFAULT
			      .withHeader("Group Nos", "Mobile Nos for SMS"))) {
			rows.forEach((author, title) -> {
			            try {
							printer.printRecord(author, title);
						} catch (IOException e) {
						}
			        });
			    } catch (IOException e) {
			    	return "".getBytes();
				}
		
		return buffer.toString().getBytes();
	}
}
