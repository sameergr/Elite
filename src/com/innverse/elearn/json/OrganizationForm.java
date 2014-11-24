package com.innverse.elearn.json;

import com.innverse.elearn.eenum.RegistrationType;

public class OrganizationForm {

	private String orgName;
	
	private String website;
	
	private String aboutOrganization;
	
	private String tagLine;
	
	private String email;
	
	private String password;
	
	private String planName;


	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getAboutOrganization() {
		return aboutOrganization;
	}

	public void setAboutOrganization(String aboutOrganization) {
		this.aboutOrganization = aboutOrganization;
	}

	public String getTagLine() {
		return tagLine;
	}

	public void setTagLine(String tagLine) {
		this.tagLine = tagLine;
	}

	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

}
