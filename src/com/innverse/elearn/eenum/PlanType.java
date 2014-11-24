package com.innverse.elearn.eenum;

public enum PlanType {
	
	YEARLY(1,"Yearly"), MONTHLY(2,"monthly"),QUATERLY(3,"Quaterly"),DAYS(4,"Days");
	
	private int code;
	private String status;
	
	PlanType(int code,String status){
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

	public PlanType getKYLStatus(int code){
		PlanType[] values = PlanType.values();
		for(PlanType kylStatus : values){
			if(kylStatus.code == code){
				return kylStatus;
			}
		}
		return null;
	}

}
