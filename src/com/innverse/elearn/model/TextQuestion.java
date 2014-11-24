package com.innverse.elearn.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "textquestion")
@DiscriminatorValue("TQ")
public class TextQuestion extends Question {

	
	private String question;
	
	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

}
