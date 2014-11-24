package com.innverse.elearn.pojo;

import java.io.Serializable;


public class Choice implements Serializable{
	
	private String option;
	
	private String value;

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}