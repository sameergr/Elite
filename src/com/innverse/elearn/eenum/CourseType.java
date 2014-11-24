package com.innverse.elearn.eenum;

public enum CourseType {
	
	SCORM(1,"Scorm"), AICC(2,"AICC");
	
	private int code;
	private String message;
	
	CourseType(int code,String message){
		this.code = code;
		this.message = message;
	}
	
	public int getCode(){
		return code;
	}
	
	public String getMessage(){
		return message;
	}
	
	public String getStatus() {
		return message;
	}

	public void setStatus(String message) {
		this.message = message;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public CourseType getanswerType(int code){
		CourseType[] values = CourseType.values();
		for(CourseType answer : values){
			if(answer.code == code){
				return answer;
			}
		}
		return null;
	}

}

