package com.innverse.elearn.eenum;

public enum PrivilegeType {
	
	CREATE(1,"Create"),EDIT(2,"Edit"), DELETE(3,"Delete");
	
	private int code;
	private String status;
	
	PrivilegeType(int code,String status){
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

	public PrivilegeType getKYLStatus(int code){
		PrivilegeType[] values = PrivilegeType.values();
		for(PrivilegeType kylStatus : values){
			if(kylStatus.code == code){
				return kylStatus;
			}
		}
		return null;
	}

}
