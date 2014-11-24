package com.innverse.elearn.json;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.innverse.elearn.eenum.CourseType;

public class ScormFileForm {

	private String courseName;
	
	private CommonsMultipartFile courseFile;
	
	private CourseType courseType;
	
	private String courseDescription;

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public CommonsMultipartFile getCourseFile() {
		return courseFile;
	}

	public void setCourseFile(CommonsMultipartFile courseFile) {
		this.courseFile = courseFile;
	}

	public CourseType getCourseType() {
		return courseType;
	}

	public void setCourseType(CourseType courseType) {
		this.courseType = courseType;
	}

	public String getCourseDescription() {
		return courseDescription;
	}

	public void setCourseDescription(String courseDescription) {
		this.courseDescription = courseDescription;
	}
	
}
