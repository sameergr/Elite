package com.innverse.elearn.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.innverse.elearn.eenum.AnswerType;
import com.innverse.elearn.eenum.ProficiencyType;

@Entity
@Table(name = "content")
public class Content {

	@Id
	@GeneratedValue
	private long id;
	
	private String keyword;
	
	private long catagoryId;
	
	private long subCategoryId;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "organization_id")
	private Organization createBy;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "question_id")
	private Question question;
	
	private boolean active;
	
	private AnswerType answerType;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "answer_id")
	private Answer answer;
	
	private ProficiencyType minProficiency;
	
	private ProficiencyType maxProficiency;
	
	private String referencePoint;
	
	private String hint;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCatagoryId() {
		return catagoryId;
	}

	public void setCatagoryId(long catagoryId) {
		this.catagoryId = catagoryId;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public AnswerType getAnswerType() {
		return answerType;
	}

	public void setAnswerType(AnswerType answerType) {
		this.answerType = answerType;
	}

	public Answer getAnswer() {
		return answer;
	}

	public void setAnswer(Answer answer) {
		this.answer = answer;
	}

	public long getSubCategoryId() {
		return subCategoryId;
	}

	public void setSubCategoryId(long subCategoryId) {
		this.subCategoryId = subCategoryId;
	}

	@JsonIgnore
	public Organization getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Organization createBy) {
		this.createBy = createBy;
	}

	public ProficiencyType getMinProficiency() {
		return minProficiency;
	}

	public void setMinProficiency(ProficiencyType minProficiency) {
		this.minProficiency = minProficiency;
	}

	public ProficiencyType getMaxProficiency() {
		return maxProficiency;
	}

	public void setMaxProficiency(ProficiencyType maxProficiency) {
		this.maxProficiency = maxProficiency;
	}

	public String getReferencePoint() {
		return referencePoint;
	}

	public void setReferencePoint(String referencePoint) {
		this.referencePoint = referencePoint;
	}

	public String getHint() {
		return hint;
	}

	public void setHint(String hint) {
		this.hint = hint;
	}
	
	

}
