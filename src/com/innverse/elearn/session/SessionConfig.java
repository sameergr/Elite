package com.innverse.elearn.session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionConfig {
	
	public HttpSession createNewSession(HttpServletRequest request){
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(20 * 60);
		return session;
	}

}
