package com.bsl.test;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.springframework.util.DigestUtils;

public class MD5Test {

//	@Test
	public void tset() {
		System.out.println(DigestUtils.md5DigestAsHex("admin".getBytes()));
	}
	
//	@Test
	public void test() {
		long incr = 3;
//		String userId = String.format("%08d", incr);
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String userId = String.format("Z%s%02d", sdf.format(new Date()), incr);
		System.out.println(userId);
	}
}
