package com.innverse.elearn.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "subjectiveanswer")
@DiscriminatorValue("S")
public class SubjectiveAnswer extends Answer {
	
	private String subjectiveAnswer;

	public String getSubjectiveAnswer() {
		return subjectiveAnswer;
	}

	public void setSubjectiveAnswer(String subjectiveAnswer) {
		this.subjectiveAnswer = subjectiveAnswer;
	}
	
}
