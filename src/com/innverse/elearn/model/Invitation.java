package com.innverse.elearn.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "invitation")
public class Invitation {
	
	@Id
	@GeneratedValue
	private long id;
	
	private long inviteeId;
	
	private String friendEmailId;
	
	private String token;
	
	private String valid;
	
	private boolean active;
	
	private String inviteBy;
	
	private String result;
	
	private long organizationId;

	public String getInviteBy() {
		return inviteBy;
	}

	public void setInviteBy(String inviteBy) {
		this.inviteBy = inviteBy;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getInviteeId() {
		return inviteeId;
	}

	public void setInviteeId(long inviteeId) {
		this.inviteeId = inviteeId;
	}

	public String getFriendEmailId() {
		return friendEmailId;
	}

	public void setFriendEmailId(String friendEmailId) {
		this.friendEmailId = friendEmailId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getValid() {
		return valid;
	}

	public void setValid(String valid) {
		this.valid = valid;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(long organizationId) {
		this.organizationId = organizationId;
	}

	
}
