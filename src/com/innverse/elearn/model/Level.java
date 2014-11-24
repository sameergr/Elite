package com.innverse.elearn.model;

import java.util.HashSet;
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
import javax.persistence.Table;




@Entity
@Table(name = "level")
public class Level {

	
	@Id
	@GeneratedValue
	private Long id;
	
	private String levelname;
	
	@ManyToMany(cascade = CascadeType.DETACH,fetch=FetchType.EAGER)
	@JoinTable(name = "level_content_join",
    joinColumns =
    @JoinColumn(name = "level_id", nullable = false),
    inverseJoinColumns =
    @JoinColumn(name = "content_id", nullable = false))
	private Set<Content> questions;
	
	@Column(name = "passing_score")
	private String passingScore;
	
	@Column(name = "mastery_score")
	private String masteryScore;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="series_id", nullable=false)
	private Series series;
	
	private int levelOrder;
	
	private int maxTryOrHit;
	
	private boolean isFirstLevel;
	
	private boolean isLastLevel;
	
	private double creditPoints;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getLevelname() {
		return levelname;
	}
	
	public void setLevelname(String levelname) {
		this.levelname = levelname;
	}
	
	public Set<Content> getQuestions() {
		return questions;
	}

	public void setQuestions(Set<Content> questions) {
		this.questions = questions;
	}

	public void addQuestion(Content content){
		if(this.questions == null){
			this.questions = new HashSet<Content>();
		}
		this.questions.add(content);
	}
	
	public Series getSeries() {
		return series;
	}
	
	public void setSeries(Series series) {
		this.series = series;
	}

	public String getPassingScore() {
		return passingScore;
	}

	public void setPassingScore(String passingScore) {
		this.passingScore = passingScore;
	}

	public String getMasteryScore() {
		return masteryScore;
	}

	public void setMasteryScore(String masteryScore) {
		this.masteryScore = masteryScore;
	}

	public int getLevelOrder() {
		return levelOrder;
	}

	public void setLevelOrder(int levelOrder) {
		this.levelOrder = levelOrder;
	}

	public int getMaxTryOrHit() {
		return maxTryOrHit;
	}

	public void setMaxTryOrHit(int maxTryOrHit) {
		this.maxTryOrHit = maxTryOrHit;
	}

	public boolean isFirstLevel() {
		return isFirstLevel;
	}

	public void setFirstLevel(boolean isFirstLevel) {
		this.isFirstLevel = isFirstLevel;
	}

	public boolean isLastLevel() {
		return isLastLevel;
	}

	public void setLastLevel(boolean isLastLevel) {
		this.isLastLevel = isLastLevel;
	}

	public double getCreditPoints() {
		return creditPoints;
	}

	public void setCreditPoints(double creditPoints) {
		this.creditPoints = creditPoints;
	}
	
}
