package com.innverse.elearn.model;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.innverse.elearn.eenum.MemberRole;

@Entity
@Table(name = "member_fal_permission")
public class MemberFALPermission {

	@Id
	@GeneratedValue
	@Column(name="id")
	private long id;

	private MemberRole memberRole;
	
	@ManyToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinTable(name = "memberfalpermission_fal_join",
    joinColumns =
    @JoinColumn(name = "memberfalpermission_id", nullable = false),
    inverseJoinColumns =
    @JoinColumn(name = "fal_id", nullable = false))
	private Set<FeatureAccessLevel> featureAccessLevelList;

	@OneToOne(cascade=CascadeType.ALL,fetch = FetchType.EAGER)
	private Permission memberPermission;
	

	private boolean active;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public MemberRole getMemberRole() {
		return memberRole;
	}

	public void setMemberRole(MemberRole memberRole) {
		this.memberRole = memberRole;
	}

	public Set<FeatureAccessLevel> getFeatureAccessLevelList() {
		return featureAccessLevelList;
	}

	public void setFeatureAccessLevelList(Set<FeatureAccessLevel> featureAccessLevelList) {
		this.featureAccessLevelList = featureAccessLevelList;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Permission getMemberPermission() {
		return memberPermission;
	}

	public void setMemberPermission(Permission memberPermission) {
		this.memberPermission = memberPermission;
	}
	
}
