package com.innverse.elearn.json;

import java.util.List;

public class SaveUserQueAns {

	private String userId;
	
	private String levelId;
	
	private String seriesId;
	
	private List<QuestionAnswer> result = null;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getLevelId() {
		return levelId;
	}

	public void setLevelId(String levelId) {
		this.levelId = levelId;
	}

	public String getSeriesId() {
		return seriesId;
	}

	public void setSeriesId(String seriesId) {
		this.seriesId = seriesId;
	}

	public List<QuestionAnswer> getResult() {
		return result;
	}

	public void setResult(List<QuestionAnswer> result) {
		this.result = result;
	}
	
}
