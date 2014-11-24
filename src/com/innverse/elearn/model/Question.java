package com.innverse.elearn.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.innverse.elearn.eenum.CategoryType;


@Entity
@Table(name = "questions")
public class Question {

	@Id
	@GeneratedValue
	private long id;
	
	private String keyWord;
	
	@OneToOne
	private Category subCatagories;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public Category getSubCatagories() {
		return subCatagories;
	}

	public void setSubCatagories(Category subCatagories) {
		this.subCatagories = subCatagories;
	}

	
	
	
}


