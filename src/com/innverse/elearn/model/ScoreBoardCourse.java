package com.innverse.elearn.model;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "score_board_course" )
public class ScoreBoardCourse {

	@Id
	@GeneratedValue
	private long id;
	
	private String username;
	
	private long organizationId;
	
	private long memberId;
	
	private long courseId;

	private long deckId;
	
	private double score;
	
	private String status;
	
	private String startTime;
	
	private Timestamp endTime;
	
	private double earnCreditPoints;
	
	private int totalTryOrHits;
	
	private double percentComplete;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(long organizationId) {
		this.organizationId = organizationId;
	}

	public long getMemberId() {
		return memberId;
	}

	public void setMemberId(long memberId) {
		this.memberId = memberId;
	}

	public long getCourseId() {
		return courseId;
	}

	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}

	public long getDeckId() {
		return deckId;
	}

	public void setDeckId(long deckId) {
		this.deckId = deckId;
	}


	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public String getStartTime() {
		return startTime;
	}


	public Date getEndTime() {
		return endTime;
	}


	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}


	public double getEarnCreditPoints() {
		return earnCreditPoints;
	}

	public void setEarnCreditPoints(double earnCreditPoints) {
		this.earnCreditPoints = earnCreditPoints;
	}

	public int getTotalTryOrHits() {
		return totalTryOrHits;
	}

	public void setTotalTryOrHits(int totalTryOrHits) {
		this.totalTryOrHits = totalTryOrHits;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getPercentComplete() {
		return percentComplete;
	}

	public void setPercentComplete(double percentComplete) {
		this.percentComplete = percentComplete;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
	
	

}
