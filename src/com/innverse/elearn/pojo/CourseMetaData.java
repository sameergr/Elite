package com.innverse.elearn.pojo;

import java.io.Serializable;

public class CourseMetaData implements Serializable {
	
	private String organizationId;
	
	private String memberId;
	
	private String courseId;
	
	private String sessKey;

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getSessKey() {
		return sessKey;
	}

	public void setSessKey(String sessKey) {
		this.sessKey = sessKey;
	}

	@Override
	public String toString() {
		return "CourseMetaData [organizationId=" + organizationId + ", memberId=" + memberId + ", courseId="
				+ courseId + "]";
	}
	
}
