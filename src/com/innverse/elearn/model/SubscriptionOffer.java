package com.innverse.elearn.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.innverse.elearn.eenum.OfferType;
import com.innverse.elearn.eenum.PlanType;
import com.innverse.elearn.eenum.RegistrationType;
import com.innverse.elearn.eenum.SubscriptionMode;

@Entity
@Table(name="subscription_offer")
public class SubscriptionOffer {

	@Id
	@GeneratedValue
	private long id;
	
	private String code;
	
	private String description;
	
	private String adImageURL;
	
	private String offerAmount;
	
	private PlanType planType;
	
	private int planTime;
	
	private RegistrationType registrationType;
	
	private Date creationTime;
	
	private boolean valid;
	
	private Date availableTillDate;
	
	private OfferType offerType;
	 
	private Date expiryDate;
	
	private boolean checkDefault;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "createby_account_id")
	private UserProfile createdBy;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "approvedby_account_id")
	private UserProfile approvedBy;
	
	private SubscriptionMode subscriptionMode;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAdImageURL() {
		return adImageURL;
	}

	public void setAdImageURL(String adImageURL) {
		this.adImageURL = adImageURL;
	}

	public String getOfferAmount() {
		return offerAmount;
	}

	public void setOfferAmount(String offerAmount) {
		this.offerAmount = offerAmount;
	}

	public PlanType getPlanType() {
		return planType;
	}

	public void setPlanType(PlanType planType) {
		this.planType = planType;
	}

	public RegistrationType getRegistrationType() {
		return registrationType;
	}

	public void setRegistrationType(RegistrationType registrationType) {
		this.registrationType = registrationType;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public Date getAvailableTillDate() {
		return availableTillDate;
	}

	public void setAvailableTillDate(Date availableTillDate) {
		this.availableTillDate = availableTillDate;
	}

	public OfferType getOfferType() {
		return offerType;
	}

	public void setOfferType(OfferType offerType) {
		this.offerType = offerType;
	}

	public int getPlanTime() {
		return planTime;
	}

	public void setPlanTime(int planTime) {
		this.planTime = planTime;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public boolean isCheckDefault() {
		return checkDefault;
	}

	public void setCheckDefault(boolean checkDefault) {
		this.checkDefault = checkDefault;
	}

	public UserProfile getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(UserProfile createdBy) {
		this.createdBy = createdBy;
	}

	public UserProfile getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(UserProfile approvedBy) {
		this.approvedBy = approvedBy;
	}

	public SubscriptionMode getSubscriptionMode() {
		return subscriptionMode;
	}

	public void setSubscriptionMode(SubscriptionMode subscriptionMode) {
		this.subscriptionMode = subscriptionMode;
	}
	
}
