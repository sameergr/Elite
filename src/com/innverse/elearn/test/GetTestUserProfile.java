package com.innverse.elearn.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.innverse.elearn.model.UserProfile;
import com.innverse.elearn2.service.TestService;

public class GetTestUserProfile {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/com/innverse/elearn/test/test2ApplicationContext.xml");
		TestService testService = (TestService)applicationContext.getBean("testService");
		
		UserProfile userProfile = testService.getUserProfile("3");
		System.out.println(userProfile.getFirstName());
		System.out.println(userProfile.getOrganization().getName());
		
	}

}
