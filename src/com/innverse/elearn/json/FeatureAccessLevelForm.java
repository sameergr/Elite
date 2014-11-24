package com.innverse.elearn.json;

import com.innverse.elearn.eenum.FeatureType;
import com.innverse.elearn.eenum.ViewType;

public class FeatureAccessLevelForm {

	private String featureid;
	
	private String featureName;
	
	private FeatureType featureType;
	
	private String value;
	
	private String description;
	
	private ViewType viewType;

	public String getFeatureid() {
		return featureid;
	}

	public void setFeatureid(String featureid) {
		this.featureid = featureid;
	}

	public FeatureType getFeatureType() {
		return featureType;
	}

	public void setFeatureType(FeatureType featureType) {
		this.featureType = featureType;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getFeatureName() {
		return featureName;
	}

	public void setFeatureName(String featureName) {
		this.featureName = featureName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ViewType getViewType() {
		return viewType;
	}

	public void setViewType(ViewType viewType) {
		this.viewType = viewType;
	}

}
