package com.innverse.elearn.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.innverse.elearn.eenum.RegistrationStatus;

@Entity
@Table(name="organization",uniqueConstraints=
    @UniqueConstraint(columnNames = {"name"}))
public class Organization {

	@Id
	@GeneratedValue
	@Column(name="organization_id")
	private long id;
	
	private String name;
	
	private RegistrationStatus registrationStatus;
	
	private String website;
	
	private String logoURL;
	
	private String aboutOrganization;
	
	private String tagLine;
	
	private boolean approved;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "workprofile_id")
	private WorkProfile workProfile;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "address_id")
	private Address currentAddress;

	private boolean active;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "subscription_id")
	private Subscription subscription;
	
	@OneToOne(mappedBy="organization")
	private UserProfile userProfile;

	@OneToMany(cascade=CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name="organization_id")
	private Set<MemberFALPermission> memberFALPermissions;

	private String privacyPolicy;
	
	private String organizationLogo; 
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public WorkProfile getWorkProfile() {
		return workProfile;
	}

	public void setWorkProfile(WorkProfile workProfile) {
		this.workProfile = workProfile;
	}

	public Address getCurrentAddress() {
		return currentAddress;
	}

	public void setCurrentAddress(Address currentAddress) {
		this.currentAddress = currentAddress;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Subscription getSubscription() {
		return subscription;
	}

	public void setSubscription(Subscription subscription) {
		this.subscription = subscription;
	}

	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}

	public String getPrivacyPolicy() {
		return privacyPolicy;
	}

	public void setPrivacyPolicy(String privacyPolicy) {
		this.privacyPolicy = privacyPolicy;
	}

	public String getLogoURL() {
		return logoURL;
	}

	public void setLogoURL(String logoURL) {
		this.logoURL = logoURL;
	}

	public Set<MemberFALPermission> getMemberFALPermissions() {
		return memberFALPermissions;
	}

	public void setMemberFALPermissions(Set<MemberFALPermission> memberFALPermissions) {
		this.memberFALPermissions = memberFALPermissions;
	}

	public String getOrganizationLogo() {
		return organizationLogo;
	}

	public void setOrganizationLogo(String organizationLogo) {
		this.organizationLogo = organizationLogo;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public RegistrationStatus getRegistrationStatus() {
		return registrationStatus;
	}

	public void setRegistrationStatus(RegistrationStatus registrationStatus) {
		this.registrationStatus = registrationStatus;
	}

}
