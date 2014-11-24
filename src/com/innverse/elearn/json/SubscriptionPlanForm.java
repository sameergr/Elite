package com.innverse.elearn.json;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.innverse.elearn.eenum.FeatureType;
import com.innverse.elearn.eenum.RegistrationType;
import com.innverse.elearn.eenum.PlanType;
import com.innverse.elearn.eenum.SubscriptionMode;
import com.innverse.elearn.eenum.SubscriptionType;
import com.innverse.elearn.model.Feature;

public class SubscriptionPlanForm {
	
	private String planName;

	private double amount;
	
	private String planDescription;
	
	private Date creationTime;
	
	private String authorName;
	
	private List<String> featureId;
	
	private RegistrationType registrationType;
	
	private SubscriptionType subscriptionType;
	
	private SubscriptionMode subscriptionMode;
	
	private String checkDefault;
	
	private PlanType planType;
	
	private FeatureType featureType;
	
	private List<FeatureAccessLevelForm> accessLevels;
	
	private String expirydate;

	public SubscriptionMode getSubscriptionMode() {
		return subscriptionMode;
	}

	public void setSubscriptionMode(SubscriptionMode subscriptionMode) {
		this.subscriptionMode = subscriptionMode;
	}

	public String getCheckDefault() {
		return checkDefault;
	}

	public void setCheckDefault(String checkDefault) {
		this.checkDefault = checkDefault;
	}

	public List<String> getFeatureId() {
		return featureId;
	}

	public void setFeatureId(List<String> featureId) {
		this.featureId = featureId;
	}

	public RegistrationType getRegistrationType() {
		return registrationType;
	}

	public void setRegistrationType(RegistrationType registrationType) {
		this.registrationType = registrationType;
	}

	public PlanType getPlanType() {
		return planType;
	}

	public void setPlanType(PlanType planType) {
		this.planType = planType;
	}


	public SubscriptionType getSubscriptionType() {
		return subscriptionType;
	}

	public void setSubscriptionType(SubscriptionType subscriptionType) {
		this.subscriptionType = subscriptionType;
	}
	
	public void addFeatureList(List<Feature> featureList){
		accessLevels = new ArrayList<FeatureAccessLevelForm>();
		for(Feature feature : featureList){
			FeatureAccessLevelForm accessLevel = new FeatureAccessLevelForm();
			accessLevel.setFeatureid( String.valueOf(feature.getId()));
			accessLevel.setFeatureName(feature.getFeatureName());
			accessLevels.add(accessLevel);
		}
	}

	public List<FeatureAccessLevelForm> getAccessLevels() {
		return accessLevels;
	}

	public void setAccessLevels(List<FeatureAccessLevelForm> accessLevels) {
		this.accessLevels = accessLevels;
	}

	public FeatureType getFeatureType() {
		return featureType;
	}

	public void setFeatureType(FeatureType featureType) {
		this.featureType = featureType;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getPlanDescription() {
		return planDescription;
	}

	public void setPlanDescription(String planDescription) {
		this.planDescription = planDescription;
	}


	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getExpirydate() {
		return expirydate;
	}

	public void setExpirydate(String expirydate) {
		this.expirydate = expirydate;
	}

	
}
