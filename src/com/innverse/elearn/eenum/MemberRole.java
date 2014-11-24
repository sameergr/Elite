package com.innverse.elearn.eenum;

public enum MemberRole {

	UserRole(1,"UserRole"),AdminRole(2,"AdminRole"),SuperAdminRole(3,"SuperAdminRole"),QARole(4,"QARole");
	
	private int code;
	private String status;
	
	MemberRole(int code,String status){
		this.code = code;
		this.status = status;
	}
	
	public int getCode(){
		return code;
	}
	
	public String getMessage(){
		return status;
	}
	

}
