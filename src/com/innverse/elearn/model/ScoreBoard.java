package com.innverse.elearn.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "score_board" )
public class ScoreBoard {

	@Id
	@GeneratedValue
	private long id;
	
	private String username;
	
	private long organizationId;
	
	private long memberId;
	
	private long deckId;
	
	private long seriesId;
	
	private long levelId;
	
	private double score;
	
	private String status;
	
	private boolean isCompletedSeries;
	
	private boolean isCompletedLevel;
	
	private String startTime;
	
	private String endTime;
	
	private double earnCreditPoints;
	
	private int totalTryOrHits;

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

	public long getDeckId() {
		return deckId;
	}

	public void setDeckId(long deckId) {
		this.deckId = deckId;
	}

	public long getSeriesId() {
		return seriesId;
	}

	public void setSeriesId(long seriesId) {
		this.seriesId = seriesId;
	}

	public long getLevelId() {
		return levelId;
	}

	public void setLevelId(long levelId) {
		this.levelId = levelId;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public boolean isCompletedSeries() {
		return isCompletedSeries;
	}

	public void setCompletedSeries(boolean isCompletedSeries) {
		this.isCompletedSeries = isCompletedSeries;
	}

	public boolean isCompletedLevel() {
		return isCompletedLevel;
	}

	public void setCompletedLevel(boolean isCompletedLevel) {
		this.isCompletedLevel = isCompletedLevel;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
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
	
}
