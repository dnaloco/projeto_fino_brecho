package br.arthur.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import javax.swing.JInternalFrame;

import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPage;
import com.sun.pdfview.PagePanel;

public class LeitorPdfUtil extends JInternalFrame {

	public LeitorPdfUtil(String strFile) {
		// setBounds(100, 100, 450, 300);
		PagePanel panel = new PagePanel();
		add(panel);
		pack();
		setVisible(true);
		
		File file = new File(strFile);
		RandomAccessFile raf = null;
		try {
			raf = new RandomAccessFile(file, "r");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}  
        FileChannel channel = raf.getChannel();  
        ByteBuffer buf = null;
		try {
			buf = channel.map(FileChannel.MapMode.READ_ONLY,  
			    0, channel.size());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        PDFFile pdffile = null;
		try {
			pdffile = new PDFFile(buf);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
  
        // show the first page  
        PDFPage page = pdffile.getPage(0);  
        panel.showPage(page); 
	}

}
