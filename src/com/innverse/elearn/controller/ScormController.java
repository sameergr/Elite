package com.innverse.elearn.controller;

import java.net.URI;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.innverse.elearn.eenum.MemberRole;
import com.innverse.elearn.model.Course;
import com.innverse.elearn.model.Member;
import com.innverse.elearn.model.UserAccount;
import com.innverse.elearn.scorm.AICCService;
import com.innverse.elearn.scorm.ScormService;
import com.innverse.elearn.services.CommonServiceImpl;
import com.innverse.elearn.util.Base64Util;
import com.innverse.elearn.util.UrlEncodedQueryString;

@Controller
@SessionAttributes
@RequestMapping(value = "/scorm")
public class ScormController {
	
	@Autowired
	private AICCService aiccService;
	
	@Autowired
	private ScormService scormService;
	
	@Autowired
	private CommonServiceImpl commonService;
	
	@RequestMapping("/aicc")
	public ModelAndView handleAICCRequestData(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mv = new ModelAndView("aicc_page");
		aiccService.handleRequest(request,mv);
		return mv;
	}
	
	@RequestMapping("/launch/{courseid}")
	public ModelAndView handleAICCRequestData(@PathVariable("courseid") String courseId, HttpServletRequest request, HttpServletResponse response){
		System.out.println("Context Path ::- " + request.getContextPath());
		Member memberAccount = (Member)request.getSession(false).getAttribute("member");
		Member member = commonService.getOrganizationMember(memberAccount.getAccount().getOrganization().getId(), memberAccount.getAccount().getId(), MemberRole.AdminRole);
		String encodeStr = Base64Util.encode("courseId="+courseId+"&memberId="+member.getMemberId()+"&organizationId="+memberAccount.getAccount().getOrganization().getId());
		ModelAndView mv = new ModelAndView("scorm_player_page");
		mv.addObject("cmd", encodeStr);
		return mv;
	}
	
	@RequestMapping("/load/sco")
	public ModelAndView loadSCO(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mv = new ModelAndView("load_sco_page");
		System.out.println("CMD ::- " + request.getParameter("cmd"));
		String endcodedCourseMetadata = request.getParameter("cmd");

		try{
			String courseMetada = Base64Util.decode(endcodedCourseMetadata);
			
			URI uri = new URI("http://abc.com?"+courseMetada);
			UrlEncodedQueryString queryString = UrlEncodedQueryString.parse(uri);
			
			String courseId = queryString.get("courseId");
			String memberId = queryString.get("memberId");
			String organizationId = queryString.get("organizationId");
			
			Course course = commonService.getCourseById(Long.valueOf(courseId));
			mv.addObject("fileName", course.getUploadedScorm().getFileName());
			mv.addObject("lauch", course.getUploadedScorm().getLaunch());
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return mv;
	}
	
	@RequestMapping("/load/test")
	public ModelAndView loadTest(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mv = new ModelAndView("test_sco_page");
		return mv;
	}
	
}
