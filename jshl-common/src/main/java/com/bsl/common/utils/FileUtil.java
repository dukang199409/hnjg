package com.bsl.common.utils;

import java.io.File;
public class FileUtil {
	/**
     * 删除文件
     * @param sPath 文件路径
     * @return
     */
    public static boolean delectFile(String sPath){
    	boolean flag = false;
	    File file = new File(sPath);  
	    // 判断目录或文件是否存在  
	    if (!file.exists()) {  // 不存在返回 false  
	        return flag;  
	    } else {  
	        // 判断是否为文件  
	        if (file.isFile()) {  // 为文件时调用删除文件方法  
	            
	        	file.delete();
	        } 
	    } 
    	return flag;
    }
}
