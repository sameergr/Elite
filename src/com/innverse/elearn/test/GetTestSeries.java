package com.innverse.elearn.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.innverse.elearn.model.Content;
import com.innverse.elearn.model.Level;
import com.innverse.elearn.model.Series;
import com.innverse.elearn2.service.TestService;

public class GetTestSeries {
	
public static void main(String[] args) {
	ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/com/innverse/elearn/test/test2ApplicationContext.xml");
	TestService testService = (TestService)applicationContext.getBean("testService");
	
	Series series = testService.getSeries("1");
	
	for(Level level : series.getLevels()){
		
	System.out.println("Detail of First Series :"+level.getQuestions());
	
	for(Content content: level.getQuestions()){
		
		System.out.println("Question of First Series :"+content.getQuestion());
	}
	
	}
	
	Series series2 = testService.getSeries("2");
	
	for(Level level : series2.getLevels()){
		
	System.out.println("Detail of First Series2 :"+level.getQuestions());
	
	for(Content content: level.getQuestions()){
		
		System.out.println("Question of First Series :"+content.getQuestion());
	}
	
	}
}

}
