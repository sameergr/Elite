package com.innverse.elearn.json;

import java.util.List;

import com.innverse.elearn.model.Level;
import com.innverse.elearn.model.Organization;
import com.innverse.elearn.model.Question;

public class SeriesForm {
	
	private long seriesId;

	private String subCategoryId;
	
	private String seriesname;
	
	private List<Level> levelname;
	
	private List<Question> questions;
	
	private String answers;
	
	private String chkvalues;
	
	private String oneQuestionTime;
	
	private String totalQuestionTime;
	

	public String getSubCategoryId() {
		return subCategoryId;
	}

	public void setSubCategoryId(String subCategoryId) {
		this.subCategoryId = subCategoryId;
	}

	public String getSeriesname() {
		return seriesname;
	}

	public void setSeriesname(String seriesname) {
		this.seriesname = seriesname;
	}

	public long getSeriesId() {
		return seriesId;
	}

	public void setSeriesId(long seriesId) {
		this.seriesId = seriesId;
	}

	public List<Level> getLevelname() {
		return levelname;
	}

	public void setLevelname(List<Level> levelname) {
		this.levelname = levelname;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public String getAnswers() {
		return answers;
	}

	public void setAnswers(String answers) {
		this.answers = answers;
	}

	public String getChkvalues() {
		return chkvalues;
	}

	public void setChkvalues(String chkvalues) {
		this.chkvalues = chkvalues;
	}

	public String getOneQuestionTime() {
		return oneQuestionTime;
	}

	public void setOneQuestionTime(String oneQuestionTime) {
		this.oneQuestionTime = oneQuestionTime;
	}

	public String getTotalQuestionTime() {
		return totalQuestionTime;
	}

	public void setTotalQuestionTime(String totalQuestionTime) {
		this.totalQuestionTime = totalQuestionTime;
	}
	
}
