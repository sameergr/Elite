package com.innverse.elearn.eenum;


public enum FeatureType {

	LIMITED(1,"Limited"), UNLIMITED(2,"UnLimited"), NOTALLOWED(3,"Not Allowed");
	
	private int code;
	private String status;
	
	FeatureType(int code,String status){
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

	public static FeatureType getTransStatus(int code){
		FeatureType[] values = FeatureType.values();
		for(FeatureType type : values){
			if(type.code == code){
				return type;
			}
		}
		return null;
	}
	
}
	