package com.innverse.elearn.model;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "groups")
public class Group {
	
	@Id
	@GeneratedValue
	private long id;
	
	private String groupName;
	
	@ManyToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinTable(name = "member_groups",
    joinColumns =
    @JoinColumn(name = "groups_id", nullable = false, referencedColumnName="id"),
    inverseJoinColumns =
    @JoinColumn(name = "member_id", nullable = false, referencedColumnName="member_id"))
	private Set<Member> members;
	
	private boolean active;
	
	private String author;
	
	private Date createdOn;
	
	@OneToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "organization_id")
	private Organization createby;
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Organization getCreateby() {
		return createby;
	}

	public void setCreateby(Organization createby) {
		this.createby = createby;
	}

	public Set<Member> getMembers() {
		return members;
	}

	public void setMembers(Set<Member> members) {
		this.members = members;
	}

	public void addMember(Member m){
		if(this.members == null){
			this.members = new HashSet<Member>();
		}
		this.members.add(m);
	}
	
	public void removeMember(Member m){
		if(members != null){
			members.remove(m);
		}
	}
	
	public void removeAllMembers(){
		if(members != null){
			members.clear();
			members = null;
		}
	}
	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	

}
