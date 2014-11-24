package com.innverse.elearn.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.innverse.elearn.model.UserProfile;
import com.innverse.elearn2.service.TestService;


public class CreateTestUserProfile {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/com/innverse/elearn/test/test2ApplicationContext.xml");
		TestService testService = (TestService)applicationContext.getBean("testService");

		UserProfile userProfile = new UserProfile();
		userProfile.setUsername("sumit@innverse.com");
		userProfile.setFirstName("Sumit");
		userProfile.setLastName("Jagia");
		userProfile.setEmail("sumit@innverse.com");
		userProfile.setPhone("9818118731");
		
		testService.saveUserProfile(userProfile);
		
	}

}
