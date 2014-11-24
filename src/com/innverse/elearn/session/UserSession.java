package com.innverse.elearn.session;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

public class UserSession {

private static UserSession userSession = null;
	
	private UserSession(){
		
	}
	
	public static UserSession getInstance(){
		if(userSession == null){
			userSession = new UserSession();
		}
		return userSession;
	}
	
	public User getCurrentlyAuthenticatedUser() {

	    Authentication a = SecurityContextHolder.getContext().getAuthentication();
	    Object currentUserDetails = (Object) a.getPrincipal();
	    System.out.println("Current User Detail --> " + currentUserDetails);
	    if(currentUserDetails == null || currentUserDetails instanceof String){
	    	return null;
	    }
	    if(currentUserDetails != null && currentUserDetails instanceof User) {
	        return (User)currentUserDetails;
	    }
	    return null;
	}
	
}
