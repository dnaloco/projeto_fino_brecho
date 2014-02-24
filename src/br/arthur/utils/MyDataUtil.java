package br.arthur.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyDataUtil {
	public static String stampToStringData(Timestamp stamp) {
		Date myDate = new Date(stamp.getTime());
		
		String myStr = new SimpleDateFormat("dd/MM/yyyy").format(myDate);
		
		return myStr;
	}
}
