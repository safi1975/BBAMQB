package com.app.dataentry.services;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.app.dataentry.report.PageList;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class ReportServiceImpl implements ReportService {
	
	@Override
	public ByteArrayOutputStream generateReport(PageList pageList) {		
		
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(pageList.getPageList());

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
}
