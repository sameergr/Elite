package com.innverse.elearn.pojo;

import java.util.ArrayList;
import java.util.List;

public class CategoryMap{
	private String category;
	private List<CategoryData> categoryList = new ArrayList<CategoryData>();
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public List<CategoryData> getCategoryList() {
		return categoryList;
	}
	public void setCategoryList(List<CategoryData> categoryList) {
		this.categoryList = categoryList;
	}
}