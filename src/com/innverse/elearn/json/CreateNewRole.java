package com.innverse.elearn.json;

import java.util.ArrayList;

import com.innverse.elearn.eenum.MemberRole;

public class CreateNewRole {
	
	private ArrayList<String> features;
	
	private ArrayList<String> limited;
	
	private ArrayList<String> value;
	
	private MemberRole role;
	
	public ArrayList<String> getLimited() {
		return limited;
	}

	public void setLimited(ArrayList<String> limited) {
		this.limited = limited;
	}

	public ArrayList<String> getFeatures() {
		return features;
	}

	public void setFeatures(ArrayList<String> features) {
		this.features = features;
	}

	public ArrayList<String> getValue() {
		return value;
	}

	public void setValue(ArrayList<String> value) {
		this.value = value;
	}

	public MemberRole getRole() {
		return role;
	}

	public void setRole(MemberRole role) {
		this.role = role;
	}

}
