package com.innverse.elearn.json;

public class CheckoutForm {
	
	private String planName;
	
	private double amount;
	
	private String username;
	
	private double tax;
	
	private String paymentgateway;
	
	private String currencycode;

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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public double getTax() {
		return tax;
	}

	public void setTax(double tax) {
		this.tax = tax;
	}

	public String getPaymentgateway() {
		return paymentgateway;
	}

	public void setPaymentgateway(String paymentgateway) {
		this.paymentgateway = paymentgateway;
	}

	public String getCurrencycode() {
		return currencycode;
	}

	public void setCurrencycode(String currencycode) {
		this.currencycode = currencycode;
	}

}
