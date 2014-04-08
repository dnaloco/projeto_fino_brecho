package br.arthur.temp.tests;

import java.net.MalformedURLException;
import java.net.URL;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;

public class AnyTest2 {

	public static void main(String[] args) {
		// http://upload.wikimedia.org/wikipedia/commons/thumb/3/37/Clock.gif/50px-Clock.gif
		 PrintService service = PrintServiceLookup.lookupDefaultPrintService();
		 DocPrintJob job = service.createPrintJob();
		 URL url = null;
		try {
			url = new URL(
			    "http://upload.wikimedia.org/wikipedia/commons/thumb/3/37/Clock.gif/50px-Clock.gif");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 DocFlavor flavor = DocFlavor.URL.GIF;
		 Doc doc = new SimpleDoc(url, flavor, null);
		 PrintRequestAttributeSet attrs = new HashPrintRequestAttributeSet();
		 attrs.add(new Copies(2));
		 try {
			job.print(doc, attrs);
		} catch (PrintException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
 

	}

}
