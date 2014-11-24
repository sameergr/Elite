package com.innverse.elearn.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.innverse.elearn.bo.AICCParser;
import com.innverse.elearn.config.Config;
import com.innverse.elearn.contants.Constants;
import com.innverse.elearn.eenum.CourseType;
import com.innverse.elearn.eenum.MediaType;
import com.innverse.elearn.eenum.PlanType;
import com.innverse.elearn.eenum.SubscriptionType;
import com.innverse.elearn.json.FollowFriendData;
import com.innverse.elearn.json.GroupForm;
import com.innverse.elearn.json.LevelForm;
import com.innverse.elearn.json.MediaFileForm;
import com.innverse.elearn.json.MemberForm;
import com.innverse.elearn.json.ScormFileForm;
import com.innverse.elearn.json.SeriesForm;
import com.innverse.elearn.json.UserForm;
import com.innverse.elearn.model.Category;
import com.innverse.elearn.model.Content;
import com.innverse.elearn.model.Course;
import com.innverse.elearn.model.Deck;
import com.innverse.elearn.model.DeckCourse;
import com.innverse.elearn.model.Friend;
import com.innverse.elearn.model.Group;
import com.innverse.elearn.model.Level;
import com.innverse.elearn.model.MediaFile;
import com.innverse.elearn.model.Member;
import com.innverse.elearn.model.Organization;
import com.innverse.elearn.model.ScoreBoard;
import com.innverse.elearn.model.Series;
import com.innverse.elearn.model.UploadedScorm;
import com.innverse.elearn.util.TokenGenerator;
import com.innverse.elearn.util.UnZip;

@Controller
@SessionAttributes
@RequestMapping("/org")
public class OrganizationController extends AbstractController{
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView oragnizationPage(){
		ModelAndView mv = new ModelAndView("organization_Page");
		return mv;
	}

	@RequestMapping(value="/members", method = RequestMethod.GET)
	public ModelAndView myGroup(HttpServletRequest request, HttpServletResponse response,HttpSession session){
		ModelAndView mv = new ModelAndView();
		Member member = (Member)session.getAttribute("member");
		List<Member> members =  commonService.getAllMember(member.getOrganization().getId());
		mv.addObject("memberList" ,members);
		mv.setViewName("reg_my_member");
		return mv ;
	}
	
	@RequestMapping(value="/memberDetails/{id}*", method = RequestMethod.GET)
	public ModelAndView memberDetails(@PathVariable("id") String id, HttpServletRequest request, HttpServletResponse response,HttpSession session){		
		ModelAndView mv = new ModelAndView();
		Member memberDetail = commonService.getMemberById(Long.valueOf(id));
		mv.addObject("details", memberDetail);		
		mv.setViewName("reg_member_details");
		return mv ;
	}
		
	@RequestMapping(value="/deleteMember/{id}*", method = RequestMethod.GET)
	public ModelAndView deleteMember(@PathVariable("id") String id, HttpSession session){		
		ModelAndView mv = new ModelAndView();
		commonService.deleteMemberById(id);		
		Member member = (Member)session.getAttribute("member");
		List<Member> members =  commonService.getAllMember(member.getOrganization().getId());
		if(members!=null)
			mv.addObject("memberList" ,member);
		mv.setViewName("reg_my_member");		
		return mv ;
	}
	
	@RequestMapping(value="/learningGroup", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView learningGroup(HttpServletRequest request, HttpServletResponse response,HttpSession session){	
		ModelAndView mv = new ModelAndView();
		Member member = (Member)session.getAttribute("member");
		List<Group> group = commonService.getAllGroups(String.valueOf(member.getOrganization().getId()));
		if( group.size() > 0 ){
			mv.addObject("group", group);		
			mv.setViewName("reg_learning_group");
		}
		else{
			mv.addObject("err", Constants.NO_GROUP);
			mv.setViewName("reg_learning_group");
		}
		return mv;
	}
	
	@RequestMapping(value="/createNewGroup", method = RequestMethod.GET)
	public ModelAndView createNewGroup(HttpServletRequest request, HttpServletResponse response,HttpSession session){	
		ModelAndView mv = new ModelAndView();	
		Member member = (Member)session.getAttribute("member");
		List<Member> members =  commonService.getAllMember(member.getOrganization().getId());
		if(members.size() > 0){
			mv.addObject("memberList" ,member);
			mv.setViewName("reg_create_new_group");
			return mv ;
		}
		else{
			mv.addObject("err", Constants.NO_MEMBER);
			mv.setViewName("reg_create_new_group");
			return mv ;
		}
	}
	
	@RequestMapping(value="/saveNewGroup", method = RequestMethod.POST)
	public ModelAndView saveNewGroup(@ModelAttribute("saveGroups") GroupForm groupForm, HttpServletRequest req,HttpSession session){
		ModelAndView mv = new ModelAndView();
		commonService.saveGroup(groupForm, session);
		Member member = (Member)session.getAttribute("member");
		List<Group> group = commonService.getAllGroups(String.valueOf(member.getOrganization().getId()));
		if( group.size() > 0 ){
			mv.addObject("group", group);		
			mv.setViewName("reg_learning_group");
		}
		else{
			mv.addObject("err", Constants.NO_GROUP);
			mv.setViewName("reg_learning_group");
		}
		return mv;
	}
	
	@RequestMapping(value="/learningGroup/{id}", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView groupDetails(@PathVariable("id") String groupId , HttpServletRequest request, HttpServletResponse response,HttpSession session){		
		ModelAndView mv = new ModelAndView();
		Group gId = commonService.groupDetailById(groupId);		
		mv.addObject("gDetail", gId);
		mv.setViewName("reg_view_group_deatils");
		return mv ;
	}
	
	@RequestMapping(value="/editGroupDetail/{id}", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView editGroupDetail(@PathVariable("id") String groupId , @ModelAttribute("saveGroups") GroupForm saveGroups, HttpServletRequest req,HttpSession session){
		ModelAndView mv = new ModelAndView();
		Member member = (Member)session.getAttribute("member");
		Group gId = commonService.groupDetailById(groupId);
		List<Member> allUserMembers = commonService.getAllMember(member.getOrganization().getId());
		Set<Member> rest = new HashSet<Member>();
			int i=0;
				for(Member  fromMember: allUserMembers){
					for(Member fromGroup : gId.getMembers()){
						
						if(fromGroup.getMemberId()==fromMember.getMemberId()){
							i++;
							}
						}
					if(i==0){
						rest.add(fromMember);
						}
					i=0;
					}
		mv.addObject("gDetail", gId);
		mv.addObject("allUserMembers", rest);
		mv.setViewName("reg_user_editGroupDetail");
		return mv;
	}
	
	@RequestMapping(value="/saveEditGroup/{id}", method = RequestMethod.POST)
	public ModelAndView saveEditGroup(@PathVariable("id") String groupID, @ModelAttribute("saveEditGroup") GroupForm saveGroups, HttpServletRequest req,HttpSession session){
		ModelAndView mv = new ModelAndView();
			if(saveGroups!=null){
				commonService.saveEditGroup(saveGroups, groupID);
				}
			else{
				mv.addObject("err", Constants.MEMBER_AND_GROUP_NOTEMPTY);
				mv.setViewName("reg_learning_group");
			}
		Member member = (Member)session.getAttribute("member");
		List<Group> group = commonService.getAllGroups(String.valueOf(member.getOrganization().getId()));
		if( group.size() > 0 ){
			mv.addObject("group", group);		
			mv.setViewName("reg_learning_group");
			}
		return mv;
	}
	
	@RequestMapping(value="/deleteGroup/{id}", method ={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView deleteGroup(@PathVariable("id") String groupId, HttpServletRequest req,HttpSession session){
		ModelAndView mv = new ModelAndView();	// Remove all members of group but not group but if refresh the page only then it deletes group also.
		if(groupId!=null){						// 7-feb-2014
			commonService.deleteGroup(groupId);
		}
		Member member = (Member)session.getAttribute("member");
		List<Group> groupList = commonService.getAllGroups(String.valueOf(member.getOrganization().getId()));
		if(groupList != null){
			mv.addObject("group", groupList);
		}
		mv.setViewName("reg_learning_group");
		return mv;
	}

	@RequestMapping(value="/inviteFriends", method = RequestMethod.GET)
	public String invitationPage(){
		return "reg_invite_Friend" ;
	}

	@RequestMapping(value="/viewContent", method = RequestMethod.GET)
	public ModelAndView getDisplayContentPage(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("reg_displaycontent");
		List<Content> allContents = commonService.getAllContents();
		mv.addObject("contents",allContents);
		return mv;
	}

	@RequestMapping(value="/createSeries", method = RequestMethod.GET)
	 public ModelAndView createSeries(@ModelAttribute("createcSeries") SeriesForm series,HttpServletRequest request, HttpServletResponse response){
	  ModelAndView mv = new ModelAndView("org_createSeries-page");
	  List<Category> categoryList = commonService.getAllMainCategories();
	  mv.addObject("categoryList",categoryList);
	  List<Category> subCategoryList = commonService.getAllSubCategories();
	  mv.addObject("subCategoryList",subCategoryList);
	  return mv;
	 }
	
	@RequestMapping(value="/saveSeries", method = {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView getSeries(@ModelAttribute("createcSeries") SeriesForm series, HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		Member member = (Member)session.getAttribute("member");
		long seriesId = commonService.insertSeries(series, request,session);
		Series series2 = commonService.getSeriesById(seriesId, member.getOrganization().getId());
		List<Content> contentList = commonService.getAllQuestions(member.getAccount(),series2.getCategory().getId());
		List<Category> subCategories = commonService.getSubCategories(series2.getCategory().getParentCategory().getId());
		ModelAndView modelAndView = new ModelAndView("reg_teacher_seriesLevel");
		modelAndView.addObject("series", series2);
		modelAndView.addObject("question", contentList);
		modelAndView.addObject("subCategories", subCategories);
		return modelAndView;
	}
	
	@RequestMapping(value="/createLevel", method = {RequestMethod.POST})
	 public ModelAndView getLevels(@ModelAttribute("levelForm") LevelForm level,HttpServletRequest request, HttpServletResponse response
			 ,HttpSession session){
		commonService.insertLevel(level);
	  	Member member = (Member)request.getSession(false).getAttribute("member");
		Series series2 = commonService.getSeriesById(Long.valueOf(level.getSeriesId()), member.getOrganization().getId());
		session.setAttribute("series2", series2.getCategory().getId());
		
		List<Content> questionsList = commonService.getAllQuestions(member.getAccount(),series2.getCategory().getId());
		List<Category> subCategories = commonService.getSubCategories(series2.getCategory().getParentCategory().getId());
		ModelAndView modelAndView = new ModelAndView("reg_teacher_seriesLevel");
		modelAndView.addObject("series", series2);
		modelAndView.addObject("subCategories", subCategories);
		modelAndView.addObject("questions", questionsList);
		List<Content> contentList = commonService.getAllQuestions(member.getAccount(),series2.getCategory().getId());
		modelAndView.addObject("question", contentList);
	  return modelAndView;
	 }
	
	@RequestMapping(value="/viewSeries", method = RequestMethod.GET)
	public ModelAndView viewSeries(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		Member member = (Member)session.getAttribute("member");
		List<Series> seriesList = commonService.getAllSeriesByOrgId(member.getOrganization().getId());
		ModelAndView modelAndView = new ModelAndView("reg_view_series");
		modelAndView.addObject("seriesList", seriesList);
		return modelAndView;
	}
	
	@RequestMapping(value="/updateSeries", method ={RequestMethod.GET,RequestMethod.POST})
	 public ModelAndView updateSeries(@ModelAttribute("updateSeries") SeriesForm seriesForm,HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		Member member = (Member)session.getAttribute("member");
		List<Series> seriesList = commonService.getAllSeriesByOrgId(member.getOrganization().getId());
		commonService.updateSeriesDetail(seriesForm);
		ModelAndView modelAndView = new ModelAndView("reg_view_series");
		modelAndView.addObject("seriesList", seriesList);
		return modelAndView;
	}
	
	@RequestMapping(value="/deleteSeries/{id}", method ={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView deleteSeries(@PathVariable("id") String Id, HttpServletRequest request){
		commonService.deleteSeries(Long.valueOf(Id));
		HttpSession session = request.getSession();
		Member member = (Member)session.getAttribute("member");
		List<Series> seriesList = commonService.getAllSeriesByOrgId(member.getOrganization().getId());
		ModelAndView modelAndView = new ModelAndView("reg_view_series");
		modelAndView.addObject("seriesList", seriesList);
		return modelAndView;
	}
	
	@RequestMapping(value="/editSeriesDetail/{id}", method ={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView editSeriesDetail(@PathVariable("id") String Id, HttpServletRequest req,HttpSession session){
		List<Series> editSeries= commonService.editSeriesDetail(Long.valueOf(Id));
		ModelAndView modelAndView = new ModelAndView("reg_edit_SeriesDetails");
		modelAndView.addObject("seriesList", editSeries);
		return modelAndView;
	}
	
	@RequestMapping(value="/viewSeriesDetail/{id}", method ={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView viewSeriesDetail(@PathVariable("id") String viewId, HttpServletRequest req,HttpSession session){
		Series series= commonService.getSeriesDetailById(Long.valueOf(viewId));
		ModelAndView modelAndView = new ModelAndView("reg_view_SeriesDetails");
		modelAndView.addObject("series", series);
		return modelAndView;
	}

	@RequestMapping(value="/memberEdit/{id}", method = RequestMethod.GET)
	public ModelAndView memberEdit(@PathVariable("id") String id){
		ModelAndView mv = new ModelAndView();
		Member member = commonService.getMemberById(Long.valueOf(id));
		Organization organization = commonService.getOrganizationById(member.getOrganization().getId());
		mv.addObject("mFALPermission", organization.getMemberFALPermissions());
		mv.addObject("member", member);
		mv.addObject("is", member.isActive());
		mv.setViewName("reg_member_edit");
		return mv ;
	}

	@RequestMapping(value="/selectsubcategory/{pid}", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView getsubCategories(@PathVariable("pid") String subId,HttpServletRequest request, HttpServletResponse response){
		ModelAndView modelAndView = new ModelAndView("reg_user_seriesLevel");
		List<Category> series1=commonService.getAllSubCategories((subId));
		modelAndView.addObject("allSubCategory",series1);
		return modelAndView;
	}
	
	@RequestMapping(value = "/createNewDeck", method = RequestMethod.GET)
	public ModelAndView createDeck(HttpServletRequest req, HttpSession session){
		ModelAndView mv = new ModelAndView("create_deck");
		Member member = (Member)session.getAttribute("member");
		try{
		List<Group> groupList = commonService.getAllGroups( String.valueOf(member.getOrganization().getId()) );
		List<Series> seriesList = commonService.getAllSeriesByOrgId(member.getOrganization().getId());
		List<Course> coursesList = commonService.getAllCourses(member.getOrganization().getId());
		mv.addObject("seriesList", seriesList);
		mv.addObject("coursesList", coursesList);
		mv.addObject("groupList", groupList);
		return mv;
		}
		catch(EntityNotFoundException e){
			mv.setViewName("view_deck");
			mv.addObject("err", Constants.CREATE_SERIES_COURSE_GROUP);
			return mv;
		}
	}
	
	@RequestMapping(value="/testCenter", method ={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView testCenter(HttpServletRequest req, HttpSession session){
		ModelAndView modelAndView = new ModelAndView("guest_user_test_center");		
		Member member = (Member)session.getAttribute("member");
		Set<Group> memberGroups = member.getGroups();  
		List<Deck> groupDeck = null;
		Set<List<Deck>> memberDecksList = new HashSet<List<Deck>>();
		List<DeckCourse> groupCourseList = null;
		Set<List<DeckCourse>> deckCourseSet = new HashSet<List<DeckCourse>>();
		for(Group g : memberGroups){
			groupDeck = commonService.getDeckListbyGroupId(g.getId());
			groupCourseList = commonService.getDeckCoursesbyGroupId(g.getId());
			memberDecksList.add(groupDeck);
			deckCourseSet.add(groupCourseList);
		}
		List<Deck> memberDeck = new ArrayList<Deck>();;
		for(List<Deck> memberDecks : memberDecksList){
			for(Deck deck : memberDecks){
				memberDeck.add(deck);
			}
		}
		List<DeckCourse> coursesList = new ArrayList<DeckCourse>();
		for(List<DeckCourse> courseList : deckCourseSet){
			for(DeckCourse course : courseList){
				coursesList.add(course);
			}
		}
		modelAndView.addObject("memberDeck", memberDeck);
		modelAndView.addObject("courseList", coursesList);
		return modelAndView;
	}
	
	@RequestMapping(value="/testCenterSeries/{id}", method ={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView testCenter(@PathVariable("id") String seriesId, HttpServletRequest req, HttpSession session){
		ModelAndView modelAndView = new ModelAndView("guest_user_test_center_series");		
		Member member = (Member)session.getAttribute("member");
		Series series = commonService.getSeriesById(Long.valueOf(seriesId),member.getOrganization().getId());
		Hashtable scoreMap = new Hashtable();
		for(Level level : series.getLevels()){
			ScoreBoard scoreBoard = commonService.getScoreBoard(level.getId(), member.getMemberId(), member.getOrganization().getId());
			scoreMap.put(level.getId(), scoreBoard);
		}
		modelAndView.addObject("scoreMap", scoreMap);
		System.out.println("Series by id :"+ series.getSeriesName() +" level :" +series.getLevels());
		modelAndView.addObject("series", series);
		modelAndView.addObject("levels", series.getLevels());
		boolean preview = (member.getOrganization()!=null ? true : false);
		modelAndView.addObject("prview", preview);
		return modelAndView;
	}
	
	@RequestMapping(value="playLevel/{id}", method ={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView playLevel(@PathVariable("id") String levelId, HttpServletRequest req, HttpSession session){
		ModelAndView modelAndView = new ModelAndView("guest_user_play_level");		
		Level level = commonService.getLevelById(levelId);
		modelAndView.addObject("level",level);
		modelAndView.addObject("levelId",level.getId());
		modelAndView.addObject("seriesId",level.getSeries().getId());
		modelAndView.addObject("questionLevelTime",level.getSeries().getQuestionLevelTiming());
		Member memberAccount = (Member)session.getAttribute("member");
		modelAndView.addObject("userId",memberAccount.getAccount().getUsername());
		return modelAndView;
	}
	
	@RequestMapping(value="viewCourse", method = RequestMethod.GET)
	public ModelAndView getViewCourse(@ModelAttribute("course") ScormFileForm scormFile, HttpServletRequest req, HttpSession session){
		ModelAndView mv = new ModelAndView("view_course_page");
		Member memberAccount = (Member)session.getAttribute("member");
		List<Course> courses = commonService.getAllCourses(memberAccount.getAccount().getOrganization().getId());
		mv.addObject("courses", courses);
		return mv;
	}

	@RequestMapping(value="createCourse", method = RequestMethod.GET)
	public ModelAndView getCreateCourse(@ModelAttribute("course") ScormFileForm scormFile, HttpServletRequest req, HttpSession session){
		ModelAndView mv = new ModelAndView("create_course_page");
		return mv;
	}

	@RequestMapping(value="createCourse", method = RequestMethod.POST)
	public ModelAndView postCreateCourse(@ModelAttribute("course") ScormFileForm scormFile, HttpServletRequest req, HttpSession session){
		ModelAndView mv = new ModelAndView("create_course_page");
		Course course = null;
		UploadedScorm scorm = null;
		TokenGenerator tg = new TokenGenerator();
		try {
			String scormFileName = scormFile.getCourseFile().getOriginalFilename();
			String scormFilePath = Config.getMsg("scorm.path");
			String uniqueKey = tg.generateUniqueKey();
			String name = scormFilePath + uniqueKey + scormFileName;
			System.out.println("FileName ::- " + name);
			MultipartFile file = scormFile.getCourseFile();
			if (!file.isEmpty()) {
	            try {
	                byte[] bytes = file.getBytes();
	                BufferedOutputStream stream =
	                        new BufferedOutputStream(new FileOutputStream(new File(name)));
	                stream.write(bytes);
	                stream.close();
	                String destinationDirectory = name.substring(0, name.indexOf(".zip")); 
	                UnZip unZip = new UnZip();
	                unZip.unZipIt(name, destinationDirectory);
	                if(scormFile.getCourseType().equals(CourseType.AICC)){
	                	try{
	                		Member memberAccount = (Member)session.getAttribute("member");
		                	AICCParser parser = new AICCParser();
		                	parser.createMetaData(destinationDirectory);
		    	            String ext = FilenameUtils.getExtension(scormFileName);
		    	            String filenameWithoutExt = scormFileName.substring(0, scormFileName.indexOf("."+ext));
		    	            filenameWithoutExt = uniqueKey + filenameWithoutExt;
	                		scorm = parser.getUploadScrom(destinationDirectory, file.getSize(), filenameWithoutExt, memberAccount.getAccount().getOrganization());
	                		scorm.setScormType(CourseType.AICC);
	                		scorm.setVersion((scorm.getVersion() == null ? "AICC" : scorm.getVersion()));
	                		
	                		course = new Course();
	                		course.setCourseName(scormFile.getCourseName());
	                		course.setCouserDescription(scormFile.getCourseDescription());
	                		course.setOrganization(memberAccount.getAccount().getOrganization());
	                		course.setUploadedScorm(scorm);
	                		course.setActive(true);
	                		course.setPublishDate(Calendar.getInstance().getTime());
	                	}catch(Exception e){
	                		e.printStackTrace();
	                	}
	                }
	                System.out.println("You successfully uploaded " + name + " into " + name + "-uploaded !");
	            } catch (Exception e) {
	            	System.out.println("You failed to upload " + name + " => " + e.getMessage());
	            }
	        } else {
	        	System.out.println("You failed to upload " + name + " because the file was empty.");
	        }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(course != null){
			commonService.saveCourse(course);
			Member memberAccount = (Member)session.getAttribute("member");
			List<Course> courses = commonService.getAllCourses(memberAccount.getAccount().getOrganization().getId());
			mv.addObject("courses", courses);
			mv.setViewName("view_course_page");
		}
		return mv;
	}
	
	@RequestMapping(value="/updateMemberDetail/{id}", method = RequestMethod.POST)
	public ModelAndView updateMemberDetail(@PathVariable("id") String id, HttpServletRequest req,HttpSession session){
		ModelAndView mv = new ModelAndView();
		if(id!=null){
			commonService.updateMemberDetail(id, req);
		}
		Member member = (Member)session.getAttribute("member");
		List<Member> members =  commonService.getAllMember(member.getOrganization().getId());
		mv.addObject("memberList" ,members);
		mv.setViewName("reg_my_member");
		return mv;
	}

	@RequestMapping(value="/createNewMember", method = RequestMethod.GET)
	public ModelAndView createNewMember(@ModelAttribute("saveNewMember") MemberForm memberForm){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("org_create_new_member");
		mv.addObject("memberRole", memberForm.getMemberRole());
		return mv;
	}
	
	@RequestMapping(value="/saveNewMember", method = RequestMethod.POST)
	public ModelAndView saveNewMember(@ModelAttribute("saveNewMember") MemberForm memberForm, HttpServletRequest req, HttpSession session){
		ModelAndView mv = new ModelAndView("org_create_new_member");
		Member member = (Member)session.getAttribute("member");
		if(memberForm!=null){
			long orgId = member.getOrganization().getId();
			commonService.saveNewMember(orgId, memberForm);
			List<Member> members =  commonService.getAllMember(member.getOrganization().getId());
			mv.addObject("memberList" ,members);
			mv.setViewName("reg_my_member");
			mv.addObject("err", "Member has created successfully.");
		}
		return mv;
	}

	@RequestMapping(value="/mediaCenter", method = RequestMethod.GET)
	public ModelAndView getMediaCenter(HttpServletRequest req){
		ModelAndView mv = new ModelAndView("mediaCenter_page");
		return mv;
	}
	
	@RequestMapping(value="/mediaData", method = {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView getElmsBook(@RequestParam("id") String id,@ModelAttribute("elmsBook") MediaFileForm mediaFile, HttpServletRequest req,HttpSession session){
		Member member = (Member)session.getAttribute("member");
		boolean isOrgnization = (member.getOrganization()!=null ? true : false);
		PlanType planType = member.getOrganization().getSubscription().getSubscriptionPlan().getPlanType();
		String plan = member.getOrganization().getSubscription().getSubscriptionPlan().getPlanName();
		SubscriptionType subscriptionType=member.getOrganization().getSubscription().getSubscriptionPlan().getSubscriptionType();
		List<MediaFile> mediaData = commonService.getAllMediaDatails();
		ModelAndView mv = new ModelAndView("elmsBook_page");
		MediaType mediaType=null;
		if(id.equals("video")){
			 mediaType=MediaType.Videos;
			}
		if(id.equals("tutorial")){
			 mediaType=MediaType.Tutorials;
			}
		if(id.equals("book")){
			 mediaType=MediaType.Books;
			}
		mv.addObject("isOrgnization", isOrgnization);
		mv.addObject("planType", planType);
		mv.addObject("plan", plan);
		mv.addObject("subscriptionType", subscriptionType);
		mv.addObject("mediaData", mediaData);
		mv.addObject("mediaType", mediaType);
		mv.addObject("id", id);
		System.out.println("ttttttt"+mediaType);
		return mv;
	}
	
	@RequestMapping(value="/elmsInsert", method =RequestMethod.POST)
	public ModelAndView postBookInsert(@ModelAttribute("elmsBook") MediaFileForm mediaFile, HttpServletRequest req, HttpSession session){
		Member member = (Member)session.getAttribute("member");
		SubscriptionType subscriptionType=member.getOrganization().getSubscription().getSubscriptionPlan().getSubscriptionType();
		long organizationId= member.getOrganization().getId();
		List<MediaFile> mediaData = commonService.getAllMediaDatails();
		Organization organizationDetails=commonService.getOrganizationById(organizationId);
		ModelAndView modelAndView = new ModelAndView();
		TokenGenerator tg = new TokenGenerator();
		String mediaFileName="";
		try{
			mediaFileName = mediaFile.getScormfile().getOriginalFilename();
			String mediaFilePath = Config.getMsg("media.path");
			String uniqueKey = tg.generateUniqueKey();
			String name = mediaFilePath + uniqueKey + mediaFileName;
			MultipartFile file=mediaFile.getScormfile();
			if(!file.isEmpty()){
				try{
					byte[] bytes=file.getBytes();
					BufferedOutputStream stream=new BufferedOutputStream(new FileOutputStream(new File(name)));
					stream.write(bytes);
					stream.close();
					System.out.println("You successfully uploaded " + name + " into " + name + "-uploaded !");
	            } catch (Exception e) {
	            	System.out.println("You failed to upload " + name + " => " + e.getMessage());
	            }
			}else {
	        	System.out.println("You failed to upload " + name + " because the file was empty.");
	        }
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("You failed to upload file..................");
		}
		commonService.saveBook(mediaFile,mediaFileName,subscriptionType,organizationDetails);
		modelAndView.addObject("mediaData", mediaData);
		modelAndView.setViewName("elmsBook_page");	
		return modelAndView;
	}
	
	@RequestMapping(value="/elmsDelete/{Id}*", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView elmsDelete(@PathVariable("Id") String Id,HttpServletRequest req,HttpSession session){
		ModelAndView mv = new ModelAndView();
		commonService.deleteMediaFileById(Id);
		List<MediaFile> mediaData = commonService.getAllMediaDatails();
		mv.addObject("mediaData", mediaData);
		mv.setViewName("elmsBook_page");		
		return mv;
	}
	
	@RequestMapping(value="/elmsEdit/{Id}*", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView elmsEdit(@PathVariable("Id") String Id,HttpServletRequest req,HttpSession session){
		ModelAndView mv = new ModelAndView();
		MediaFile mediaData=commonService.editMediaFileById(Id);
		/*List<MediaFile> mediaData = commonService.getAllMediaDatails();*/
		mv.addObject("mediaData", mediaData);
		mv.setViewName("elmsEdit_page");		
		return mv;
	}

	@RequestMapping(value="/updateMediaFile/{id}", method =RequestMethod.POST)
	public ModelAndView saveEditMediaFile(@PathVariable("id") String id, @ModelAttribute("editMediaFile") MediaFileForm mediaFile, HttpServletRequest req, HttpSession session){
		commonService.saveEditMediaFile(id, mediaFile);
		ModelAndView mv = new ModelAndView();
		//commonService.saveBook(mediaFile,mediaFileName,subscriptionType,organizationDetails);
		mv.setViewName("mediaCenter_page");
		return mv;
	}
	
	@RequestMapping(value="/elmsDownload/{id}", method ={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView elmsDownloadFile(@PathVariable("id") String id,HttpServletRequest req){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("mediaFileDownload_page");
		return mv;
	}
	
	@RequestMapping(value="/followFriend", method = RequestMethod.GET)
	public ModelAndView getfollowFriend(HttpServletRequest req,HttpSession session){
		Member member = (Member)session.getAttribute("member");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("followFriend_page");
		List<Friend> approvedFriends=commonService.getAllApprovedFriends(member);
		mv.addObject("approvedFriends", approvedFriends);
		return mv;
	}
	
	
	@RequestMapping(value="/addInviteFriend", method ={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView addFolowFriend(HttpServletRequest req,HttpSession session){
		Member member = (Member)session.getAttribute("member");
		long organizationId= member.getOrganization().getId();
		List<Member> list = commonService.getAllMember(organizationId);
		ModelAndView mv = new ModelAndView();
		mv.addObject("listOfMember", list);
		mv.setViewName("addFollowFriend_page");
		return mv;
	}
	
	
	@RequestMapping(value="/sendInvitation", method ={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView sendInvitation(@ModelAttribute("sendInvitation") FollowFriendData followFriend,HttpServletRequest req,HttpSession session){
		Member member = (Member)session.getAttribute("member");
		commonService.saveInvitation(followFriend,req);
		long organizationId= member.getOrganization().getId();
		List<Member> list = commonService.getAllMember(organizationId);
		ModelAndView mv = new ModelAndView();
		mv.addObject("listOfMember", list);
		mv.setViewName("addFollowFriend_page");
		return mv;
	}
	
	@RequestMapping(value="/approveFriend", method ={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView approveFriend(@ModelAttribute("sendInvitation") FollowFriendData followFriend,HttpServletRequest req,HttpSession session){
		Member member = (Member)session.getAttribute("member");
		List<Friend> friends=commonService.getallUnapprovedFriends(member);
		ModelAndView mv = new ModelAndView();
		mv.addObject("friends", friends);
		mv.setViewName("addApproveFriend_page");
		return mv;
	}
	
	@RequestMapping(value="/sendForApproval", method ={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView sendForApproval(@ModelAttribute("sendForApproval") FollowFriendData followFriend,HttpServletRequest req,HttpSession session){
		ModelAndView mv = new ModelAndView("addApproveFriend_page");
		Member member = (Member)session.getAttribute("member");
		for(String friend:followFriend.getMemberIds()){
			Friend friend2=commonService.getApprovedFriendsById(friend);
			commonService.updateForApproval(friend2);
		}
		List<Friend> friends=commonService.getallUnapprovedFriends(member);
		mv.addObject("friends", friends);
		return mv;
	}
	
	@RequestMapping(value="/getfollowFriend/{id}", method ={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView showfollowFriend(@PathVariable("id") String friendId,HttpServletRequest req,HttpSession session){
		Member member = (Member)session.getAttribute("member");
		ModelAndView mv = new ModelAndView("followFriend_page");
		commonService.savefollowFriend(friendId, req);
		List<Friend> approvedFriends=commonService.getAllApprovedFriends(member);
		mv.addObject("approvedFriends", approvedFriends);
		return mv;
	}
	
	@RequestMapping(value="/deleteFollowFriend/{id}", method ={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView deleteFollowFriend(@PathVariable("id") String friendId,HttpServletRequest req,HttpSession session){
		Member member = (Member)session.getAttribute("member");
		ModelAndView mv = new ModelAndView("followFriend_page");
		commonService.deleteFollowFriend(friendId);
		List<Friend> approvedFriends=commonService.getAllApprovedFriends(member);
		mv.addObject("approvedFriends", approvedFriends);
		return mv;
	}
	
	@RequestMapping(value="/removeFollowFriend/{id}", method ={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView removeFollowFriend(@PathVariable("id") String friendId,HttpServletRequest req,HttpSession session){
		Member member = (Member)session.getAttribute("member");
		ModelAndView mv = new ModelAndView("followFriend_page");
		commonService.removeFollowFriend(friendId);
		List<Friend> approvedFriends=commonService.getAllApprovedFriends(member);
		mv.addObject("approvedFriends", approvedFriends);
		return mv;
	}
	
	@RequestMapping(value = "/viewDeck", method = RequestMethod.GET)
	public ModelAndView viewDeck(HttpServletRequest req, HttpSession session){
		ModelAndView mv = new ModelAndView("view_deck");
		Member memberAccount = (Member)session.getAttribute("member");
		try{
		List<Deck> deckList = commonService.getAllDecksByOrgId(memberAccount.getAccount().getOrganization().getId());
		List<DeckCourse> deckCoursesList = commonService.getAllDecksCoursesByOrgId(memberAccount.getAccount().getOrganization().getId());
		mv.addObject("deckList", deckList);
		mv.addObject("deckCoursesList", deckCoursesList);
		return mv;
		}
		catch(EntityNotFoundException ex){
			ex.printStackTrace();
			mv.addObject("err", Constants.NO_DECK);
			return mv;
		}
	}
	
	@RequestMapping(value = "/saveNewDeck", method = RequestMethod.POST)
	public ModelAndView saveDeck(@ModelAttribute("deckDetails") UserForm userForm, HttpSession session){
		ModelAndView mv = new ModelAndView("view_deck");
		Member memberAccount = (Member)session.getAttribute("member");
		Group group = commonService.getGroupById(userForm.getGroupList());
		Series series = commonService.getSeriesById(userForm.getSeriesList());
		commonService.saveNewDeck(group, series, memberAccount.getAccount().getOrganization());
		List<Deck> deckList = commonService.getAllDecksByOrgId(memberAccount.getAccount().getOrganization().getId());
		List<DeckCourse> deckCoursesList = commonService.getAllDecksCoursesByOrgId(memberAccount.getAccount().getOrganization().getId());
		mv.addObject("deckList", deckList);
		mv.addObject("deckCoursesList", deckCoursesList);
		return mv;
	}
	
	@RequestMapping(value="saveDeckCourses", method = RequestMethod.POST)
	public ModelAndView saveDeckCourses(@ModelAttribute("saveDeckCourse") UserForm userForm, HttpServletRequest req){
		ModelAndView mv = new ModelAndView("view_deck");
		Member memberAccount = (Member)req.getSession(false).getAttribute("member");
		Group group = commonService.getGroupById(userForm.getGroupList());
		Course course = commonService.getCourseById(userForm.getCourseList());
		commonService.saveNewDeckCourse(group, course, memberAccount);
		List<DeckCourse> deckCoursesList = commonService.getAllDecksCoursesByOrgId(memberAccount.getAccount().getOrganization().getId());
		List<Deck> deckList = commonService.getAllDecksByOrgId(memberAccount.getAccount().getOrganization().getId());
		mv.addObject("deckList", deckList);
		mv.addObject("deckCoursesList", deckCoursesList);
		return mv;
	}
	
	@RequestMapping(value = "/viewDeckDetail/{id}", method = RequestMethod.GET)
	public ModelAndView viewDeckDetail(@PathVariable("id") String id,@RequestParam("type") String type, HttpServletRequest req, HttpSession session){
		ModelAndView mv = new ModelAndView("view_deck_detail_by_id");
		if(type.equalsIgnoreCase("Quiz")){
			Deck deckSeries = commonService.getDeckSeriesById(id);
			List<Level> levelList = commonService.getAllLevelBySeriesId(deckSeries.getSeries().getId());
			mv.addObject("levelList", levelList);
			mv.addObject("deckSeries", deckSeries);
			mv.addObject("type", type);
		}
		if(type.equalsIgnoreCase("course")){
			DeckCourse deckCourse = commonService.getDeckCourseById(id);
			mv.addObject("deckCourse", deckCourse);
			mv.addObject("type", type);
		}
		return mv;
	}
	
	@RequestMapping(value = "/deleteDeck/{id}", method = RequestMethod.GET)
	public ModelAndView deleteDeck(@PathVariable("id") String id,@RequestParam("type") String type, HttpServletRequest req, HttpSession session){
		ModelAndView mv = new ModelAndView();
		if(type.equalsIgnoreCase("quiz")){
			commonService.deleteDeckSeriesById(id);
		}
		if(type.equalsIgnoreCase("course")){
			commonService.deleteDeckCourseById(id);
		}
		Member memberAccount = (Member)session.getAttribute("member");
		try{
		List<Deck> deckList = commonService.getAllDecksByOrgId(memberAccount.getAccount().getOrganization().getId());
		List<DeckCourse> deckCoursesList = commonService.getAllDecksCoursesByOrgId(memberAccount.getAccount().getOrganization().getId());
		mv.addObject("deckList", deckList);
		mv.addObject("deckCoursesList", deckCoursesList);
		mv.setViewName("view_deck");
		return mv;
		}
		catch(EntityNotFoundException ex){
			ex.printStackTrace();
			mv.addObject("err", Constants.NO_DECK);
			mv.setViewName("view_deck");
			return mv;
		}
	}
}