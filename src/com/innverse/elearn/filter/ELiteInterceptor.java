package com.innverse.elearn.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.innverse.elearn.config.Config;
import com.innverse.elearn.services.CommonServiceImpl;
import com.innverse.elearn.session.SessionConfig;

public class ELiteInterceptor implements HandlerInterceptor  {
    
	@Autowired
    private Config config;
	
	@Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {
		
        System.out.println("Pre-handle");
        System.out.println("Server Name :- " + request.getServerName());
        System.out.println("Context Path :- " + request.getContextPath());
        System.out.println("Remote Addr :- " + request.getRemoteAddr());
        System.out.println("Remote Host :- " + request.getRemoteHost());
        System.out.println("Host :- " + request.getHeader("Host"));
        System.out.println("Local Addr :- " + request.getLocalAddr());
        System.out.println("Local URL :- " + request.getRequestURL());
        System.out.println("Local URI :- " + request.getRequestURI());
        System.out.println("Query URL :- " + request.getQueryString());
        System.out.println("Referer URL :- " + request.getHeader("Referer"));
        System.out.println("Local Name :- " + request.getLocalName());
        System.out.println("Local Name :- " + request.getPathInfo());
        System.out.println("Translated Name :- " + request.getPathTranslated());
        System.out.println("Image Path :- " + config.getMsg("image.path"));

        SessionConfig sessionConfig = new SessionConfig();
        HttpSession session = sessionConfig.createNewSession(request);
		String hostName =  request.getHeader("Host");
		int findIndex = hostName.indexOf(".elite.com");
		if(request.getHeader("Host").indexOf(".elite.com") > -1){
			String companyName = hostName.substring(0,findIndex);
			System.out.println("--------- Host Name ---------");
			String cn = (String)session.getAttribute("cn");
			if(cn == null){
				System.out.println("--------- User Type ---------");
				session.setAttribute("userType", "org");
				session.setAttribute("cn", companyName);
			}
		}
		else{
			session.setAttribute("userType", "site");
		}
		
        return true;
    }

	@Override
    public void postHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler, ModelAndView mv) throws Exception {
        System.out.println("Post-handle");
    }

	@Override
    public void afterCompletion(HttpServletRequest request,
            HttpServletResponse response, Object handler , Exception expection){
        System.out.println("after-completion");
    }
	
}