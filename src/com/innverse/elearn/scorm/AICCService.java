package com.innverse.elearn.scorm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.innverse.elearn.dao.CommonDaoImpl;
import com.innverse.elearn.dao.ScormDao;
import com.innverse.elearn.model.Course;
import com.innverse.elearn.model.CourseTrack;
import com.innverse.elearn.model.Member;
import com.innverse.elearn.model.Organization;
import com.innverse.elearn.model.ScoreBoardCourse;
import com.innverse.elearn.pojo.ScormData;
import com.innverse.elearn.util.Base64Util;
import com.innverse.elearn.util.UrlEncodedQueryString;

public class AICCService {

	@Autowired
	private ScormDao scormDao;
	
	@Autowired
	private CommonDaoImpl commonDao;
	
	String courseId;
	String organizationId;
	String memberId;
	
	public void handleRequest(HttpServletRequest request, ModelAndView mv){
		System.out.println("Command ::- " + request.getParameter("command"));
		System.out.println("Sess Key ::- " + request.getParameter("session_id"));
		System.out.println("AICC Data ::- " + request.getParameter("aicc_data"));
		
		String command = request.getParameter("command");
		String sessKey = request.getParameter("session_id");
		String aiccData = request.getParameter("aicc_data");

		try{
			
			String courseMetada = Base64Util.decode(request.getParameter("session_id"));
			
			URI uri = new URI("http://abc.com?"+courseMetada);
			UrlEncodedQueryString queryString = UrlEncodedQueryString.parse(uri);

			courseId = queryString.get("courseId");
			memberId = queryString.get("memberId");
			organizationId = queryString.get("organizationId");
			
			System.out.println("course Id ::- " + courseId);
			System.out.println("member Id ::- " + memberId);
			System.out.println("organization Id ::- " + organizationId);
			

			if("GETPARAM".equalsIgnoreCase(command)){
				handleGetParamRequest(command, sessKey, aiccData, mv);
			}else if("PUTPARAM".equalsIgnoreCase(command)){
				handlePutParamRequest(command, sessKey, aiccData, mv);
			}else if("EXITAU".equalsIgnoreCase(command)){
				handleExitAURequest(command, sessKey, aiccData, mv);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private void handleExitAURequest(String command, String sessKey, String aiccData, ModelAndView mv){
		
		Course course = commonDao.getCourseById(Long.valueOf(courseId));
		Member member = commonDao.getMemberById(Long.valueOf(memberId));
		
		Hashtable<String,String> aiccDataMap = new Hashtable<String,String>();
		BufferedReader br = null;
        try {
            String sCurrentLine;
            br = new BufferedReader(new StringReader(aiccData));
            while ((sCurrentLine = br.readLine()) != null) {
                System.out.println("Current Line --> " + sCurrentLine);
                if(sCurrentLine != null && sCurrentLine.indexOf("=") > -1){
                	int equalIndex = sCurrentLine.indexOf("=");
        			if(equalIndex > -1){
        				String keyData = sCurrentLine.substring(0,equalIndex).trim();
        				String valueData = sCurrentLine.substring(equalIndex + 1).trim();
        				aiccDataMap.put(keyData, valueData);
        			}
                }else if(sCurrentLine.indexOf("Core_Lesson") > -1){
                	sCurrentLine = br.readLine();
                	aiccDataMap.put("Suspended_Data", sCurrentLine);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        System.out.println("aiccDataMap ::- " + aiccDataMap);
        List<CourseTrack> courseTracks = new ArrayList<CourseTrack>();
        for(Entry<String, String> entry : aiccDataMap.entrySet()){
        	if(ScormUtil.getKeyForAICCElement(entry.getKey()) != null){
            	CourseTrack courseTrack = scormDao.getCourseTrack(member.getMemberId(), course.getId(), member.getOrganization().getId(), entry.getKey());
            	if(courseTrack == null){
                	courseTrack = new CourseTrack();
                	courseTrack.setCourse(course);
                	courseTrack.setMember(member);
                	courseTrack.setOrganization(member.getOrganization());
            	}
            	courseTrack.setAttempt(1);
            	courseTrack.setElement(ScormUtil.getKeyForAICCElement(entry.getKey()));
            	courseTrack.setValue((ScormUtil.getKeyForAICCElement(entry.getKey()).equalsIgnoreCase("cmi.core.lesson_status") ? ScormUtil.getKeyForAICCLessonStatusValue(entry.getValue()) : entry.getValue() ));
            	System.out.println("Key/Value ::- " + entry.getKey()+"/"+(ScormUtil.getKeyForAICCElement(entry.getKey()).equalsIgnoreCase("cmi.core.lesson_status") ? ScormUtil.getKeyForAICCLessonStatusValue(entry.getValue()) : entry.getValue() ));
            	scormDao.saveUpdateCourseTrack(courseTrack);
        	}
        }

    	CourseTrack courseTrackScore = scormDao.getCourseTrack(member.getMemberId(), course.getId(), member.getOrganization().getId(), "cmi.core.score.raw");
    	CourseTrack courseTrackLessonStatus = scormDao.getCourseTrack(member.getMemberId(), course.getId(), member.getOrganization().getId(), "cmi.core.lesson_status");
    	createCourseScoreBoard(courseTrackScore,courseTrackLessonStatus);
        
        StringBuffer sb = new StringBuffer();
		sb.append("error=0").append("\n");
		sb.append("error_text=Successful").append("\n");
		mv.addObject("result", sb.toString());
        
	}

	private void handlePutParamRequest(String command, String sessKey, String aiccData, ModelAndView mv){
		
		Course course = commonDao.getCourseById(Long.valueOf(courseId));
		Member member = commonDao.getMemberById(Long.valueOf(memberId));
		
		Hashtable<String,String> aiccDataMap = new Hashtable<String,String>();
		BufferedReader br = null;
        try {
            String sCurrentLine;
            br = new BufferedReader(new StringReader(aiccData));
            while ((sCurrentLine = br.readLine()) != null) {
                System.out.println("Current Line --> " + sCurrentLine);
                if(sCurrentLine != null && sCurrentLine.indexOf("=") > -1){
                	int equalIndex = sCurrentLine.indexOf("=");
        			if(equalIndex > -1){
        				String keyData = sCurrentLine.substring(0,equalIndex).trim();
        				String valueData = sCurrentLine.substring(equalIndex + 1).trim();
        				aiccDataMap.put(keyData, valueData);
        			}
                }else if(sCurrentLine.indexOf("Core_Lesson") > -1){
                	sCurrentLine = br.readLine();
                	aiccDataMap.put("Suspended_Data", sCurrentLine);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        System.out.println("aiccDataMap ::- " + aiccDataMap);
        List<CourseTrack> courseTracks = new ArrayList<CourseTrack>();
        for(Entry<String, String> entry : aiccDataMap.entrySet()){
        	if(ScormUtil.getKeyForAICCElement(entry.getKey()) != null){
            	CourseTrack courseTrack = scormDao.getCourseTrack(member.getMemberId(), course.getId(), member.getOrganization().getId(), ScormUtil.getKeyForAICCElement(entry.getKey()));
            	if(courseTrack == null){
                	courseTrack = new CourseTrack();
                	courseTrack.setCourse(course);
                	courseTrack.setMember(member);
                	courseTrack.setOrganization(member.getOrganization());
            	}
            	courseTrack.setAttempt(1);
            	courseTrack.setElement(ScormUtil.getKeyForAICCElement(entry.getKey()));
            	courseTrack.setValue((ScormUtil.getKeyForAICCElement(entry.getKey()).equalsIgnoreCase("cmi.core.lesson_status") ? ScormUtil.getKeyForAICCLessonStatusValue(entry.getValue()) : entry.getValue() ));
            	System.out.println("Key/Value ::- " + entry.getKey()+"/"+(ScormUtil.getKeyForAICCElement(entry.getKey()).equalsIgnoreCase("cmi.core.lesson_status") ? ScormUtil.getKeyForAICCLessonStatusValue(entry.getValue()) : entry.getValue() ));
            	scormDao.saveUpdateCourseTrack(courseTrack);
        	}
        }
		StringBuffer sb = new StringBuffer();
		sb.append("error=0").append("\n");
		sb.append("error_text=Successful").append("\n");
		mv.addObject("result", sb.toString());
        
	}
	
	private void handleGetParamRequest(String command, String sessKey, String aiccData, ModelAndView mv){
		
		Course course = commonDao.getCourseById(Long.valueOf(courseId));
		Member member = commonDao.getMemberById(Long.valueOf(memberId));

		List<CourseTrack> courseTracks = scormDao.getCourseTracks(member.getMemberId(), course.getId(), member.getOrganization().getId());

		ScormData scormData = ScormUtil.createScormData(course, member, courseTracks);
		

		StringBuffer sb = new StringBuffer();
		sb.append("error=0").append("\n");
		sb.append("error_text=Successful").append("\n");
		sb.append("aicc_data=[Core]").append("\n");
		sb.append("Student_ID="+scormData.getMemberId()).append("\n");
		sb.append("Student_Name="+scormData.getMemberName()).append("\n");
		sb.append("Lesson_Location="+scormData.getLessionLocation()).append("\n");
		sb.append("Credit=credit").append("\n");
		sb.append("Lesson_Status=incomplete").append("\n");
		sb.append("Score="+scormData.getScoreRaw()).append("\n");
		sb.append("Time="+scormData.getTotalTime()).append("\n");
		sb.append("Lesson_Mode=normal").append("\n");
		sb.append("[Core_Lesson]").append("\n");
		sb.append(scormData.getSuspendedData()).append("\n");
		sb.append("[Core_Vendor]").append("\n");
		sb.append("[Evaluation]").append("\n");
		sb.append("Course_ID = " + scormData.getCourseName()).append("\n");
		sb.append("[Student_Data]").append("\n");
		sb.append("Mastery_Score=" + scormData.getMasteryScore()).append("\n");
		sb.append("Max_Time_Allowed="+scormData.getMaxTimeAllowed()).append("\n");
		sb.append("Time_Limit_Action=").append("\n");
		mv.addObject("result", sb.toString());
	}
	
	private void createCourseScoreBoard(CourseTrack scoreTrack,CourseTrack lessonStatusTrack){
		ScoreBoardCourse courseScoreBoard = scormDao.getCourseScoreBoard(Long.valueOf(memberId) , Long.valueOf(courseId), Long.valueOf(organizationId));
		if(courseScoreBoard == null){
			courseScoreBoard = new ScoreBoardCourse();
			courseScoreBoard.setTotalTryOrHits(1);
			courseScoreBoard.setMemberId(Long.valueOf(memberId));
			courseScoreBoard.setOrganizationId(Long.valueOf(organizationId));
			courseScoreBoard.setCourseId(Long.valueOf(courseId));
		}
		courseScoreBoard.setTotalTryOrHits(courseScoreBoard.getTotalTryOrHits() + 1);
		courseScoreBoard.setScore(Double.valueOf(scoreTrack.getValue()));
		courseScoreBoard.setStatus(lessonStatusTrack.getValue());
		if(lessonStatusTrack.getValue().equalsIgnoreCase("incomplete")){
			CourseTrack suspendedDataCourseTrack = scormDao.getCourseTrack(Long.valueOf(memberId) , Long.valueOf(courseId), Long.valueOf(organizationId), "cmi.suspend_data");
			String suspendedDataString = suspendedDataCourseTrack.getValue();
			if(suspendedDataString.indexOf("|") > -1){
				String viewedSlide =suspendedDataString.substring(0, suspendedDataString.indexOf("|"));
				String totalSlide = suspendedDataString.substring(suspendedDataString.indexOf("|"));
				int viewedSlideCount = viewedSlide.split(",").length;
				int totalSlideCount = totalSlide.split(",").length;
				System.out.println("Viewed Slide ::- " + viewedSlide + " , Viewed Count ::- " + viewedSlideCount);
				System.out.println("Total Slide ::- " + totalSlide + " , Viewed Count ::- " + totalSlideCount);
				double percentComplete = (((double)viewedSlideCount/totalSlideCount)*100);
				courseScoreBoard.setPercentComplete(percentComplete);
			}
		}
		scormDao.saveUpdateCourseScoreBoard(courseScoreBoard);
	}
}


