package com.innverse.elearn.eenum;

public enum ViewType {
	
PUBLIC(0,"Public"), PRIVATE(1,"Private");
	
	private int code;
	private String status;
	
	ViewType(int code,String status){
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

	public static ViewType getTransStatus(int code){
		ViewType[] values = ViewType.values();
		for(ViewType type : values){
			if(type.code == code){
				return type;
			}
		}
		return null;
	}

}
