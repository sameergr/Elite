package com.innverse.elearn.json;

import java.util.List;


public class GenrateAutoSeriesForm {
	
	private String seriesName;
	
	private String categoryId;
	
	private List<LevelForm> levels;

	public String getSeriesName() {
		return seriesName;
	}

	public void setSeriesName(String seriesName) {
		this.seriesName = seriesName;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public List<LevelForm> getLevels() {
		return levels;
	}

	public void setLevels(List<LevelForm> levels) {
		this.levels = levels;
	}
	
}
