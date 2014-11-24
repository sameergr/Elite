package com.innverse.elearn.json;

import java.util.ArrayList;
import java.util.List;

public class GroupForm {

	
	private List<String> memberId;
	
	private String groupName;
	
	private ArrayList<String> groupMembers;
	
	private ArrayList<String> allMembers;

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public List<String> getMemberId() {
		return memberId;
	}

	public void setMemberId(List<String> memberId) {
		this.memberId = memberId;
	}

	public ArrayList<String> getGroupMembers() {
		return groupMembers;
	}

	public void setGroupMembers(ArrayList<String> groupMembers) {
		this.groupMembers = groupMembers;
	}

	public ArrayList<String> getAllMembers() {
		return allMembers;
	}

	public void setAllMembers(ArrayList<String> allMembers) {
		this.allMembers = allMembers;
	}
	
	

}
