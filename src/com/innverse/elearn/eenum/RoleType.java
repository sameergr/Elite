package com.innverse.elearn.eenum;

public enum RoleType {
	
	USER(1,"User"), ADMIN(2,"Admin"), SUPERADMIN(3,"SuperAdmin");
	
	private int code;
	private String status;
	
	RoleType(int code,String status){
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

	public RoleType getRoleType(int code){
		RoleType[] values = RoleType.values();
		for(RoleType roleType : values){
			if(roleType.code == code){
				return roleType;
			}
		}
		return null;
	}

}
