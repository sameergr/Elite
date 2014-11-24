package com.innverse.elearn.test;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.innverse.elearn.model.Category;
import com.innverse.elearn2.service.TestService;

public class CreateTestCategory {
	
	
	private Category category;
	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/com/innverse/elearn/test/test2ApplicationContext.xml");
		TestService testService = (TestService)applicationContext.getBean("testService");

		Category c1 = new Category();
		c1.setCategoryName("Advertising manager");
		c1.setDescription("Advertising manager");
		c1.setParentCategory(null);
		c1 = testService.saveOrUpdateCategory(c1);
		 
		Category c2 = new Category();
		c2.setCategoryName("Lorem ipsum");
		c2.setDescription("Lorem ipsum");
		c2.setParentCategory(c1);
		testService.saveOrUpdateCategory(c2);
		
		Category c3 = new Category();
		c3.setCategoryName("Mauris congue");
		c3.setDescription("Mauris congue");
		c3.setParentCategory(c1);
		testService.saveOrUpdateCategory(c3);
		
		Category c4 = new Category();
		c4.setCategoryName("Ut ut tortor");
		c4.setDescription("Ut ut tortor");
		c4.setParentCategory(c1);
		testService.saveOrUpdateCategory(c4);
		
		Category c5 = new Category();
		c5.setCategoryName("Sed at lorem");
		c5.setDescription("Sed at lorem");
		c5.setParentCategory(c1);
		testService.saveOrUpdateCategory(c5);
		
		Category c6 = new Category();
		c6.setCategoryName("Proin vulputate");
		c6.setDescription("Proin vulputate");
		c6.setParentCategory(c1);
		testService.saveOrUpdateCategory(c6);
		
		Category c7 = new Category();
		c7.setCategoryName("Lorem ipsum");
		c7.setDescription("Lorem ipsum");
		c7.setParentCategory(c1);
		testService.saveOrUpdateCategory(c7);
		
		Category c8 = new Category();
		c8.setCategoryName("Ut ut tortor");
		c8.setDescription("Ut ut tortor");
		c8.setParentCategory(c1);
		testService.saveOrUpdateCategory(c8);
		
		Category c9 = new Category();
		c9.setCategoryName("Mauris congue");
		c9.setDescription("Mauris congue");
		c9.setParentCategory(c1);
		testService.saveOrUpdateCategory(c9);
		
	}
	
	public void createCategoryList(){		
		
	}
}
