package com.innverse.elearn.eenum;

public enum OfferType {
	
	LIMITED(1,"Limited Time Period"), UNLIMITED(2,"Valid Till Plan Subscribed");
	
	private int code;
	private String status;
	
	OfferType(int code,String status){
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

	public OfferType getKYLStatus(int code){
		OfferType[] values = OfferType.values();
		for(OfferType kylStatus : values){
			if(kylStatus.code == code){
				return kylStatus;
			}
		}
		return null;
	}

}
