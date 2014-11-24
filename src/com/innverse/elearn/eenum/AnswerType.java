package com.innverse.elearn.eenum;

public enum AnswerType {
	
	MCQ(1,"MultipleChoiceQuestions"), YES_NO(2,"Yes/No"), SUBJECTIVE(3, "Subjective");
	
	private int code;
	private String status;
	
	AnswerType(int code,String status){
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

	public AnswerType getanswerType(int code){
		AnswerType[] values = AnswerType.values();
		for(AnswerType answer : values){
			if(answer.code == code){
				return answer;
			}
		}
		return null;
	}

}

