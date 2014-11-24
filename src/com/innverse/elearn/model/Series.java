package com.innverse.elearn.model;


import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;




@Entity
@Table(name = "series")
public class Series {

	@Id
	@GeneratedValue
	private Long id;
	
	private String seriesName;
	
	@OneToOne(cascade = CascadeType.DETACH)
	private Category category;
	
	@OneToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER,mappedBy="series")
	private List<Level> levels;
	
	@OneToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "organization_id")
	private Organization createBy;
	
	private Date createOn;
	
	private double creditPoints;

	private String questionLevelTiming;
	
	private String totalTime;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@JsonIgnore
	public List<Level> getLevels() {
		return levels;
	}

	public void setLevels(List<Level> levels) {
		this.levels = levels;
	}
	
	public void addLevel(Level level){
		if(this.levels == null){
			this.levels = new ArrayList<Level>();
		}
		this.levels.add(level);
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getSeriesName() {
		return seriesName;
	}

	public void setSeriesName(String seriesName) {
		this.seriesName = seriesName;
	}

	@JsonIgnore
	public Organization getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Organization createBy) {
		this.createBy = createBy;
	}

	public Date getCreateOn() {
		return createOn;
	}

	public void setCreateOn(Date createOn) {
		this.createOn = createOn;
	}

	public double getCreditPoints() {
		return creditPoints;
	}

	public void setCreditPoints(double creditPoints) {
		this.creditPoints = creditPoints;
	}

	public String getQuestionLevelTiming() {
		return questionLevelTiming;
	}

	public void setQuestionLevelTiming(String questionLevelTiming) {
		this.questionLevelTiming = questionLevelTiming;
	}

	public String getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(String totalTime) {
		this.totalTime = totalTime;
	}

	
}
