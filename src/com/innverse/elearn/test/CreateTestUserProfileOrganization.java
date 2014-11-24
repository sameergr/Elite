package com.innverse.elearn.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.innverse.elearn.model.Organization;
import com.innverse.elearn.model.UserProfile;
import com.innverse.elearn2.service.TestService;

public class CreateTestUserProfileOrganization {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/com/innverse/elearn/test/test2ApplicationContext.xml");
		TestService testService = (TestService)applicationContext.getBean("testService");

		UserProfile userProfile = new UserProfile();
		userProfile.setUsername("sameer@innverse.com");
		userProfile.setFirstName("Sameer");
		userProfile.setLastName("Kumar");
		userProfile.setEmail("sameer@innverse.com");
		userProfile.setPhone("9818118731");
		
		Organization organization = new Organization();
		organization.setName("S-LMS");
		userProfile.setOrganization(organization);
		testService.saveUserProfile(userProfile);
		
	}

}
