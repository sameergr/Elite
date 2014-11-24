package com.innverse.elearn.eenum;

public enum EnrollWith {
	
	PARENT(0,"Parent"),TEACHER(1,"SubCategory"),ORGANIZATION(2,"Organization");
	
	private int code;
	private String desc;
	
	
	
	EnrollWith(int code,String desc){
		this.code=code;
		this.desc=desc;
	}
	
	public int getCode() {
		return code;
	}
	public String getDesc() {
		return desc;
	}
	
	public EnrollWith getEnrollWith(int code){
		EnrollWith[] values = EnrollWith.values();
		for(EnrollWith EnrollWithStatus : values){
			if(EnrollWithStatus.code == code){
				return EnrollWithStatus;
			}
		}
		return null;
	}
}
