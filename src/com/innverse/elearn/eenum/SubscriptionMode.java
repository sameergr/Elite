package com.innverse.elearn.eenum;


public enum SubscriptionMode {

	OFFLINE(1,"Offline"), ONLINE(2,"Online");
	
	private int code;
	private String status;
	
	SubscriptionMode(int code,String status){
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

	public static SubscriptionMode getTransStatus(int code){
		SubscriptionMode[] values = SubscriptionMode.values();
		for(SubscriptionMode type : values){
			if(type.code == code){
				return type;
			}
		}
		return null;
	}
	
}
	