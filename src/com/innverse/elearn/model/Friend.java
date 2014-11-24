package com.innverse.elearn.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "friend")
public class Friend {

	@Id
	@GeneratedValue
	private long id;
	
	@OneToOne
	@Cascade({CascadeType.SAVE_UPDATE})
	private Member member;
	
	@OneToOne
	@Cascade({CascadeType.SAVE_UPDATE})
	private Organization organization;
	
	@OneToOne
	@Cascade({CascadeType.SAVE_UPDATE})
	private Member friend;
	
	private boolean follow;
	
	private boolean	approved;
	
	private boolean active;
	
	private boolean deleted;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public Organization getOrganization() {
		return organization;
	}
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	public Member getFriend() {
		return friend;
	}
	public void setFriend(Member friend) {
		this.friend = friend;
	}
	public boolean isApproved() {
		return approved;
	}
	public void setApproved(boolean approved) {
		this.approved = approved;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	public boolean isFollow() {
		return follow;
	}
	public void setFollow(boolean follow) {
		this.follow = follow;
	}
	
}
