package com.innverse.elearn.eenum;



public enum CategoryType {
	Category(0,"Category"),SubCategory(1,"SubCategory");
	
	private int code;
	private String desc;
	
	
	
	CategoryType(int code,String desc){
		this.code=code;
		this.desc=desc;
	}
	
	public int getCode() {
		return code;
	}
	public String getDesc() {
		return desc;
	}
	
	public CategoryType getCategoryType(int code){
		CategoryType[] values = CategoryType.values();
		for(CategoryType CategoryTypeStatus : values){
			if(CategoryTypeStatus.code == code){
				return CategoryTypeStatus;
			}
		}
		return null;
	}

}
