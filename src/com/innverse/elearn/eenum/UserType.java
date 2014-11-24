package com.innverse.elearn.eenum;


public enum UserType {

	PARENT(1,"Parent"), STUDENT(2,"Student"), TEACHER(3,"Teacher"), ORGANIZATION(4,"Organization");
	
	private int code;
	private String status;
	
	UserType(int code,String status){
		this.code = code;
		this.status = status;
	}
	
	public int getCode(){
		return code;
	}
	
	public String getMessage(){
		return status;
	}
	
	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public static UserType getTransStatus(int code){
		UserType[] values = UserType.values();
		for(UserType type : values){
			if(type.code == code){
				return type;
			}
		}
		return null;
	}
	
}
	