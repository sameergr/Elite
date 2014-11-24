package com.innverse.elearn.eenum;

public enum ProficiencyType {
	
	BEGINNER(0,"Beginner"),INTERMIDIATE(1,"Intermidiate"), ADVANCED(2,"Advanced"), EXPERT(3,"Expert");
	
	private int code;
	private String status;
	
	ProficiencyType(int code,String status){
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

	public ProficiencyType getanswerType(int code){
		ProficiencyType[] values = ProficiencyType.values();
		for(ProficiencyType answer : values){
			if(answer.code == code){
				return answer;
			}
		}
		return null;
	}

}

