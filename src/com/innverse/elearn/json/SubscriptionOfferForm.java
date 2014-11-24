package com.innverse.elearn.json;

import com.innverse.elearn.eenum.RegistrationType;
import com.innverse.elearn.eenum.OfferType;
import com.innverse.elearn.eenum.PlanType;

public class SubscriptionOfferForm {

	private OfferType offerType;
	
	private RegistrationType registrationType;
	
	private PlanType planType;
	
	private String offerImageURL;
	
	private String offerCode;
	
	private String expiryDate;
	
	private String offerAmount;
	
	private String description;
	
	private int planTime;

	public OfferType getOfferType() {
		return offerType;
	}

	public void setOfferType(OfferType offerType) {
		this.offerType = offerType;
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

	public String getOfferImageURL() {
		return offerImageURL;
	}

	public void setOfferImageURL(String offerImageURL) {
		this.offerImageURL = offerImageURL;
	}

	public String getOfferCode() {
		return offerCode;
	}

	public void setOfferCode(String offerCode) {
		this.offerCode = offerCode;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getOfferAmount() {
		return offerAmount;
	}

	public void setOfferAmount(String offerAmount) {
		this.offerAmount = offerAmount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPlanTime() {
		return planTime;
	}

	public void setPlanTime(int planTime) {
		this.planTime = planTime;
	}
	
}
