package com.innverse.elearn.eenum;

public enum MediaType {

	Books(1,"Books"),Videos(2,"Videos"),Tutorials(3,"Tutorials");
	
	private int code;
	private String status;
	
	MediaType(int code,String status){
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
