package com.innverse.elearn.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.innverse.elearn.model.Feature;
import com.innverse.elearn.model.FeatureAccessLevel;

public class FillterNewFeature {

	public static List<Feature> getNewFilterFeatureList(Set<FeatureAccessLevel> oldFeatureList,List<Feature> newFeatureList){
		List<Feature> tmpFeatureList = new ArrayList<Feature>();
		boolean isExist = false;
		for(Feature newFeature : newFeatureList){
			isExist = false;
			for(FeatureAccessLevel oldFAL : oldFeatureList){
				Feature oldFeature = oldFAL.getFeature();
				if(oldFeature.getId() == newFeature.getId()){
					isExist = true;
				}
			}
			if(!isExist){
				tmpFeatureList.add(newFeature);
			}
		}
		return tmpFeatureList;
	}
}
