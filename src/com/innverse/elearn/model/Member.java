package com.innverse.elearn.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.innverse.elearn.eenum.MemberRole;


@Entity
@Table(name = "member")
public class Member {
	
	@Id
	@GeneratedValue
	@Column(name="member_id")
	private long memberId;
	
	@OneToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "account_id")
	private UserProfile account;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "organization_id")
	private Organization organization;
	
	@ManyToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER, mappedBy ="members")
	private Set<Group> groups;
	
	private MemberRole memberRole;
	
	private Date registrationDate;
	
	private String lastLoginTime;
	
	private boolean isDefaultMemberOfOrganization;
	
	private boolean active;

	public long getMemberId() {
		return memberId;
	}

	public void setMemberId(long memberId) {
		this.memberId = memberId;
	}

	public UserProfile getAccount() {
		return account;
	}

	public void setAccount(UserProfile account) {
		this.account = account;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public MemberRole getMemberRole() {
		return memberRole;
	}

	public void setMemberRole(MemberRole memberRole) {
		this.memberRole = memberRole;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Set<Group> getGroups() {
		return groups;
	}

	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}
	
	public void addGroup(Group group){
		if(this.groups == null){
			this.groups = new HashSet<Group>();
		}
		this.groups.add(group);
	}

	public boolean isDefaultMemberOfOrganization() {
		return isDefaultMemberOfOrganization;
	}

	public void setDefaultMemberOfOrganization(boolean isDefaultMemberOfOrganization) {
		this.isDefaultMemberOfOrganization = isDefaultMemberOfOrganization;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
}
