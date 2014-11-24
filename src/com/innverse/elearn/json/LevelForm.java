package com.innverse.elearn.json;

import java.util.List;

public class LevelForm {

	private String categoryId;
	
	private String seriesId;
	
	private List<String> questionIds;
	
	private String levelname;
	
	private String proficiency;
	
	private String totatlQuestion;

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getSeriesId() {
		return seriesId;
	}

	public void setSeriesId(String seriesId) {
		this.seriesId = seriesId;
	}

	public List<String> getQuestionIds() {
		return questionIds;
	}

	public void setQuestionIds(List<String> questionIds) {
		this.questionIds = questionIds;
	}

	public String getLevelname() {
		return levelname;
	}

	public void setLevelname(String levelname) {
		this.levelname = levelname;
	}

	public String getProficiency() {
		return proficiency;
	}

	public void setProficiency(String proficiency) {
		this.proficiency = proficiency;
	}

	public String getTotatlQuestion() {
		return totatlQuestion;
	}

	public void setTotatlQuestion(String totatlQuestion) {
		this.totatlQuestion = totatlQuestion;
	}

}
