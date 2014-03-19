package br.arthur.temp.tests;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class anyTeste01 {
	public static void main(String[] args) throws IOException {
		String fileReports = "relatorios/reports/TestProdutos.jrxml";
		String fileRelator = "relatorios/teste2.pdf";

	    // A File object to represent the filename
	    File f = new File(fileRelator);
	    boolean success;
	    // Make sure the file or directory exists and isn't write protected
	    if (f.exists()) {
			if (!f.canWrite())
			      throw new IllegalArgumentException("Delete: write protected: "
			      + fileRelator);

			success = f.delete();
	    } else {
	    	success = true;
	    }

	    if (!success)
	      throw new IllegalArgumentException("Delete: deletion failed");
	    
	    List dataReport = new ArrayList();
	    HashMap<String, String> data = new HashMap<String, String>();
	    
	    //data.put("image", "images/add-icon.png");
	    data.put("email", "qualquer coisa");
	    
	    dataReport.add(data);		

	    
		try {
			JasperReport report = JasperCompileManager.compileReport(fileReports);
			JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(dataReport));
			JasperExportManager.exportReportToPdfFile(print, fileRelator);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}
}
