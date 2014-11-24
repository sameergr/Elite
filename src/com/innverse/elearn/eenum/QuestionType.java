package com.innverse.elearn.eenum;



public enum QuestionType {
	 YESNO(0,"Yes No"), MULTIPLECHOICE(1,"Multiple Choice"), SUBJECTIVE(2, "Subjective");
	
	private int code;
	private String desc;
	
	
	
	QuestionType(int code,String desc){
		this.code=code;
		this.desc=desc;
	}
	
	public int getCode() {
		return code;
	}
	public String getDesc() {
		return desc;
	}
	
	public QuestionType getQuestionType(int code){
		QuestionType[] values = QuestionType.values();
		for(QuestionType QuestionTypeStatus : values){
			if(QuestionTypeStatus.code == code){
				return QuestionTypeStatus;
			}
		}
		return null;
	}

}
