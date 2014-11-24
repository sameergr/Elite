package com.innverse.elearn.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="course_track")
public class CourseTrack {

	@Id
	@GeneratedValue
	private long id;
	
	@OneToOne
	@JoinColumn(name = "organization_id")
	private Organization organization;
	
	@OneToOne
	@JoinColumn(name = "course_id")
	private Course course;
	
	@OneToOne
	@JoinColumn(name = "member_id")
	private Member member;
	
	private String element;
	
	private String value;
	
	private long timemodified;
	
	private int attempt;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Course getCourse() {
		return course;
	}
	
	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public String getElement() {
		return element;
	}

	public void setElement(String element) {
		this.element = element;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public long getTimemodified() {
		return timemodified;
	}

	public void setTimemodified(long timemodified) {
		this.timemodified = timemodified;
	}

	public int getAttempt() {
		return attempt;
	}

	public void setAttempt(int attempt) {
		this.attempt = attempt;
	}
	
}
