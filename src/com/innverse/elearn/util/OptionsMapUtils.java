package com.innverse.elearn.util;

import java.util.LinkedHashMap;

import com.innverse.elearn.eenum.CategoryType;
import com.innverse.elearn.eenum.PlanType;

public class OptionsMapUtils {

	public static LinkedHashMap<String, String> getPlanCategory(){
		LinkedHashMap<String, String> planCategoryMap = new LinkedHashMap<String, String>();
		for(PlanType plan : PlanType.values()){
			planCategoryMap.put(String.valueOf(plan.getCode()), plan.getMessage());
		}
		return planCategoryMap;
	}
	
	
	public static LinkedHashMap<String, String> getCategory(){
		LinkedHashMap<String, String> categoryMap = new LinkedHashMap<String, String>();
		for(CategoryType category : CategoryType.values()){
			categoryMap.put(String.valueOf(category.getCode()), category.getDesc());
		}
		return categoryMap;
	}
}                             	
