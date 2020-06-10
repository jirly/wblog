package com.jirly.sso.utils;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zhoudl
 * @date:2011-6-30 上午10:41:02
 * @version :1.0
 * 
 */
public class DateUtils {
	public static final String DATETIME_PATTERN1 = "yyyy-MM-dd HH:mm:ss";
	public static final String DATETIME_PATTERN4 = "yyyyMMddHHmmss";
	public static String convertDateToString(Date d, String format){
		if(d==null) return "";
		DateFormat df = new SimpleDateFormat(format);
		return df.format(d);
	}
}
