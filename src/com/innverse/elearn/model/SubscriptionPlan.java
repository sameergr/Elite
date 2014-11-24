package com.innverse.elearn.model;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.innverse.elearn.eenum.PlanType;
import com.innverse.elearn.eenum.RegistrationType;
import com.innverse.elearn.eenum.RoleType;
import com.innverse.elearn.eenum.SubscriptionMode;
import com.innverse.elearn.eenum.SubscriptionType;

@Entity
@Table(name="subscription_plan")
public class SubscriptionPlan {

	@Id
	@GeneratedValue
	private long id;
	
	private String planName;

	private double amount;
	
	private RegistrationType registrationType;
	
	private PlanType planType;
	
	private boolean active;
	
	private String planDescription;
	
	private Date creationTime;
	
	@OneToMany(cascade=CascadeType.ALL,fetch = FetchType.EAGER,mappedBy="subscriptionPlan")
	private Set<Subscription> subscriptions;
	
	@OneToMany(cascade=CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name="subscription_plan_id")
	private Set<FeatureAccessLevel> featureAccessLevelList;

	@OneToOne(cascade=CascadeType.DETACH)
	@JoinColumn(name = "createby_account_id")
	private UserProfile createdBy;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "approvedby_account_id")
	private UserProfile approvedBy;
	
	private RoleType authorRoleType;
	
	private String authorName;
	
	private SubscriptionType subscriptionType;
	
	private SubscriptionMode subscriptionMode;
	
	private boolean checkDefault;
	
	private Date expiryDate;	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public PlanType getPlanType() {
		return planType;
	}

	public void setPlanType(PlanType planType) {
		this.planType = planType;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
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

	public UserProfile getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(UserProfile createdBy) {
		this.createdBy = createdBy;
	}

	public RoleType getAuthorRoleType() {
		return authorRoleType;
	}

	public void setAuthorRoleType(RoleType authorRoleType) {
		this.authorRoleType = authorRoleType;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public SubscriptionType getSubscriptionType() {
		return subscriptionType;
	}

	public void setSubscriptionType(SubscriptionType subscriptionType) {
		this.subscriptionType = subscriptionType;
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

	public RegistrationType getRegistrationType() {
		return registrationType;
	}

	public void setRegistrationType(RegistrationType registrationType) {
		this.registrationType = registrationType;
	}

	public boolean isCheckDefault() {
		return checkDefault;
	}

	public void setCheckDefault(boolean checkDefault) {
		this.checkDefault = checkDefault;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Set<FeatureAccessLevel> getFeatureAccessLevelList() {
		return featureAccessLevelList;
	}

	public void setFeatureAccessLevelList(Set<FeatureAccessLevel> featureAccessLevelList) {
		this.featureAccessLevelList = featureAccessLevelList;
	}

	public Set<Subscription> getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(Set<Subscription> subscriptions) {
		this.subscriptions = subscriptions;
	}

}
