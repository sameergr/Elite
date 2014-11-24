package com.innverse.elearn.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="learning_courses")
public class Course {

	@Id
	@GeneratedValue
	@Column(name="id")
	private long id;
	
	private String courseName;
	
	private String couserDescription;
	
	private Date publishDate;
	
	private boolean active;
	
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name = "organization_id")
	private Organization organization;
	
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="course_id")
	private UploadedScorm uploadedScorm;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCouserDescription() {
		return couserDescription;
	}

	public void setCouserDescription(String couserDescription) {
		this.couserDescription = couserDescription;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public UploadedScorm getUploadedScorm() {
		return uploadedScorm;
	}

	public void setUploadedScorm(UploadedScorm uploadedScorm) {
		this.uploadedScorm = uploadedScorm;
	}
	
	
}
