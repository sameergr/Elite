package com.innverse.elearn.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.innverse.elearn.eenum.ProficiencyType;
import com.innverse.elearn.model.Answer;
import com.innverse.elearn.model.Category;
import com.innverse.elearn.model.Content;
import com.innverse.elearn.model.MultipleChoiceAnswer;
import com.innverse.elearn.model.Organization;
import com.innverse.elearn.model.Question;
import com.innverse.elearn.model.TextQuestion;
import com.innverse.elearn.model.YesNoAnswer;
import com.innverse.elearn2.service.TestService;

public class CreateTestContent {

	private Content content;
	
	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/com/innverse/elearn/test/test2ApplicationContext.xml");
		TestService testService = (TestService)applicationContext.getBean("testService");
		
		Organization organization = testService.getOrganization("4");
		
		Category category = testService.getCategory("2");
		
//First Row
		TextQuestion tQuestion = new TextQuestion();
		tQuestion.setKeyWord("java");
		tQuestion.setSubCatagories(category);
		tQuestion.setQuestion("Is the JVM (Java Virtual Machine) platform dependent?");
		
		YesNoAnswer yesNoAnswer = new YesNoAnswer();
		yesNoAnswer.setAnswer("YES");
		
		Content c = new Content();
		c.setActive(true);
		c.setCreateBy(organization);
		c.setCatagoryId(category.getParentCategory().getId());
		c.setSubCategoryId(category.getId());
		c.setMinProficiency(ProficiencyType.BEGINNER);
		c.setMaxProficiency(ProficiencyType.INTERMIDIATE);
		c.setQuestion(tQuestion);
		c.setAnswer(yesNoAnswer);
		testService.saveContent(c);
		
//Second Row
		TextQuestion tQuestion1 = new TextQuestion();
		tQuestion1.setKeyWord("G.K.");
		tQuestion1.setSubCatagories(category);
		tQuestion1.setQuestion("is goa capital of India?");
		
		YesNoAnswer yesNoAnswer1 = new YesNoAnswer();
		yesNoAnswer1.setAnswer("NO");
		
		Content c1 = new Content();
		c1.setActive(true);
		c1.setCreateBy(organization);
		c1.setCatagoryId(category.getParentCategory().getId());
		c1.setSubCategoryId(category.getId());
		c1.setMinProficiency(ProficiencyType.BEGINNER);
		c1.setMaxProficiency(ProficiencyType.INTERMIDIATE);
		c1.setQuestion(tQuestion1);
		c1.setAnswer(yesNoAnswer1);
		testService.saveContent(c1);
		
//Third Row
		TextQuestion tQuestion2 = new TextQuestion();
		tQuestion2.setKeyWord("english");
		tQuestion2.setSubCatagories(category);
		tQuestion2.setQuestion("26 letters in english?");
		
		YesNoAnswer yesNoAnswer2 = new YesNoAnswer();
		yesNoAnswer2.setAnswer("YES");
		
		Content c2 = new Content();
		c2.setActive(true);
		c2.setCreateBy(organization);
		c2.setCatagoryId(category.getParentCategory().getId());
		c2.setSubCategoryId(category.getId());
		c2.setMinProficiency(ProficiencyType.BEGINNER);
		c2.setMaxProficiency(ProficiencyType.INTERMIDIATE);
		c2.setQuestion(tQuestion2);
		c2.setAnswer(yesNoAnswer2);
		testService.saveContent(c2);
		
//Fourth Row
		TextQuestion tQuestion3 = new TextQuestion();
		tQuestion3.setKeyWord("G.K.");
		tQuestion3.setSubCatagories(category);
		tQuestion3.setQuestion("chess invented by india?");
		
		YesNoAnswer yesNoAnswer3 = new YesNoAnswer();
		yesNoAnswer3.setAnswer("YES");
		
		Content c3 = new Content();
		c3.setActive(true);
		c3.setCreateBy(organization);
		c3.setCatagoryId(category.getParentCategory().getId());
		c3.setSubCategoryId(category.getId());
		c3.setMinProficiency(ProficiencyType.BEGINNER);
		c3.setMaxProficiency(ProficiencyType.INTERMIDIATE);
		c3.setQuestion(tQuestion3);
		c3.setAnswer(yesNoAnswer3);
		testService.saveContent(c3);
		
//Fifth Row 
		TextQuestion tQuestion4 = new TextQuestion();
		tQuestion4.setKeyWord("java");
		tQuestion4.setSubCatagories(category);
		tQuestion4.setQuestion("Does java support pointer?");
		
		YesNoAnswer yesNoAnswer4 = new YesNoAnswer();
		yesNoAnswer4.setAnswer("NO");
		
		Content c4 = new Content();
		c4.setActive(true);
		c4.setCreateBy(organization);
		c4.setCatagoryId(category.getParentCategory().getId());
		c4.setSubCategoryId(category.getId());
		c4.setMinProficiency(ProficiencyType.BEGINNER);
		c4.setMaxProficiency(ProficiencyType.INTERMIDIATE);
		c4.setQuestion(tQuestion4);
		c4.setAnswer(yesNoAnswer4);
		testService.saveContent(c4);
		
// Sixth Row
		TextQuestion tQuestion5 = new TextQuestion();
		tQuestion5.setKeyWord("G.K.");
		tQuestion5.setSubCatagories(category);
		tQuestion5.setQuestion("does india have 29 states?");
		
		YesNoAnswer yesNoAnswer5 = new YesNoAnswer();
		yesNoAnswer5.setAnswer("YES");
		
		Content c5 = new Content();
		c5.setActive(true);
		c5.setCreateBy(organization);
		c5.setCatagoryId(category.getParentCategory().getId());
		c5.setSubCategoryId(category.getId());
		c5.setMinProficiency(ProficiencyType.BEGINNER);
		c5.setMaxProficiency(ProficiencyType.INTERMIDIATE);
		c5.setQuestion(tQuestion5);
		c5.setAnswer(yesNoAnswer5);
		testService.saveContent(c5);
		
//Seventh Row
		TextQuestion tQuestion6 = new TextQuestion();
		tQuestion6.setKeyWord("java");
		tQuestion6.setSubCatagories(category);
		tQuestion6.setQuestion("java invented by Jamy Gosling?");
		
		YesNoAnswer yesNoAnswer6 = new YesNoAnswer();
		yesNoAnswer6.setAnswer("NO");
		
		Content c6 = new Content();
		c6.setActive(true);
		c6.setCreateBy(organization);
		c6.setCatagoryId(category.getParentCategory().getId());
		c6.setSubCategoryId(category.getId());
		c6.setMinProficiency(ProficiencyType.BEGINNER);
		c6.setMaxProficiency(ProficiencyType.INTERMIDIATE);
		c6.setQuestion(tQuestion6);
		c6.setAnswer(yesNoAnswer6);
		testService.saveContent(c6);
		
//Eight Row
		TextQuestion tQuestion7 = new TextQuestion();
		tQuestion7.setKeyWord("G.K.");
		tQuestion7.setSubCatagories(category);
		tQuestion7.setQuestion("INDIA is the world's largest producer of milk?");
		
		YesNoAnswer yesNoAnswer7 = new YesNoAnswer();
		yesNoAnswer7.setAnswer("YES");
		
		Content c7 = new Content();
		c7.setActive(true);
		c7.setCreateBy(organization);
		c7.setCatagoryId(category.getParentCategory().getId());
		c7.setSubCategoryId(category.getId());
		c7.setMinProficiency(ProficiencyType.BEGINNER);
		c7.setMaxProficiency(ProficiencyType.INTERMIDIATE);
		c7.setQuestion(tQuestion7);
		c7.setAnswer(yesNoAnswer7);
		testService.saveContent(c7);
		
//Ninth Row
		TextQuestion tQuestion8 = new TextQuestion();
		tQuestion8.setKeyWord("G.K.");
		tQuestion8.setSubCatagories(category);
		tQuestion8.setQuestion("The world richest country in 2013 is USA?");
		
		YesNoAnswer yesNoAnswer8 = new YesNoAnswer();
		yesNoAnswer8.setAnswer("NO");
		
		Content c8 = new Content();
		c8.setActive(true);
		c8.setCreateBy(organization);
		c8.setCatagoryId(category.getParentCategory().getId());
		c8.setSubCategoryId(category.getId());
		c8.setMinProficiency(ProficiencyType.BEGINNER);
		c8.setMaxProficiency(ProficiencyType.INTERMIDIATE);
		c8.setQuestion(tQuestion8);
		c8.setAnswer(yesNoAnswer8);
		testService.saveContent(c8);
		
//Tenth Row
		TextQuestion tQuestion9 = new TextQuestion();
		tQuestion9.setKeyWord("G.K.");
		tQuestion9.setSubCatagories(category);
		tQuestion9.setQuestion("Somalia is the most corrupted country in the world?");
		
		YesNoAnswer yesNoAnswer9 = new YesNoAnswer();
		yesNoAnswer9.setAnswer("YES");
		
		Content c9 = new Content();
		c9.setActive(true);
		c9.setCreateBy(organization);
		c9.setCatagoryId(category.getParentCategory().getId());
		c9.setSubCategoryId(category.getId());
		c9.setMinProficiency(ProficiencyType.BEGINNER);
		c9.setMaxProficiency(ProficiencyType.INTERMIDIATE);
		c9.setQuestion(tQuestion9);
		c9.setAnswer(yesNoAnswer9);
		testService.saveContent(c9);		
	}
}
