package com.innverse.elearn.json;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.innverse.elearn.model.Address;
import com.innverse.elearn.model.Organization;
import com.innverse.elearn.model.WorkProfile;

public class UserForm {

	private String website;
	
	private String username;
	
	private String password;
	
	private String phone;
	
	private String email;
	
	private String firstName;
	
	private String lastName;
	
	private String subject;
	
	private String aboutYou;
	
	private String howDoYouKnowAboutSite;
	
	private String tagLine;
	
	private String country;
	
	private String street;
	
	private String city;
	
	private String landmark;
	
	private String zipCode;
	
	private String registeredAs;
	
	private Organization organization;
	
	private String name;
	
	private String aboutOrganization;
	
	private String inviteeEmailAddress;
	
	private List<WorkProfile> profile;
	
	private long plan;
	
	private String regType;
	
	private String organizationId;
	
	private String token;
	
	private String address;
	
	private String endDate;
	
	private String organizationName;
	
	private String oName;
	
	private String role;
	
	private String startDate;
	
	private String organizationCity;
	
	private String logoURL;
	
	private String previousOrgName;
	
	private boolean isInvited;
	
	private long groupList;
	
	private long seriesList;
	
	private long courseList;
	
	private MultipartFile logo;
	
	public MultipartFile getLogo() {
		return logo;
	}

	public void setLogo(MultipartFile logo) {
		this.logo = logo;
	}

	public boolean isInvited() {
		return isInvited;
	}

	public void setInvited(boolean isInvited) {
		this.isInvited = isInvited;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getAboutYou() {
		return aboutYou;
	}

	public void setAboutYou(String aboutYou) {
		this.aboutYou = aboutYou;
	}

	public String getHowDoYouKnowAboutSite() {
		return howDoYouKnowAboutSite;
	}

	public void setHowDoYouKnowAboutSite(String howDoYouKnowAboutSite) {
		this.howDoYouKnowAboutSite = howDoYouKnowAboutSite;
	}

	public String getTagLine() {
		return tagLine;
	}

	public void setTagLine(String tagLine) {
		this.tagLine = tagLine;
	}


	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}


	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getRegisteredAs() {
		return registeredAs;
	}

	public void setRegisteredAs(String registeredAs) {
		this.registeredAs = registeredAs;
	}

	public List<WorkProfile> getProfile() {
		return profile;
	}

	public void setProfile(List<WorkProfile> profile) {
		this.profile = profile;
	}


	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAboutOrganization() {
		return aboutOrganization;
	}

	public void setAboutOrganization(String aboutOrganization) {
		this.aboutOrganization = aboutOrganization;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getInviteeEmailAddress() {
		return inviteeEmailAddress;
	}

	public void setInviteeEmailAddress(String inviteeEmailAddress) {
		this.inviteeEmailAddress = inviteeEmailAddress;
	}

	public long getPlan() {
		return plan;
	}

	public void setPlan(long plan) {
		this.plan = plan;
	}

	public String getRegType() {
		return regType;
	}

	public void setRegType(String regType) {
		this.regType = regType;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getOrganizationCity() {
		return organizationCity;
	}

	public void setOrganizationCity(String organizationCity) {
		this.organizationCity = organizationCity;
	}

	public String getLogoURL() {
		return logoURL;
	}

	public void setLogoURL(String logoURL) {
		this.logoURL = logoURL;
	}

	public String getoName() {
		return oName;
	}

	public void setoName(String oName) {
		this.oName = oName;
	}

	public String getPreviousOrgName() {
		return previousOrgName;
	}

	public void setPreviousOrgName(String previousOrgName) {
		this.previousOrgName = previousOrgName;
	}

	public long getGroupList() {
		return groupList;
	}

	public void setGroupList(long groupList) {
		this.groupList = groupList;
	}

	public long getSeriesList() {
		return seriesList;
	}

	public void setSeriesList(long seriesList) {
		this.seriesList = seriesList;
	}

	public long getCourseList() {
		return courseList;
	}

	public void setCourseList(long courseList) {
		this.courseList = courseList;
	}
	
}
