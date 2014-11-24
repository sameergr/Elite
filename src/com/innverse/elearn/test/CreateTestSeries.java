package com.innverse.elearn.test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.innverse.elearn.model.Category;
import com.innverse.elearn.model.Content;
import com.innverse.elearn.model.Level;
import com.innverse.elearn.model.Organization;
import com.innverse.elearn.model.Series;
import com.innverse.elearn.util.DateTimeUtils;
import com.innverse.elearn2.service.TestService;

public class CreateTestSeries {
	
	private Series series;

	public static void main(String[] args) {		
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/com/innverse/elearn/test/test2ApplicationContext.xml");
		TestService testService = (TestService)applicationContext.getBean("testService");
		
		SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		timeFormat = DateTimeUtils.getGMTSimpleDateFormat(timeFormat);
		Calendar currentTime = DateTimeUtils.getGMTCalendar();
		
		Organization organization = testService.getOrganization("4");
		
		Category category = testService.getCategory("2");
		
		List<Content> contentList = testService.getContentList("2");		
				
//--Series1----Start
		Series series = new Series();
		series.setSeriesName("General Question");
		series.setCategory(category.getParentCategory());
		series.setCreateBy(organization);
		series.setCreditPoints(10.5);
		series.setQuestionLevelTiming("15");
		series.setTotalTime("180");
		series.setCreateOn(currentTime.getTime());
		
//--Level-------Start		
		Level level = new Level();		
		for(Content content : contentList){
			level.addQuestion(content);
		}
		level.setCreditPoints(10.5);
		level.setFirstLevel(true);
		level.setLastLevel(false);
		level.setLevelname("First_Level");
		level.setLevelOrder(1);
		level.setMasteryScore("80");
		level.setMaxTryOrHit(2);
		level.setPassingScore("60");
		level.setSeries(series);
//--Level-------End	
		
		ArrayList<Level> levelList = new ArrayList<Level>();
		levelList.add(level);
		series.setLevels(levelList);
		
		testService.saveOrUpdateSeries(series);
//--Series1-- End
		
//--Series2--Start
		
		Series series1 = new Series();
		series1.setSeriesName("General Question");
		series1.setCategory(category.getParentCategory());
		series1.setCreateBy(organization);
		series1.setCreditPoints(10.5);
		series1.setCreateOn(currentTime.getTime());
		series.setQuestionLevelTiming("15");
		series.setTotalTime("180");
		
//--Level-------Start		
		Level level1 = new Level();		
		for(Content content : contentList){
			level1.addQuestion(content);
		}
		level1.setCreditPoints(10.5);
		level1.setFirstLevel(true);
		level1.setLastLevel(false);
		level1.setLevelname("Second_Level");
		level1.setLevelOrder(1);
		level1.setMasteryScore("80");
		level1.setMaxTryOrHit(2);
		level1.setPassingScore("60");
		level1.setSeries(series1);
//--Level-------End	
		
		ArrayList<Level> levelList1 = new ArrayList<Level>();
		levelList1.add(level1);
		series1.setLevels(levelList1);
		
		testService.saveOrUpdateSeries(series1);
		
//--Series2--End
	}

}
