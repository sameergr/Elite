package com.innverse.elearn.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.innverse.elearn2.service.TestService;

public class CompareList {
	
	public static void main(String s[]){
		ApplicationContext appContext = new ClassPathXmlApplicationContext("/com/innverse/elearn/test/test2ApplicationContext.xml");
		TestService testService = (TestService) appContext.getBean("testService");
		
		System.out.println("!!!!!!!!!");
	}

}
