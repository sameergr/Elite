package com.innverse.elearn.json;

import java.util.ArrayList;
import java.util.List;

import com.innverse.elearn.eenum.FeatureType;
import com.innverse.elearn.eenum.MemberRole;
import com.innverse.elearn.model.Feature;

public class CreateNewRoleForm {
	
	private MemberRole memberRole;
	
	private String roleName;
	
	private List<FeatureAccessLevelForm> accessLevels;
	
	private List<String> featureId;
	
	private List<String> limit;
	
	private FeatureType featureType;
	
	public void addFeature(List<Feature> featureList){
		accessLevels = new ArrayList<FeatureAccessLevelForm>();
		for(Feature feature : featureList){
			FeatureAccessLevelForm accLevelForm = new FeatureAccessLevelForm();
			accLevelForm.setFeatureName(feature.getFeatureName());
			accLevelForm.setFeatureid(String.valueOf(feature.getId()));
			accessLevels.add(accLevelForm);
		}
	}
	
	public MemberRole getMemberRole() {
		return memberRole;
	}

	public void setMemberRole(MemberRole memberRole) {
		this.memberRole = memberRole;
	}

	public List<FeatureAccessLevelForm> getAccessLevels() {
		return accessLevels;
	}

	public void setAccessLevels(List<FeatureAccessLevelForm> accessLevels) {
		this.accessLevels = accessLevels;
	}

	public List<String> getFeatureId() {
		return featureId;
	}

	public void setFeatureId(List<String> featureId) {
		this.featureId = featureId;
	}

	public List<String> getLimit() {
		return limit;
	}

	public void setLimit(List<String> limit) {
		this.limit = limit;
	}

	public FeatureType getFeatureType() {
		return featureType;
	}

	public void setFeatureType(FeatureType featureType) {
		this.featureType = featureType;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
}
