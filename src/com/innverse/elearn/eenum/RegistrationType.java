package com.innverse.elearn.eenum;

public enum RegistrationType {
	
	FREE(1,"Free"), STANDARD(2,"Standard"),PREMIUM(3,"Premium");
	
	private int code;
	private String status;
	
	RegistrationType(int code,String status){
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

	public RegistrationType getKYLStatus(int code){
		RegistrationType[] values = RegistrationType.values();
		for(RegistrationType kylStatus : values){
			if(kylStatus.code == code){
				return kylStatus;
			}
		}
		return null;
	}

}
