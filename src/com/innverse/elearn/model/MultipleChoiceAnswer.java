package com.innverse.elearn.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.innverse.elearn.pojo.Choice;


@Entity
@Table(name = "multiplechoiceanswer")
@DiscriminatorValue("MCQ")
public class MultipleChoiceAnswer extends Answer {
	
	private ArrayList<String> choiceList;
	
	private ArrayList<String> answerList;

	

	public ArrayList<String> getChoiceList() {
		return choiceList;
	}

	public void setChoiceList(ArrayList<String> choiceList) {
		this.choiceList = choiceList;
	}

	public ArrayList<String> getAnswerList() {
		return answerList;
	}

	public void setAnswerList(ArrayList<String> answerList) {
		this.answerList = answerList;
	}

}

