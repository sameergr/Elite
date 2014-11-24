package com.innverse.elearn.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.innverse.elearn.pojo.CategoryData;
import com.innverse.elearn.pojo.CategoryMap;

public class CategoryMapUtil {

	private static HashMap<String, List<CategoryMap>> categoryMap = new HashMap<String,List<CategoryMap>>();
	public static HashMap<String,List<CategoryMap>> getCategoryMap(List<CategoryData> catagoriesList){
		categoryMap.clear();
		boolean firstValue = true;
		boolean newCategory = true;
		long oldCatId = 0;
		for(CategoryData category : catagoriesList){
			char c = category.getCategory().charAt(0);
			String cStr = String.valueOf(c);
			if(!categoryMap.containsKey(cStr)){
				List<CategoryMap> catMapList = new ArrayList<CategoryMap>();
				categoryMap.put(cStr, catMapList);
				firstValue = true;
			}
			
			if(firstValue){
				List<CategoryMap> catMapList = categoryMap.get(cStr);

				CategoryMap map = new CategoryMap();
				List<CategoryData> catList = new ArrayList<CategoryData>();
				catList.add(category);
				map.setCategory(category.getCategory());
				map.setCategoryList(catList);
				
				catMapList.add(map);
				firstValue = false;
				oldCatId = category.getCatId();
			}else{
				if(oldCatId == category.getCatId()){
					List<CategoryMap> catMapList = categoryMap.get(cStr);
					CategoryMap map = catMapList.get(catMapList.size() -1);
					map.getCategoryList().add(category);
				}else{
					List<CategoryMap> catMapList = categoryMap.get(cStr);
					
					CategoryMap map = new CategoryMap();
					List<CategoryData> catList = new ArrayList<CategoryData>();
					catList.add(category);
					map.setCategory(category.getCategory());
					map.setCategoryList(catList);
					
					catMapList.add(map);
				}
				oldCatId = category.getCatId();
			}
		}
		return categoryMap;
	}
}

