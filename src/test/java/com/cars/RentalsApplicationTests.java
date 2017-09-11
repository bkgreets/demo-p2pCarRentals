package com.cars;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = RentalsApplication.class)
//@WebAppConfiguration
public class RentalsApplicationTests {

//	@Test
//	public void contextLoads() {
//		
//		String str = "Honda_Accord.jpg";
//		System.out.println(str.split(".")[0]);
//	}
	
	
	public static void main(String[] args) {
		String str = "Honda_Accord.jpg";
		String[] strA = StringUtils.split(str,'.');
		
		
		System.out.println(strA);
	}
	

}
