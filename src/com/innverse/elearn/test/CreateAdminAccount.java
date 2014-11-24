package com.innverse.elearn.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.innverse.elearn.json.AdminAccountForm;
import com.innverse.elearn.services.AdminServiceImpl;


public class CreateAdminAccount {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/com/innverse/elearn/test/testApplicationContext.xml");
		AdminServiceImpl adminService = (AdminServiceImpl)applicationContext.getBean("adminServiceImpl");
		AdminAccountForm adminAccountForm = new AdminAccountForm();
		//TODO
		adminAccountForm.setUsername("admin");
		adminAccountForm.setPassword("p");
		adminAccountForm.setName("Admin");
		adminService.createAdminAccount(adminAccountForm);
		
	}
}
