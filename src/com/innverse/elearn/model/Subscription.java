package com.innverse.elearn.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="subscription")
public class Subscription {

	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "subscription_plan_id")
	private SubscriptionPlan subscriptionPlan;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "subscription_offer_id")
	private SubscriptionOffer subscriptionOffer;
	 
	private String nextRenewalDate;
	
	private double renewalAmount;
	
	private double lastPayedAmount;
	
	private double balanceAmount;
	
	private double currentPayedAmount;
	
	private long organizationId;
	
	private long accountId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public SubscriptionPlan getSubscriptionPlan() {
		return subscriptionPlan;
	}

	public void setSubscriptionPlan(SubscriptionPlan subscriptionPlan) {
		this.subscriptionPlan = subscriptionPlan;
	}

	public SubscriptionOffer getSubscriptionOffer() {
		return subscriptionOffer;
	}

	public void setSubscriptionOffer(SubscriptionOffer subscriptionOffer) {
		this.subscriptionOffer = subscriptionOffer;
	}

	public String getNextRenewalDate() {
		return nextRenewalDate;
	}

	public void setNextRenewalDate(String nextRenewalDate) {
		this.nextRenewalDate = nextRenewalDate;
	}

	public double getRenewalAmount() {
		return renewalAmount;
	}

	public void setRenewalAmount(double renewalAmount) {
		this.renewalAmount = renewalAmount;
	}

	public double getLastPayedAmount() {
		return lastPayedAmount;
	}

	public void setLastPayedAmount(double lastPayedAmount) {
		this.lastPayedAmount = lastPayedAmount;
	}

	public double getBalanceAmount() {
		return balanceAmount;
	}

	public void setBalanceAmount(double balanceAmount) {
		this.balanceAmount = balanceAmount;
	}

	public double getCurrentPayedAmount() {
		return currentPayedAmount;
	}

	public void setCurrentPayedAmount(double currentPayedAmount) {
		this.currentPayedAmount = currentPayedAmount;
	}

	public long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(long organizationId) {
		this.organizationId = organizationId;
	}

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}
	
	
}
