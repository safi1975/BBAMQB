package com.app.dataentry.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.app.dataentry.report.PageList;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.OutputStreamExporterOutput;

@Service
public class ReportServiceImpl {
	
	public void generateReport() {
		
		PageList pageList = new PageList();
		
		
		JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(pageList.getPageList());
		
		
		
        Map parameters = new HashMap();
        try {
               JasperPrint fillReport = JasperFillManager.fillReport("C:\\work\\data-entry-app\\src\\main\\resources\\reports\\report_template.jasper", parameters, beanColDataSource);
                JRPdfExporter exporter = new JRPdfExporter();
                exporter.setParameter(JRExporterParameter.JASPER_PRINT, fillReport);
                exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, "C:\\work\\data-entry-app\\src\\main\\resources\\reports\\report.pdf");
                exporter.exportReport();
                OutputStreamExporterOutput exporterOutput = exporter.getExporterOutput();
        } catch (JRException e) {
               e.printStackTrace();
        }
	}

}
