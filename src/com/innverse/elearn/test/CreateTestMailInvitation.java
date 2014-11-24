package com.innverse.elearn.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.innverse.elearn.dao.CommonDaoImpl;
import com.innverse.elearn.mail.Testmail;
import com.innverse.elearn.model.Invitation;
import com.innverse.elearn2.service.TestService;

public class CreateTestMailInvitation {

	private Invitation invitation;
	
	public static void main(String[] args) {
		
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/com/innverse/elearn/test/test2ApplicationContext.xml");
		TestService testService = (TestService)applicationContext.getBean("testService");
		
		String bulkEmail = "sameer@innverse.com,manish@innverse.com,shailendra@innverse.com";
		for(String friendEmail: bulkEmail.split(",")){
			Invitation invite = new Invitation();
			invite.setActive(true);
			invite.setFriendEmailId(friendEmail);
			invite.setInviteBy("manish");
			invite.setInviteeId(12);
			invite.setOrganizationId(25);
			invite.setResult("true");
			invite.setValid("yes");
			
			testService.getInvitation(invite);
		}
	}
	
}
