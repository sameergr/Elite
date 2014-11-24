package com.innverse.elearn.pojo;

public class Category {

	private Long catId;
	private Long subCatId;
	private String category;
	private String subCategory;
	
	public Category(Long catId, Long subCatId, String category,String subCategory){
		this.catId = catId;
		this.subCatId = subCatId;
		this.category = category;
		this.subCategory = subCategory;
	}

	public Long getCatId() {
		return catId;
	}

	public void setCatId(Long catId) {
		this.catId = catId;
	}

	public Long getSubCatId() {
		return subCatId;
	}

	public void setSubCatId(Long subCatId) {
		this.subCatId = subCatId;
	}



	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}
	
}
