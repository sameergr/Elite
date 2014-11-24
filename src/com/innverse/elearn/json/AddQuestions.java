package com.innverse.elearn.json;

import java.util.ArrayList;
import java.util.List;

import com.innverse.elearn.eenum.ProficiencyType;

public class AddQuestions {
	
	private String question;
	
	private String answer;
	
	private String questionType;
		
	private ArrayList<String> options;
	
	private String[] correctAnswer;
	
	private String subjectiveAnswer;
	
	private String minProficiency;
	
	private String maxProficiency;
	

	public String[] getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(String[] correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	private ArrayList<String> wrongAnswer; 

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getQuestionType() {
		return questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

	public ArrayList<String> getOptions() {
		return options;
	}

	public void setOptions(ArrayList<String> options) {
		this.options = options;
	}

	public ArrayList<String> getWrongAnswer() {
		return wrongAnswer;
	}

	public void setWrongAnswer(ArrayList<String> wrongAnswer) {
		this.wrongAnswer = wrongAnswer;
	}

	public String getSubjectiveAnswer() {
		return subjectiveAnswer;
	}

	public void setSubjectiveAnswer(String subjectiveAnswer) {
		this.subjectiveAnswer = subjectiveAnswer;
	}

	public String getMinProficiency() {
		return minProficiency;
	}

	public void setMinProficiency(String minProficiency) {
		this.minProficiency = minProficiency;
	}

	public String getMaxProficiency() {
		return maxProficiency;
	}

	public void setMaxProficiency(String maxProficiency) {
		this.maxProficiency = maxProficiency;
	}
	
}
