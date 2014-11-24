package com.innverse.elearn.scorm;

import java.util.List;

import com.innverse.elearn.model.Course;
import com.innverse.elearn.model.CourseTrack;
import com.innverse.elearn.model.Member;
import com.innverse.elearn.pojo.ScormData;

public class ScormUtil {

	
	public static ScormData createScormData(Course course, Member member, List<CourseTrack> courseTracks){
		ScormData scormData = new ScormData();
		scormData.setMemberId(member.getMemberId());
		scormData.setMemberName(member.getAccount().getLastName() + " ," + member.getAccount().getFirstName());
		scormData.setCourseId(course.getId());
		scormData.setCourseName(course.getCourseName());
		if(courseTracks != null){
			for (CourseTrack courseTrack : courseTracks) {
				System.out.println("ELEMENT/VALUE ---> " + courseTrack.getElement()+"/"+courseTrack.getValue());
				if(courseTrack.getElement().equalsIgnoreCase("x.start.time")){
					scormData.setStartTime(courseTrack.getValue());
				}else if(courseTrack.getElement().equalsIgnoreCase("cmi.core.lesson_location")){
					scormData.setLessionLocation(courseTrack.getValue());
				}else if(courseTrack.getElement().equalsIgnoreCase("cmi.core.lesson_status")){
					scormData.setLessionStatus(courseTrack.getValue());
				}else if(courseTrack.getElement().equalsIgnoreCase("cmi.core.exit")){
					scormData.setEntry(courseTrack.getValue());
				}else if(courseTrack.getElement().equalsIgnoreCase("cmi.core.total_time")){
					scormData.setTotalTime(courseTrack.getValue());
				}else if(courseTrack.getElement().equalsIgnoreCase("cmi.core.score.max")){
					scormData.setCmiCoreScoreMax(Double.valueOf(courseTrack.getValue()));
				}else if(courseTrack.getElement().equalsIgnoreCase("cmi.core.score.min")){
					scormData.setCmiCoreScoreMin(Double.valueOf(courseTrack.getValue()));
				}else if(courseTrack.getElement().equalsIgnoreCase("cmi.suspend_data")){
					scormData.setSuspendedData(courseTrack.getValue());
				}else if(courseTrack.getElement().equalsIgnoreCase("cmi.core.session_time")){
					scormData.setSessionTime(courseTrack.getValue());
				}else if(courseTrack.getElement().equalsIgnoreCase("cmi.comments")){
					scormData.setCmiComments(courseTrack.getValue());
				}else if(courseTrack.getElement().equalsIgnoreCase("cmi.core.score.raw")){
					scormData.setScoreRaw(Double.valueOf(courseTrack.getValue()));
				}else if(courseTrack.getElement().equalsIgnoreCase("score.raw")){
					scormData.setScoreRaw(Double.valueOf(courseTrack.getValue()));
				}
			}
		}
		return scormData;
	}
	
	public static String getKeyForAICCElement(String aiccElement){
		if(aiccElement.equalsIgnoreCase("Score")){
			return "cmi.core.score.raw";
		}else if(aiccElement.equalsIgnoreCase("Lesson_Location")){
			return "cmi.core.lesson_location";
		}else if(aiccElement.equalsIgnoreCase("Lesson_Status")){
			return "cmi.core.lesson_status";
		}else if(aiccElement.equalsIgnoreCase("Lesson_Status")){
			return "cmi.core.lesson_status";
		}else if(aiccElement.equalsIgnoreCase("Suspended_Data")){
			return "cmi.suspend_data";
		}else if(aiccElement.equalsIgnoreCase("Time")){
			return "cmi.core.total_time";
		}
		return null;
	}
	
	public static String getKeyForAICCLessonStatusValue(String aiccValue){
		if("passed".equalsIgnoreCase(aiccValue)){
			return "passed";
		}else if("completed".equalsIgnoreCase(aiccValue)){
			return "completed";
		}else if("failed".equalsIgnoreCase(aiccValue)){
			return "failed";
		}else if("incomplete".equalsIgnoreCase(aiccValue)){
			return "incomplete";
		}else if("browsed".equalsIgnoreCase(aiccValue)){
			return "browsed";
		}else if("browsed".equalsIgnoreCase(aiccValue)){
			return "browsed";
		}else if("not attempted".equalsIgnoreCase(aiccValue)){
			return "not attempted";
		}else if("p".equalsIgnoreCase(aiccValue)){
			return "passed";
		}else if("c".equalsIgnoreCase(aiccValue)){
			return "completed";
		}else if("f".equalsIgnoreCase(aiccValue)){
			return "failed";
		}else if("i".equalsIgnoreCase(aiccValue)){
			return "incomplete";
		}else if("b".equalsIgnoreCase(aiccValue)){
			return "browsed";
		}else if("n".equalsIgnoreCase(aiccValue)){
			return "not attempted";
		}
		return "completed";
	}

	public static void saveCourseTrack(Course course, Member member, List<CourseTrack> courseTracks){
		
		for (CourseTrack courseTrack : courseTracks) {
			System.out.println("ELEMENT/VALUE ---> " + courseTrack.getElement()+"/"+courseTrack.getValue());
			if(courseTrack.getElement().equalsIgnoreCase("x.start.time")){
				
			}else if(courseTrack.getElement().equalsIgnoreCase("cmi.core.lesson_location")){
				
			}else if(courseTrack.getElement().equalsIgnoreCase("cmi.core.lesson_status")){
				
			}else if(courseTrack.getElement().equalsIgnoreCase("cmi.core.exit")){
				
			}else if(courseTrack.getElement().equalsIgnoreCase("cmi.core.total_time")){
				
			}else if(courseTrack.getElement().equalsIgnoreCase("cmi.core.score.max")){
				
			}else if(courseTrack.getElement().equalsIgnoreCase("cmi.core.score.min")){
				
			}else if(courseTrack.getElement().equalsIgnoreCase("cmi.suspend_data")){
				
			}else if(courseTrack.getElement().equalsIgnoreCase("cmi.core.session_time")){
				
			}else if(courseTrack.getElement().equalsIgnoreCase("cmi.comments")){
				
			}else if(courseTrack.getElement().equalsIgnoreCase("cmi.core.score.raw")){
				
			}else if(courseTrack.getElement().equalsIgnoreCase("score.raw")){
				
			}
				
		}
	}
	
}
