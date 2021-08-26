package com.bsl.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static String getFormatText(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		return dateFormat.format(date);
	}
	
	/**
	 * 日期格式转换
	 * @param date
	 * @param pattern 格式
	 * @return
	 */
	public static String getFormatText(Date date,String pattern) {
		if(date == null){ 
			return "";
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		return dateFormat.format(date);
	}
}