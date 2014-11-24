package com.innverse.elearn.eenum;

public enum RegistrationStatus {
	
PENDING(0,"pending"), COMPLETED(1,"completed");
	
	private int code;
	private String status;
	
	RegistrationStatus(int code,String status){
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

	public RegistrationStatus getOrganizationStatus(int code){
		RegistrationStatus[] values = RegistrationStatus.values();
		for(RegistrationStatus organizationStatus : values){
			if(organizationStatus.code == code){
				return organizationStatus;
			}
		}
		return null;
	}

}
