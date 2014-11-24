package com.innverse.elearn.json;

import java.util.ArrayList;
import java.util.List;

import com.innverse.elearn.eenum.FeatureType;
import com.innverse.elearn.model.Feature;
import com.innverse.elearn.model.FeatureAccessLevel;
import com.innverse.elearn.model.SubscriptionPlan;

public class EditSubscriptionForm {
	
	private List<FeatureAccessLevelForm> accessLevels;
	
	private List<FeatureAccessLevelForm> availableFeatures;
	
	private FeatureType featureType;
	
	private List<String> value;
	
	private List<Feature> featureList;
	

	public void addPlan(SubscriptionPlan plan){
		accessLevels = new ArrayList<FeatureAccessLevelForm>();
		
		for(FeatureAccessLevel FAL : plan.getFeatureAccessLevelList()){
			FeatureAccessLevelForm form = new FeatureAccessLevelForm();
			form.setFeatureType(FAL.getFeatureType());
			form.setValue(FAL.getValue());
			form.setFeatureid(String.valueOf(FAL.getFeature().getId()));
			form.setFeatureName(FAL.getFeature().getFeatureName());
			
			accessLevels.add(form);
			
		}
	}
	
	public void addFeatures(List<Feature> featureList){
		availableFeatures = new ArrayList<FeatureAccessLevelForm>();
		
		for(Feature f : featureList){
			FeatureAccessLevelForm form = new FeatureAccessLevelForm();
			form.setFeatureid(String.valueOf(f.getId()));
			form.setFeatureName(f.getFeatureName());
			form.setDescription(f.getDescription());
			form.setViewType(f.getViewType());
			form.setFeatureType(getFeatureType());
			availableFeatures.add(form);
		}
	}
	
	
	public List<FeatureAccessLevelForm> getAccessLevels() {
		return accessLevels;
	}

	public void setAccessLevels(List<FeatureAccessLevelForm> accessLevels) {
		this.accessLevels = accessLevels;
	}

	public List<String> getValue() {
		return value;
	}

	public void setValue(List<String> value) {
		this.value = value;
	}


	public List<Feature> getFeatureList() {
		return featureList;
	}


	public void setFeatureList(List<Feature> featureList) {
		this.featureList = featureList;
	}

	public List<FeatureAccessLevelForm> getAvailableFeatures() {
		return availableFeatures;
	}

	public void setAvailableFeatures(List<FeatureAccessLevelForm> availableFeatures) {
		this.availableFeatures = availableFeatures;
	}

	public FeatureType getFeatureType() {
		return featureType;
	}

	public void setFeatureType(FeatureType featureType) {
		this.featureType = featureType;
	}
	
}
