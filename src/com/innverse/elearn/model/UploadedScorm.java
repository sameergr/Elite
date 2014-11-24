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

import com.innverse.elearn.eenum.CourseType;

@Entity
@Table(name="uploaded_scorm")
public class UploadedScorm {

	@Id
	@GeneratedValue
	@Column(name="id")
	private long id;
	
	private String fileName;
	
	private String filePath;
	
	private double fileSize;
	
	private Date uploadedDate;
	
	private boolean active;
	
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name = "organization_id")
	private Organization organization;
	
	private String packageType;
	
	private String version;
	
	private String title;
	
	private String identifier;
	
	private String launch;
	
	private String scromType;
	
	private String parentRoot;
	
	private boolean manifest;
	
	private double masteryScore;
	
	private double maxScore;
	
	private CourseType scormType;
	
	@OneToOne(mappedBy = "uploadedScorm")
	private Course course;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public double getFileSize() {
		return fileSize;
	}

	public void setFileSize(double fileSize) {
		this.fileSize = fileSize;
	}

	public Date getUploadedDate() {
		return uploadedDate;
	}

	public void setUploadedDate(Date uploadedDate) {
		this.uploadedDate = uploadedDate;
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

	public String getPackageType() {
		return packageType;
	}

	public void setPackageType(String packageType) {
		this.packageType = packageType;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getLaunch() {
		return launch;
	}

	public void setLaunch(String launch) {
		this.launch = launch;
	}

	public String getScromType() {
		return scromType;
	}

	public void setScromType(String scromType) {
		this.scromType = scromType;
	}

	public String getParentRoot() {
		return parentRoot;
	}

	public void setParentRoot(String parentRoot) {
		this.parentRoot = parentRoot;
	}

	public boolean isManifest() {
		return manifest;
	}

	public void setManifest(boolean manifest) {
		this.manifest = manifest;
	}

	public double getMasteryScore() {
		return masteryScore;
	}

	public void setMasteryScore(double masteryScore) {
		this.masteryScore = masteryScore;
	}

	public double getMaxScore() {
		return maxScore;
	}

	public void setMaxScore(double maxScore) {
		this.maxScore = maxScore;
	}

	public CourseType getScormType() {
		return scormType;
	}

	public void setScormType(CourseType scormType) {
		this.scormType = scormType;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
	
}
