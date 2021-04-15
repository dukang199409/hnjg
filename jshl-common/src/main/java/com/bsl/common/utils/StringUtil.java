package com.bsl.common.utils;

public class StringUtil {
	
	public static String likeStr(String str) {
		return "%" + str + "%";
	}
	
	public static String castToString(Object obj) {
		if(obj == null){
			return "";
		}
		return String.valueOf(obj);
	}
}
