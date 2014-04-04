package br.arthur.utils;

import java.io.File;
import java.util.List;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class RelatorioUtil {
	public static boolean gerarRelatorio(List data, String file) {
		String fileReports = "relatorios/reports/" + file +  ".jrxml";
		String fileRelator = "relatorios/" + file + ".pdf";

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
		
		try {
			JasperReport report = JasperCompileManager.compileReport(fileReports);
			JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(data));
			JasperExportManager.exportReportToPdfFile(print, fileRelator);
		} catch (JRException e) {
			e.printStackTrace();
		}
		
		return success;
	}
}
