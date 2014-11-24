package com.innverse.elearn.json;

import javax.jms.Session;

public class Root {

	private Session session;
    private boolean isErrFlag;
    private String errMessage;
    
    
	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}


	public boolean isErrFlag() {
		return isErrFlag;
	}

	public void setErrFlag(boolean isErrFlag) {
		this.isErrFlag = isErrFlag;
	}

	public String getErrMessage() {
		return errMessage;
	}

	public void setErrMessage(String errMessage) {
		this.errMessage = errMessage;
	}

	
}
