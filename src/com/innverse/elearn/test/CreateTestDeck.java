package com.innverse.elearn.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.innverse.elearn.model.Deck;
import com.innverse.elearn.model.Group;
import com.innverse.elearn.model.Organization;
import com.innverse.elearn.model.Series;
import com.innverse.elearn2.service.TestService;

public class CreateTestDeck {
	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/com/innverse/elearn/test/test2ApplicationContext.xml");
		TestService testService = (TestService)applicationContext.getBean("testService");
		
		Organization org = testService.getOrganization("4");
		Series series = testService.getSeries("1");
		Group group = testService.getGroupById(1);
		
		Deck newDeck = new Deck();
		newDeck.setSeries(series);
		newDeck.setGroup(group);
		newDeck.setActive(true);
		newDeck.setOrganization(org);
		testService.saveDeck(newDeck);
		
	}
}
