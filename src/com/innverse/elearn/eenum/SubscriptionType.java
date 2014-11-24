package com.innverse.elearn.eenum;


public enum SubscriptionType {

	FREE(1,"Free"), PAID(2,"Paid");
	
	private int code;
	private String status;
	
	SubscriptionType(int code,String status){
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

	public static SubscriptionType getTransStatus(int code){
		SubscriptionType[] values = SubscriptionType.values();
		for(SubscriptionType type : values){
			if(type.code == code){
				return type;
			}
		}
		return null;
	}
	
}
	