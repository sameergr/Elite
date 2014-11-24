package com.innverse.elearn.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class GetCompanyUtil {

	public static String getCompanyName(HttpServletRequest request){
		String hostName =  request.getHeader("Host");
		int findIndex = hostName.indexOf(".elite.com");
		if(request.getHeader("Host").indexOf(".elite.com") > -1){
			String companyName = hostName.substring(0,findIndex);
			return companyName;
		}
		return "";
	}
}
