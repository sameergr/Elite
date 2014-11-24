package com.innverse.elearn.services;


import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.innverse.elearn.dao.CommonDaoImpl;
import com.innverse.elearn.eenum.AnswerType;
import com.innverse.elearn.eenum.MemberRole;
import com.innverse.elearn.eenum.ProficiencyType;
import com.innverse.elearn.eenum.RegistrationStatus;
import com.innverse.elearn.eenum.RegistrationType;
import com.innverse.elearn.eenum.RoleType;
import com.innverse.elearn.eenum.SubscriptionMode;
import com.innverse.elearn.eenum.SubscriptionType;
import com.innverse.elearn.eenum.ViewType;
import com.innverse.elearn.json.AddQuestions;
import com.innverse.elearn.json.CreateNewRole;
import com.innverse.elearn.json.CreateNewRoleForm;
import com.innverse.elearn.json.EditSubscriptionForm;
import com.innverse.elearn.json.FeatureAccessLevelForm;
import com.innverse.elearn.json.FollowFriendData;
import com.innverse.elearn.json.GenrateAutoSeriesForm;
import com.innverse.elearn.json.GroupForm;
import com.innverse.elearn.json.LevelForm;
import com.innverse.elearn.json.MediaFileForm;
import com.innverse.elearn.json.MemberForm;
import com.innverse.elearn.json.QuestionAnswer;
import com.innverse.elearn.json.SaveUserQueAns;
import com.innverse.elearn.json.SeriesForm;
import com.innverse.elearn.json.SubscriptionPlanForm;
import com.innverse.elearn.json.UserForm;
import com.innverse.elearn.mail.ContactUsResquestMail;
import com.innverse.elearn.mail.Testmail;
import com.innverse.elearn.model.Address;
import com.innverse.elearn.model.AdminAccount;
import com.innverse.elearn.model.Answer;
import com.innverse.elearn.model.Blog;
import com.innverse.elearn.model.Category;
import com.innverse.elearn.model.ContactRequest;
import com.innverse.elearn.model.Content;
import com.innverse.elearn.model.Course;
import com.innverse.elearn.model.Deck;
import com.innverse.elearn.model.DeckCourse;
import com.innverse.elearn.model.Feature;
import com.innverse.elearn.model.FeatureAccessLevel;
import com.innverse.elearn.model.Friend;
import com.innverse.elearn.model.Group;
import com.innverse.elearn.model.Invitation;
import com.innverse.elearn.model.Level;
import com.innverse.elearn.model.MediaFile;
import com.innverse.elearn.model.Member;
import com.innverse.elearn.model.MemberFALPermission;
import com.innverse.elearn.model.MultipleChoiceAnswer;
import com.innverse.elearn.model.Organization;
import com.innverse.elearn.model.Permission;
import com.innverse.elearn.model.Question;
import com.innverse.elearn.model.Role;
import com.innverse.elearn.model.ScoreBoard;
import com.innverse.elearn.model.ScoreBoardCourse;
import com.innverse.elearn.model.Series;
import com.innverse.elearn.model.SubjectiveAnswer;
import com.innverse.elearn.model.Subscription;
import com.innverse.elearn.model.SubscriptionPlan;
import com.innverse.elearn.model.TextQuestion;
import com.innverse.elearn.model.UploadedScorm;
import com.innverse.elearn.model.UserAccount;
import com.innverse.elearn.model.UserPayment;
import com.innverse.elearn.model.UserProfile;
import com.innverse.elearn.model.WorkProfile;
import com.innverse.elearn.model.YesNoAnswer;
import com.innverse.elearn.pojo.CategoryMap;
import com.innverse.elearn.util.CategoryMapUtil;
import com.innverse.elearn.util.DateTimeUtils;
import com.innverse.elearn.util.TokenGenerator;


@services
public class CommonServiceImpl {

	@Autowired
	CommonDaoImpl commonDao;
	
	@Autowired
	Testmail testmail;
	
	public UserAccount registerUser(UserForm userForm, long planId, String registrationType){

		UserProfile userProfile = new UserProfile();
		userProfile.setUsername(userForm.getUsername());
		userProfile.setFirstName(userForm.getFirstName());
		userProfile.setLastName(userForm.getLastName());
		userProfile.setPhone(userForm.getPhone());
		userProfile.setActive(true);
		userProfile.setRoleType(RoleType.USER);
		userProfile.setHowDoYouKnowAboutSite(userForm.getHowDoYouKnowAboutSite());
		Subscription subscription = new Subscription();
		if(!(userForm.getInviteeEmailAddress()).equals("")){
			userProfile.setEmail(userForm.getInviteeEmailAddress());
		}
		else{
			userProfile.setEmail(userForm.getEmail());	
		}
		if(!registrationType.equalsIgnoreCase("FREE")){
			SubscriptionPlan userSubscriptionPlan = commonDao.getSubscriptionPlanById(planId);
			
			subscription.setSubscriptionPlan(userSubscriptionPlan);
			
			Organization organization = new Organization();
			organization.setName(userForm.getName());
			organization.setLogoURL(userForm.getLogoURL());
			organization.setSubscription(subscription);
			organization.setRegistrationStatus(RegistrationStatus.PENDING);
			
			userProfile.setOrganization(organization);
		}	
		
		UserAccount userAccount = new UserAccount();
		userAccount.setUsername(userForm.getUsername());
		userAccount.setPassword(userForm.getPassword());
		userAccount.setUserProfile(userProfile);
		
		UserAccount user = commonDao.createUserAccount(userAccount);
		
		try{
			if(user.getUserProfile().getOrganization().getSubscription() != null){
				Subscription userSubscription = user.getUserProfile().getOrganization().getSubscription();
				userSubscription.setOrganizationId(user.getUserProfile().getOrganization().getId());
				commonDao.saveUserFormDetails(user);
			}
		}
		catch(Exception e){
			e.printStackTrace();
			return user;
		}
		return user;
}
	
	public void setLoginStatus(UserAccount userAccount){
		commonDao.saveLoginDetails(userAccount);
	}
	
	public boolean isUserExist(String email){
		boolean status = commonDao.isUserExist(email);
		return status;
	}
	
	public UserAccount searchUserAccountDetails(UserForm userAccount){
		return commonDao.getUserDetails(userAccount);
	}

	public UserAccount validateUser(UserForm userForm){
		return commonDao.validateUser(userForm);
	}
		
	public void saveContents(Content content){
		content.setActive(true);
		commonDao.saveContentDetails(content);
	}
	
	public void saveQuestion(TextQuestion textQuestion){
		commonDao.saveQuestions(textQuestion);
	}

	public void saveAnswer(YesNoAnswer ynAnswer){
		commonDao.saveAnswers(ynAnswer);
	}
	
	public void saveAnswerList(MultipleChoiceAnswer mcq){
		commonDao.mcqList(mcq);
	}
	
	public void saveAnswerType(Content content){
		commonDao.saveAnswersTypes(content);
	}
	
	public List<Content> getAllQuestions(UserProfile profile,Long categoryId){
		 return commonDao.getQuestions(profile.getId(),categoryId);
	}
	
	public List<YesNoAnswer> getYNAnswers(){
		return commonDao.getYNAnswers();
	}
	
	public List<Content> getAnswerTypes(){
		return commonDao.getAnswerTypes();
	}
	
	
	public List<Content> getAllContents(){
		return commonDao.getAllContents();
	}
	
	public Content getContentsById(long id){
		return commonDao.getContentsById(id);
	}
	
	public  List<Member>  getAllMember(Long organizationId){
		return commonDao.getAllMembers(organizationId);		
	}
	
	public void getSubscribe(SubscriptionPlan  plan){
		if(plan!=null){
			SubscriptionPlan plan2 =new SubscriptionPlan();
			//TODO
			commonDao.getSubscriptionPlan(plan2);
		}
	}

	public UserProfile getUserDetailsById(String id){
			return commonDao.getUserDetailsById(Long.valueOf(id));
		}
		
	public HashMap<String,List<CategoryMap>> getAllCategories(){
		return CategoryMapUtil.getCategoryMap(commonDao.getAllCategories());
	}
	
	public List<Category> getAllSubCategories(){
		return commonDao.getAllSubCategories();
	}
	
	public List<Category> getAllSubCategories(String subId){
		return commonDao.getAllSubCategories(Long.valueOf(subId));
	}
	public List<Category> getAllMainCategories(){
		return commonDao.getAllMainCategories();
	}
	
	public void saveUserProfile(UserForm userForm, String fileName){
		UserProfile userprofile = commonDao.getUserProfileByEmailAdd(userForm.getEmail());
		
		userprofile.setFirstName(userForm.getFirstName());
		userprofile.setLastName(userForm.getLastName());
		userprofile.setPhone(userForm.getPhone());
		userprofile.setUsername(userForm.getUsername());
		
		Organization userOrganization = userprofile.getOrganization();
		userOrganization.setOrganizationLogo(fileName);
		userOrganization.setTagLine(userForm.getTagLine());
		userOrganization.setAboutOrganization(userForm.getAboutOrganization());
		
		commonDao.saveUserProfile(userprofile);
	}

	public void saveGroup(GroupForm groupForm, HttpSession session){
		Group group= new Group();
		List<String> memberid = groupForm.getMemberId();
		Set<Member> set = new HashSet<Member>();
		for(String members : memberid){
			Member myMembers = commonDao.getMemberById( Long.valueOf(members) );
			set.add(myMembers);
		}
		UserAccount user = (UserAccount)session.getAttribute("user");
		group.setActive(true);
		group.setAuthor(user.getUserProfile().getUsername());
		group.setGroupName(groupForm.getGroupName());
		group.setCreatedOn(new Date());
		group.setCreateby(user.getUserProfile().getOrganization());
		group.setMembers(set);
		commonDao.saveGroup(group);
	}

	public void saveEditGroup(GroupForm saveGroup, String id){

		Group group = commonDao.getGroupById(Long.valueOf(id));
		group.removeAllMembers();
		
		List<String> memberid = saveGroup.getMemberId();
		Set<Member> set = new HashSet<Member>();
		for(String members : memberid){
			Member myMembers = commonDao.getMemberById( Long.valueOf(members) );
			set.add(myMembers);
		}
		group.setMembers(set);
		commonDao.saveGroup(group);
		
	}
	
	public List<Category> getTestCenter(){
		return commonDao.getTestCenter();
	}
	
	public long insertSeries(SeriesForm series, HttpServletRequest request,HttpSession session){
		Category subCategory = commonDao.getCategoryById(Long.valueOf(series.getSubCategoryId()));
		Series series2=new Series();
		UserAccount user= (UserAccount)session.getAttribute("user");
		Organization organization= commonDao.getUserOrganizationById(user.getUserProfile().getId());
		Organization organization2=commonDao.getOrganizationDetailsById(organization.getId());
		series2.setCreateBy(organization2);
		series2.setSeriesName(series.getSeriesname());
		series2.setCategory(subCategory);
		series2.setCreateOn(new Date());
		series2.setQuestionLevelTiming(series.getOneQuestionTime());
		series2.setTotalTime(series.getTotalQuestionTime());
		return commonDao.updateSeries(series2);
	}
	
	public void insertLevel(LevelForm level){
		Series series = commonDao.getSeriesById(Long.valueOf(level.getSeriesId()));
		Level level2 = new Level();
		level2.setSeries(series);
		level2.setLevelname(level.getLevelname());
		for(String contentId : level.getQuestionIds()){
				Content q = commonDao.getContent(Long.valueOf(contentId));
				level2.addQuestion(q);
		}
		series.addLevel(level2);
		commonDao.updateSeries(series);
	}

	public void createNewLevel(long id){
		Series series = commonDao.getSeriesById(id);
		commonDao.updateSeries(series);
	}
 
	public List<Category> getSubCategories(long parentId){
		return commonDao.getSubCategories(parentId);
	}

	public Category saveOrUpdateNewCategory(Category categoryForm){
		Category category = commonDao.getCategoryById(categoryForm.getId());
		Category newSubCategory = new Category();
		newSubCategory.setCategoryName(categoryForm.getCategoryName());
		newSubCategory.setDescription(categoryForm.getDescription());
		newSubCategory.setParentCategory(category);

		return commonDao.saveOrUpdateNewCategory(newSubCategory);
	}

	public Series getSeriesById(long seriesId, Long orgId){
		return commonDao.getSeriesById(seriesId, orgId);
	}

	public Series getSeriesById(long seriesId){
		return commonDao.getSeriesById(seriesId);
	}
	
	public  List<Group>  getAllGroups(String organizationId){		
		return commonDao.getAllGroups(Long.valueOf(organizationId));		
	}
	
	public Group groupDetailById(String id){
		return commonDao.groupDetailById(Long.valueOf(id));
	}
	
	public List<Feature> getAllFeature(){
		return commonDao.getAllFeature();
	}
	
	public List<Feature> getAllFeaturebyViewType(ViewType viewType){
		return commonDao.getAllFeaturebyViewType(viewType);
	}
	
	public void saveSubcriptionPlan(SubscriptionPlanForm subscriptionPlanForm, HttpSession session){
		SubscriptionPlan subscriptionPlan=new SubscriptionPlan();
		subscriptionPlan.setPlanType(subscriptionPlanForm.getPlanType());
		Date date = new Date(); 
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		        try {
					date = sdf.parse(subscriptionPlanForm.getExpirydate());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}            
		        subscriptionPlan.setExpiryDate(date);
		subscriptionPlan.setActive(true);
		subscriptionPlan.setAmount(Double.valueOf(subscriptionPlanForm.getAmount()));				
		if(session.getAttribute("name")!=null){
			UserProfile uProfile = commonDao.getUserDetailsByName(String.valueOf(session.getAttribute("name")));
			subscriptionPlan.setCreatedBy(uProfile);
			subscriptionPlan.setAuthorName(uProfile.getFirstName());
		}
		else{
			AdminAccount adminAccount = (AdminAccount)session.getAttribute("admin");			
			UserProfile uProfile = commonDao.getUserDetailsById(adminAccount.getUserProfile().getId());
			subscriptionPlan.setCreatedBy(uProfile);
			subscriptionPlan.setAuthorName(uProfile.getFirstName());
		}
		subscriptionPlan.setSubscriptionMode(subscriptionPlanForm.getSubscriptionMode());
		subscriptionPlan.setCheckDefault(Boolean.valueOf(subscriptionPlanForm.getCheckDefault()));
		
		SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		timeFormat = DateTimeUtils.getGMTSimpleDateFormat(timeFormat);
		Calendar currentTime = DateTimeUtils.getGMTCalendar();
		subscriptionPlan.setCreationTime(currentTime.getTime());
		subscriptionPlan.setPlanDescription(subscriptionPlanForm.getPlanDescription());
		subscriptionPlan.setPlanName(subscriptionPlanForm.getPlanName());
		
		
		Set<FeatureAccessLevel> featureAccessLevelList = new HashSet<FeatureAccessLevel>();
		for(FeatureAccessLevelForm form : subscriptionPlanForm.getAccessLevels()){
			FeatureAccessLevel accessLevel = new FeatureAccessLevel();
			
			String featureId = form.getFeatureid();
			Feature feature = commonDao.getFeatureById(Long.valueOf(featureId));
			accessLevel.setFeature(feature);
			accessLevel.setFeatureType(form.getFeatureType());
			accessLevel.setValue(form.getValue());
			
			featureAccessLevelList.add(accessLevel);
		}
		subscriptionPlan.setFeatureAccessLevelList(featureAccessLevelList);
		
		subscriptionPlan.setSubscriptionType(subscriptionPlanForm.getSubscriptionType());
		
		subscriptionPlan.setRegistrationType(subscriptionPlanForm.getRegistrationType());
	
		commonDao.saveSubcriptionPlan(subscriptionPlan);
	}
	
	public void deleteGroup(String id){
		 commonDao.deleteGroup(Long.valueOf(id));
	}

	public List<SubscriptionPlan> getAllSubscriptionPlans(long id){
		return commonDao.getAllSubscriptionPlans(Long.valueOf(id));
	}

	public List<SubscriptionPlan> getTotalSubscriptionPlans(){
		return commonDao.getTotalSubscriptionPlans();
	}
	
	public List<Series> getAllSeriesByOrgId(long orgId){
		return commonDao.getAllSeriesByOrgId(orgId);
	}

	public boolean isUsernameExist(String userName){
			return commonDao.isUsernameExist(userName);
		}
	
	public UserAccount getUserDetailsByEmailAdd(String userName){
		return commonDao.getUserDetailsByEmailAdd(userName);
	}
	
	public Address getcurrentAddressbyId(long currentAddressid){
		return commonDao.getcurrentAddressbyId(currentAddressid);
	}
	
	public Address getpermanentAddressbyId(long permanentAddressid){
		return commonDao.getpermanentAddressbyId(permanentAddressid);
	}

	public UserProfile getUserProfileByEmailAdd(String username){
		return commonDao.getUserProfileByEmailAdd(username);
	}
	
	public Category getCategoryById(long categoryId){
		return commonDao.getCategoryById(categoryId);
	}
	
	public void createNewContent(String subCategoryId, Member memberAccount, AddQuestions addQuestion){
		Content content = new Content();
		Category category = getCategoryById(Long.valueOf(subCategoryId));
		content.setSubCategoryId(Long.valueOf(subCategoryId));
		content.setCatagoryId(category.getParentCategory().getId());
		content.setCreateBy(memberAccount.getAccount().getOrganization());
// Min. Proficiency-----
		if(addQuestion.getMinProficiency().equals("0")){
			content.setMinProficiency(ProficiencyType.BEGINNER);
		}
		if(addQuestion.getMinProficiency().equals("1")){
			content.setMinProficiency(ProficiencyType.INTERMIDIATE);
		}
		if(addQuestion.getMinProficiency().equals("2")){
			content.setMinProficiency(ProficiencyType.ADVANCED);
		}
		if(addQuestion.getMinProficiency().equals("3")){
			content.setMinProficiency(ProficiencyType.EXPERT);
		}
		
		if((addQuestion.getQuestionType()).equals("0")){		
			content.setAnswerType(AnswerType.YES_NO);
			
			System.out.println(addQuestion.getAnswer()+"answer class");
			YesNoAnswer ynAnswer = new YesNoAnswer();
			ynAnswer.setAnswer(addQuestion.getAnswer());
			
			System.out.println(addQuestion.getQuestion()+"question class");
			TextQuestion textQuestion = new TextQuestion();
			
			textQuestion.setQuestion(addQuestion.getQuestion());//	saving y/n question
			content.setQuestion(textQuestion);
			content.setAnswer(ynAnswer);
			content.setSubCategoryId(Long.valueOf(subCategoryId));
		}
		
		else if (addQuestion.getQuestionType().equals("2")) {
			content.setAnswerType(AnswerType.SUBJECTIVE);
			
			SubjectiveAnswer subjectiveAnswer = new SubjectiveAnswer();
			subjectiveAnswer.setSubjectiveAnswer(addQuestion.getSubjectiveAnswer());
			
			TextQuestion textQuestion = new  TextQuestion();
			textQuestion.setQuestion(addQuestion.getQuestion());
			content.setQuestion(textQuestion);
			content.setAnswer(subjectiveAnswer);
			content.setSubCategoryId(Long.valueOf(subCategoryId));
		}		
		else{
			content.setAnswerType(AnswerType.MCQ);
			
			TextQuestion textQuestion = new TextQuestion();
			textQuestion.setQuestion(addQuestion.getQuestion());//	saving MCQ question
			content.setQuestion(textQuestion);
			
			MultipleChoiceAnswer mcq = new MultipleChoiceAnswer();
			ArrayList<String> correctAnswers = new ArrayList<String>();
			ArrayList<String> totalAnswers = new ArrayList<String>();
			String arr[] = addQuestion.getCorrectAnswer();
			for(int i=0;i<arr.length;i++){
					if((arr[i]).equals("1"))
					{
						correctAnswers.add(arr[i-1]);
					}
					else if(!(arr[i]).equals("1")){
						totalAnswers.add(arr[i]);
					}				
			}
			for(String val : correctAnswers){
				System.out.println("correct opt"+val);
			}
			
			for(String total : totalAnswers){
				System.out.println("Total Ans  "+total);
			}
			mcq.setAnswerList(correctAnswers);
			mcq.setChoiceList(totalAnswers);
			content.setAnswer(mcq);
			content.setSubCategoryId(Long.valueOf(subCategoryId));
		}
		////////////////////////////////////////
		if(addQuestion.getMaxProficiency().equals("0")){
			content.setMaxProficiency(ProficiencyType.BEGINNER);
		}
		if(addQuestion.getMaxProficiency().equals("1")){
			content.setMaxProficiency(ProficiencyType.INTERMIDIATE);
		}
		if(addQuestion.getMaxProficiency().equals("2")){
			content.setMaxProficiency(ProficiencyType.ADVANCED);
		}
		if(addQuestion.getMaxProficiency().equals("3")){
			content.setMaxProficiency(ProficiencyType.EXPERT);
		}
		saveContents(content);
	}

	public UserAccount getUserAccountById(long userId){
		return commonDao.getUserAccountById(userId);
	}
	
	public Series getSeriesDetailById(long id){
		return commonDao.getSeriesDetailById(id);
	}
	
	public List<Series> editSeriesDetail(long id){
		return commonDao.editSeriesDetail(id);
	}
	
	public void deleteSeries(long id){
		 commonDao.deleteSeries(id);
	}
	
	public void updateSeriesDetail(SeriesForm seriesForm){
		Series series=new Series();
		series.setSeriesName(seriesForm.getSeriesname());
		List<Level> level=commonDao.getLeveListlById(seriesForm.getSeriesId());
		series.setLevels(level);
		commonDao.updateSeriesDetail(series);
	}
	
	public boolean addMember(Organization organization, UserProfile userProfile){
		List<Member> memberList = commonDao.getMemberListbyProfileId(userProfile.getId());	//List of all organizations that a current user is member of.
		for(Member allOrganization : memberList){
			allOrganization.setDefaultMemberOfOrganization(false);
		}
		Member member = new Member();
		member.setAccount(userProfile);
		member.setOrganization(organization);
		member.setMemberRole(MemberRole.UserRole);
		member.setDefaultMemberOfOrganization(true);
		member.setRegistrationDate(new Date());
		member.setActive(true);
		commonDao.saveMemberDetails(member);
		return true;	
	}
	 
	
	public Member getMemberById(Long id){
		return commonDao.getMemberById(id);
	}
	
	public boolean isUserEmailExist(String email){
		return commonDao.isUserEmailExist(email);
	}
/*	
	public boolean addMember(String token){			
		//Invitation inviDeatilById = commonDao.getTokenId(Long.valueOf(token));
		 		 
		 // UserProfile userProfile = commonDao.getUserDetails(inviDeatilById.getFriendEmailId());
		
			if(userProfile!=null){			
				inviDeatilById.setActive(true);			
				inviDeatilById.setValid("True");				
				commonDao.setInvite(inviDeatilById);
				
			Member membertable = new Member();
			commonDao.createMember(membertable);
			return true;
			}
		return false;
	}*/
	
	public List<SubscriptionPlan> getAdminSideSubPlanList(){		
		return commonDao.getAdminSideSubPlanList();
	}
	
	public List<SubscriptionPlan> getSubPlanByAuthorId(Long id){
		return commonDao.getSubPlanByAuthorId(id);
	}
	
	public SubscriptionPlan getSubscriptionPlanById(Long id){
		return commonDao.getSubscriptionPlanById(id);
	}
	
	public void deleteSubPlan(String id){
		 commonDao.deleteSubPlan(Long.valueOf(id));
	}
	
	public void deleteMemberById(String id){
		 commonDao.deleteMemberById(Long.valueOf(id));
	}
	
	public List<SubscriptionPlan> getAllSubscriptionPlans(){
		return commonDao.getAllSubscriptionPlans();
	}	
	
	public SubscriptionPlan getDefaultSubscriptionPlan(SubscriptionMode sMode,RegistrationType rType){
		return commonDao.getDefaultSubscriptionPlan(sMode,rType);
	}	
	
	
	public void saveUserOrganizationDetails(UserProfile userProfile, UserForm userForm){
		Address address = new Address();
		address.setAddress(userForm.getAddress());
		address.setCity(userForm.getCity());
		address.setLandmark(userForm.getLandmark());
		address.setCountry(userForm.getCountry());
		address.setStreet(userForm.getStreet());
		address.setZipCode(userForm.getZipCode());
		
		WorkProfile workProfile = new WorkProfile();
		workProfile.setOrganizationCity(userForm.getOrganizationCity());
		workProfile.setEndDate(userForm.getEndDate());
		workProfile.setOrganizationName(userForm.getPreviousOrgName());
		workProfile.setRole(userForm.getRole());
		workProfile.setStartDate(userForm.getStartDate());
		
		Organization userOrganization = userProfile.getOrganization();
		
		userOrganization.setAboutOrganization(userForm.getAboutOrganization());
		userOrganization.setActive(true);
		userOrganization.setWebsite(userForm.getWebsite());
		userOrganization.setUserProfile(userProfile);
		userOrganization.setCurrentAddress(address);
		userOrganization.setRegistrationStatus(RegistrationStatus.COMPLETED);
		userOrganization.setWorkProfile(workProfile);
		
		Permission permission = new Permission();	// Admin Permissions
		permission.setCreateData(true);
		permission.setDeleteData(true);
		permission.setEditData(true);
		permission.setViewData(true);
		
		Set<FeatureAccessLevel> adminFeatureAccessLevelSet = new HashSet<FeatureAccessLevel>();
		
		SubscriptionPlan adminSubscriptionPlan = userOrganization.getSubscription().getSubscriptionPlan();
		
		for(FeatureAccessLevel fal : adminSubscriptionPlan.getFeatureAccessLevelList()){
			FeatureAccessLevel newFAL = new FeatureAccessLevel();	// Setting all Features in List
			newFAL.setFeature(fal.getFeature());
			newFAL.setFeatureType(fal.getFeatureType());
			newFAL.setValue(fal.getValue());
			adminFeatureAccessLevelSet.add(newFAL);
		}
		
		MemberFALPermission adminFALPermission = new MemberFALPermission();	// Admin FAL
		adminFALPermission.setActive(true);
		adminFALPermission.setMemberPermission(permission);
		adminFALPermission.setMemberRole(MemberRole.AdminRole);
		adminFALPermission.setFeatureAccessLevelList(adminFeatureAccessLevelSet);
		
		
		Permission userPermission = new Permission();		//Member Permissions
		userPermission.setViewData(true);
		userPermission.setCreateData(false);
		userPermission.setDeleteData(false);
		userPermission.setEditData(false);
		
		Set<FeatureAccessLevel> userFeatureAccessLevelSet = new HashSet<FeatureAccessLevel>();

		SubscriptionPlan userSubscriptionPlan = commonDao.getDefaultSubscriptionPlan(SubscriptionMode.ONLINE, RegistrationType.FREE);

		for(FeatureAccessLevel fal : userSubscriptionPlan.getFeatureAccessLevelList()){
			FeatureAccessLevel newFAL = new FeatureAccessLevel();	// Setting all Features in List
			newFAL.setFeature(fal.getFeature());
			newFAL.setFeatureType(fal.getFeatureType());
			newFAL.setValue(fal.getValue());
			userFeatureAccessLevelSet.add(newFAL);
		}
				
		MemberFALPermission userFALPermission = new MemberFALPermission();	// Member FAL
		userFALPermission.setActive(true);
		userFALPermission.setFeatureAccessLevelList(userFeatureAccessLevelSet);
		userFALPermission.setMemberPermission(userPermission);
		userFALPermission.setMemberRole(MemberRole.UserRole);
		
		
		Set<MemberFALPermission> oragnizationFAL = userOrganization.getMemberFALPermissions();
		if(oragnizationFAL == null){
			oragnizationFAL = new HashSet<MemberFALPermission>();
			userOrganization.setMemberFALPermissions(oragnizationFAL);
		}
		
		
		Role userRole = new Role();
		userRole.setActive(true);
		userRole.setOrganization(userOrganization);
		userRole.setRoleName("UserRole");
		
		Role adminRole = new Role();
		adminRole.setActive(true);
		adminRole.setOrganization(userOrganization);
		adminRole.setRoleName("AdminRole");
		
		oragnizationFAL.add(adminFALPermission);
		oragnizationFAL.add(userFALPermission);
		Member ownerMember = new Member();
		ownerMember.setDefaultMemberOfOrganization(true);
		ownerMember.setOrganization(userOrganization);
		ownerMember.setMemberRole(MemberRole.AdminRole);
		ownerMember.setRegistrationDate(new Date());
		ownerMember.setAccount(userOrganization.getUserProfile());

		userProfile.setOrganization(userOrganization);
		
		
		
		commonDao.saveUserOrganizationDetails(ownerMember);
	}
	
	public Organization getUserOrganizationById(long id){
		return commonDao.getUserOrganizationById(id);
	}
	
	public Subscription getSubscriptionById(long id){
		return commonDao.getSubscriptionById(id);
	}
	
	public List<Series> getSeriesList(long orgId){
		return commonDao.getSeriesList(orgId);
	}
	
	public Level getLevelById(String levelId){
		return commonDao.getLevelById(Long.valueOf(levelId));
	}
	
	public boolean isUserAlreadyMember(String email){
		return commonDao.isUserAlreadyMember(email);
	}
	
	public boolean sendInvitationMail(Invitation invitations, String url, HttpServletRequest request, HttpSession session){
		
		Invitation newInvitation = null;
		for(String friendEmail : invitations.getFriendEmailId().split(",")){
			newInvitation = new Invitation();
			TokenGenerator tokenGenerator = new TokenGenerator();
			String token = tokenGenerator.generateUniqueKey();
			boolean status = testmail.sendMail(friendEmail, url, token);
			if(status){
				newInvitation.setResult("Success");
				newInvitation.setToken(token);
				newInvitation.setFriendEmailId(friendEmail);
				updateInvitationData(newInvitation, session);
			}
			else{
				newInvitation.setResult("Failure");
			}
			commonDao.setInvite(newInvitation);
		}
		return true;
	}
	
	public void saveInvitation(Invitation invitation){
		commonDao.setInvite(invitation);
	}
	
	public void updateInvitationData(Invitation invitation, HttpSession session){
		UserAccount user = (UserAccount)session.getAttribute("user");
		invitation.setActive(false);
		invitation.setInviteBy(user.getUserProfile().getEmail());
		invitation.setInviteeId(user.getUserProfile().getId());
		invitation.setOrganizationId(user.getUserProfile().getOrganization().getId());
	}
	
	public Invitation getInvitedMemberDetails(String token){
		return commonDao.getInvitedMemberDetails(token);
	}
	
	public Organization getOrganizationById(long organizationId){
		return commonDao.getOrganizationById(organizationId);
	}
	
	public Organization getOrganizationByName(String organizationName){
		return commonDao.getOrganizationByName(organizationName);
	}

	public List<Member> getMemberListbyProfileId(long profileId){
		return commonDao.getMemberListbyProfileId(profileId);
	}
	
	public List<SubscriptionPlan> getFreeDefaultPlan(SubscriptionType subsType){
		return commonDao.getFreeDefaultPlan(subsType);
	}
	
	public Question getQuestionById(String levelId){
		return commonDao.getQuestionById(Long.valueOf(levelId));
	}

	public void saveUserSubscribe(SubscriptionPlan choosePlan, String email){
		UserProfile userDetails = commonDao.getUserProfileByEmailAdd(email);
		SubscriptionPlan newsubplan = getSubscriptionPlanById(choosePlan.getId());
		//Subscription newSubscription = getSubscriptionById(userDetails.getOrganization().getSubscription().getId());
		Subscription newsub = new Subscription();
		newsub.setSubscriptionPlan(newsubplan);
		Organization organization = getOrganizationById(userDetails.getOrganization().getId());
		organization.setSubscription(newsub);
		userDetails.setOrganization(organization);
		commonDao.saveUserSubscribe(userDetails);
	}
	
	public Category insertCategory(Category category, HttpSession session){
		UserAccount user = (UserAccount)session.getAttribute("user");
		if(user!=null){
		Organization organization = getOrganizationById(user.getUserProfile().getOrganization().getId());
		category.setCreateBy(organization);
		}
		Category newCategory = commonDao.saveCategory(category);
		return newCategory;
	}
	
	public static boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    }
	    // only got here if we didn't return false
	    return true;
	}
	
	public List<SubscriptionPlan> getSubscriptionPlanByRegistration(RegistrationType registrationType){
		return commonDao.getSubscriptionPlanByRegistration(registrationType);
	}
	
	
	public List<Organization> getOrganizationList(){
		return commonDao.getOrganizationList();
	}
	
	public Member getOrganizationMember(long organizationId,long userId, MemberRole memberRole){
		return commonDao.getOrganizationMember(organizationId, userId, memberRole);
	}
	
	public List<MemberFALPermission> getMemberFALPermissionsbyRole(MemberRole memberRole){
		return commonDao.getMemberFALPermissionsbyRole(memberRole);
	}
	
	public void insertQuizData(SaveUserQueAns saveUserQA,HttpSession request){

		Member member = (Member)request.getAttribute("member");
		UserAccount account=(UserAccount) request.getAttribute("user");

		ScoreBoard scoreBoard =commonDao.getScoreBoardBylevelid(Long.valueOf(saveUserQA.getLevelId()) ,member.getMemberId(),member.getOrganization().getId());
		if(scoreBoard == null){
			scoreBoard=new ScoreBoard();
			scoreBoard.setLevelId(Long.valueOf(saveUserQA.getLevelId()));
			scoreBoard.setSeriesId(Long.valueOf(saveUserQA.getSeriesId()));
			scoreBoard.setMemberId(member.getMemberId());
			scoreBoard.setOrganizationId(member.getOrganization().getId());
			scoreBoard.setUsername(account.getUsername());
		}

		int correctCount = 0;
		int totalCount = 0;
		for(QuestionAnswer qa :saveUserQA.getResult()){
			totalCount++;
			System.out.println("ANS ::- " + qa.getAnswer().toLowerCase().trim() + " , CR ::- " + qa.getCorrect().toLowerCase().trim());
			if(qa.getAnswer().toLowerCase().trim().equalsIgnoreCase(qa.getCorrect().toLowerCase().trim())){
				correctCount++;
			}
		}
		
		double scorePercentage = ((double)correctCount/totalCount)*100;
		System.out.println("correctCount ::- " + correctCount + " , totalCount ::- " + totalCount + " , scorePercentage ::- " + scorePercentage);
		scoreBoard.setScore(scorePercentage);
		scoreBoard.setTotalTryOrHits(scoreBoard.getTotalTryOrHits() + 1);

		commonDao.updateQuizData(scoreBoard);
	}
	
	public void insertQuizDataPreview(){
	}
	
	public ScoreBoard getScoreBoard(long levelId, Long memberId, Long orgId){
		return commonDao.getScoreBoard(levelId, memberId, orgId);
	}
	
	public List<Deck> getAllDecksByOrgId(long organizationID){
		return commonDao.getAllDecksByOrgId(organizationID);
	}
	
	public void saveNewDeck(Group group, Series series){
		commonDao.saveNewDeck(group, series);
	}
	
	public void saveNewDeck(Group group, Series series, Organization organization){
		Deck deck = new Deck();
		deck.setActive(true);
		deck.setAssignedOn(new Date());
		deck.setGroup(group);
		deck.setSeries(series);
		deck.setOrganization(organization);
		commonDao.saveNewDeck(deck);
	}
	
	public Group getGroupById(long groupId){
		return commonDao.getGroupById(groupId);
	}
	
	public List<Deck> getDeckListbyGroupId(long groupId){
		return commonDao.getDeckListbyGroupId(groupId);
	}
	
	public List<DeckCourse> getDeckCoursesbyGroupId(long groupId){
		return commonDao.getDeckCoursesbyGroupId(groupId);
	}
	
	public void saveContactReq(ContactRequest contactRequest){
		contactRequest.setStatus("Pending");
		Date date = new Date();
		contactRequest.setReqDate(new Timestamp(date.getTime()));
		commonDao.saveContactReq(contactRequest);
		try{
			ContactUsResquestMail contactUsReqMail = new ContactUsResquestMail();
			contactUsReqMail.email(contactRequest.getUserName(), contactRequest.getMobile(), contactRequest.getDescription(), contactRequest.getEmail());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public SubscriptionPlan getSubscriptionPlanByName(String planName){
		return commonDao.getSubscriptionPlanByName(planName);
	}
	
	public void saveBook(MediaFileForm mediaFile,String fileName){
		MediaFile file=new MediaFile();
		file.setMediaName(fileName);
		 commonDao.saveBook(file);
	}
	
	public void updateMemberDetail(String id, HttpServletRequest req){
		
		Member member = commonDao.getMemberById(Long.valueOf(id));
		String mr = req.getParameter("memberRole");
		if(mr.equalsIgnoreCase("AdminRole")){
			member.setMemberRole(MemberRole.AdminRole);
		}
		if(mr.equalsIgnoreCase("UserRole")){
			member.setMemberRole(MemberRole.UserRole);		
		}
		if(mr.equalsIgnoreCase("SuperAdminRole")){
			member.setMemberRole(MemberRole.SuperAdminRole);
		}
		if(mr.equalsIgnoreCase("QARole")){
			member.setMemberRole(MemberRole.QARole);
		}		
		
		String [] result = req.getParameterValues("active");
			for(String s : result){
				if(s.equalsIgnoreCase("false")){
					member.setActive(false);
					}
				if(s.equalsIgnoreCase("true")){
					member.setActive(true);
					}
				commonDao.saveMemberDetails(member);
			}
		}
	
		public void saveNewMember(long id, MemberForm memberForm){
		Organization userOrg = commonDao.getOrganizationById(id);
		System.out.println("userOrg : "+userOrg.getId());
		
		
		UserProfile userProfile = new UserProfile();
		userProfile.setEmail(memberForm.getEmail());
		userProfile.setUsername(memberForm.getEmail());
		userProfile.setActive(true);
		userProfile.setFirstName(memberForm.getFirstName());
		userProfile.setRoleType(RoleType.USER);
		
		UserAccount userAccount = new UserAccount();
		userAccount.setPassword(memberForm.getPassword());
		userAccount.setUsername(userProfile.getUsername());
		userAccount.setUserProfile(userProfile);
		
		UserAccount newAccount = commonDao.createUserAccount(userAccount);
		
		System.out.println("userOrg UserAccount : "+newAccount.getId() + " proId "+newAccount.getUserProfile().getId());
		Member member = new Member();
		member.setAccount(newAccount.getUserProfile());
		member.setActive(true);
		member.setOrganization(userOrg);
		member.setRegistrationDate(new Date());
		member.setMemberRole(memberForm.getMemberRole());
		member.setDefaultMemberOfOrganization(true);
		commonDao.saveMemberDetails(member);
	}
	
		public void saveScorm(UploadedScorm uploadedScorm){
			
		}
		
		public void saveUploadedScorm(UploadedScorm uploadedScorm){
			commonDao.saveUploadedScorm(uploadedScorm);
		}
		
		public void saveBook(MediaFileForm mediaFile,String mediaFileName,SubscriptionType type,Organization organizationDetails){
			MediaFile file=new MediaFile();
			file.setMediaName(mediaFileName);
			file.setSubscriptionType(type);
			Date date = new Date(); 
			file.setPublishDate(date);
			file.setMediaType(mediaFile.getMediaType());
			file.setMediaDescription(mediaFile.getDiscription());
			file.setOrganization(organizationDetails);
			//file.setMediaName(fileName);
			commonDao.saveBook(file);
		}
		
		public List<MediaFile> getAllMediaDatails(){
			return commonDao.getAllMediaDatails();
		}
		
		public void deleteMediaFileById(String Id){
			commonDao.deleteMediaFileById(Long.valueOf(Id));
		}
		
		public MediaFile editMediaFileById(String Id){
			return commonDao.editMediaFileById(Long.valueOf(Id));
		}
		
		public void saveEditMediaFile(String id, MediaFileForm mediaFile){
			MediaFile file= commonDao.editMediaFileById(Long.valueOf(id));
			file.setMediaDescription(mediaFile.getDiscription());
			file.setMediaType(mediaFile.getMediaType());
			file.setSubscriptionType(mediaFile.getSubtype());
			commonDao.saveEditMediaFile(file);
		}
	
	public void saveCourse(Course course){
		commonDao.saveCourse(course);
	}
	
	public List<Subscription> getAllSubscriptionsByOrgId(long organizationId){
		return commonDao.getAllSubscriptionsByOrgId(organizationId);
	}
	
	public List<UserPayment> getPaymentDetailsByUsername(String username){
		return commonDao.getPaymentDetailsByUsername(username);
	}
	
	public void saveNewMemberRole(CreateNewRole createNewRole, Organization userOrganization){
		Set<MemberFALPermission> ownerMemberFALPermissionsSet = userOrganization.getMemberFALPermissions();
		
		MemberFALPermission newMemberFAL = null;
		Set<FeatureAccessLevel> FALSet = new HashSet<FeatureAccessLevel>();	// NEW FAL List
		for(MemberFALPermission memberFAL : ownerMemberFALPermissionsSet){
			for(FeatureAccessLevel FAL : memberFAL.getFeatureAccessLevelList()){
				FALSet.add(FAL);											// Set the New FAL List
				newMemberFAL = memberFAL;
			}
		}
			
		ownerMemberFALPermissionsSet.add(newMemberFAL);
		
		
		FeatureAccessLevel newFAL = new FeatureAccessLevel();
		for(Feature feature : commonDao.getAllFeature()){
			newFAL.setFeature(feature);
		}
		for(String value : createNewRole.getValue()){
			newFAL.setValue(value);
		}
		
		Permission permission = new Permission();
		permission.setCreateData(false);
		permission.setDeleteData(false);
		permission.setEditData(false);
		permission.setViewData(true);
		
		MemberFALPermission memberFALPermissions = new MemberFALPermission();
		memberFALPermissions.setMemberPermission(permission);
		memberFALPermissions.setActive(true);
		memberFALPermissions.setMemberRole(createNewRole.getRole());
		memberFALPermissions.setFeatureAccessLevelList(FALSet);
		
	}
	
	public void saveInvitation(FollowFriendData followFriend,HttpServletRequest req){
		HttpSession session = req.getSession();
		Member member = (Member)session.getAttribute("member");

		for(String friendMemberId : followFriend.getMemberIds()){
			Friend memberFriend = new Friend();
			memberFriend.setMember(member);
			memberFriend.setActive(true);
			memberFriend.setApproved(false);
			memberFriend.setFollow(false);
			memberFriend.setOrganization(member.getOrganization());

			Member organizationMemberFriend = commonDao.getOrganizationMemberById(member.getOrganization().getId(), Long.valueOf(friendMemberId));
			memberFriend.setFriend(organizationMemberFriend);
			commonDao.saveUpdateInvitation(memberFriend);
		}
	}

	public List<Friend> getallUnapprovedFriends(Member member){
		return commonDao.getallUnapprovedFriends(member);
	}
	
	public void updateForApproval(Friend friend){
		friend.setApproved(true);
		commonDao.updateForApproval(friend);
	}
	
	public Friend getApprovedFriendsById(String id){
		return commonDao.getApprovedFriendsById(Long.valueOf(id));
		 
	}
	
	public List<Friend> getAllApprovedFriends(Member member){
		return commonDao.getAllApprovedFriends(member.getMemberId());
	}
	
	public void updateFriend(Friend friend){
		commonDao.updateFriend(friend);
	}
	
	public Friend getFriendById(String id){
		return commonDao.getFriendById(Long.valueOf(id));
	}
	
	public void savefollowFriend(String friendId, HttpServletRequest req){
		Friend friend= commonDao.getFriendById(Long.valueOf(friendId));
		friend.setFollow(true);
		commonDao.updateFriend(friend);
	}

	public List<Course> getAllCourses(long orgId){
		return commonDao.getAllCourses(orgId);
	}
	
	public Course getCourseById(long courseId){
		return commonDao.getCourseById(courseId);
	}
	
	public void deleteFollowFriend(String friendId){
		commonDao.deleteFollowFriend(Long.valueOf(friendId));
	}
	
	public void removeFollowFriend(String friendId){
		Friend friend= commonDao.getFriendById(Long.valueOf(friendId));
		friend.setFollow(false);
		commonDao.updateFollowFriendStatus(friend);
	}
	
	public void saveNewRole(CreateNewRoleForm createNewRole, HttpSession session){
		Set<FeatureAccessLevel> FALList = new HashSet<FeatureAccessLevel>();
		
		for(FeatureAccessLevelForm FALForm : createNewRole.getAccessLevels()){
			FeatureAccessLevel FAL = new FeatureAccessLevel();
			Feature feature = commonDao.getFeatureById(Long.valueOf(FALForm.getFeatureid()));
			FAL.setFeature(feature);
			FAL.setFeatureType(FALForm.getFeatureType());
			FAL.setValue(FALForm.getValue());
			FALList.add(FAL);
		}
		
		Permission permission = new Permission();
		permission.setCreateData(false);
		permission.setDeleteData(false);
		permission.setEditData(false);
		permission.setViewData(true);
		
		MemberFALPermission memberFALPermission = new MemberFALPermission();
		memberFALPermission.setActive(true);
		memberFALPermission.setFeatureAccessLevelList(FALList);
		memberFALPermission.setMemberRole(createNewRole.getMemberRole());
		memberFALPermission.setMemberPermission(permission);
		
		Member memberAccount = (Member)session.getAttribute("member");
		Set<MemberFALPermission> organizationMemberFAL = memberAccount.getAccount().getOrganization().getMemberFALPermissions();
		organizationMemberFAL.add(memberFALPermission);
		Organization userOrganization = memberAccount.getAccount().getOrganization();
		userOrganization.setMemberFALPermissions(organizationMemberFAL);
		
		Role newRole = new Role();
		newRole.setActive(true);
		newRole.setOrganization(userOrganization);
		newRole.setRoleName(String.valueOf(createNewRole.getMemberRole()));
		commonDao.saveNewRole(newRole);
	}
	
	public List<Deck> getDeckListbyOrgId(long organizationId){
		return commonDao.getDeckListbyOrgId(organizationId);
	}
	
	public void saveNewDeckCourse(Group group, Course course, Member memberAccount){
		DeckCourse deckCourse = new DeckCourse(); 
		deckCourse.setActive(true);
		deckCourse.setAssignedOn(new Date());
		deckCourse.setCourse(course);
		deckCourse.setGroup(group);
		deckCourse.setOrganization(memberAccount.getAccount().getOrganization());
		commonDao.saveNewDeckCourse(deckCourse);
	}
	
	public List<DeckCourse> getAllDecksCoursesByOrgId(long organizationId){
		return commonDao.getAllDecksCoursesByOrgId(organizationId);
	}
	
	public List<Content> getAllQuestionsWithProficiency(long organizationId, String subCatId, String value){
		return commonDao.getAllQuestionsWithProficiency(organizationId, Long.valueOf(subCatId), value);
	}
	
	public void saveAutoGenSeries(UserProfile user, GenrateAutoSeriesForm genrateAS){
		Series series = new Series();
		series.setCreateBy(user.getOrganization());
		series.setSeriesName(genrateAS.getSeriesName());
		series.setCreateOn(new Date());
		series.setCategory(commonDao.getCategoryById(Long.valueOf(genrateAS.getCategoryId())));
		
		Series updSeries = commonDao.saveSeries(series);
		int index=1;
		for(LevelForm levelForm : genrateAS.getLevels()){
			Level level = new Level();
			level.setLevelname(levelForm.getLevelname());
			level.setSeries(updSeries);
			level.setPassingScore("60%");
			level.setMasteryScore("80%");
			level.setLevelOrder(index);
			
			List<Content> contents = commonDao.getAllQuestionsForAutoGenerate(user.getOrganization().getId(), Long.valueOf(genrateAS.getCategoryId()), levelForm.getProficiency());
				for(Content question: contents){
					level.addQuestion(question);
				}
			index++;
			commonDao.createLevel(level);
		}
	}
	
	public List<ScoreBoard> getScoreBoardRecord(long memberId){
		return commonDao.getScoreBoardRecord(memberId);
	}
	
	public List<ScoreBoard> getScoreBoardRecordByOrganizationId(long orgId){
		return commonDao.getScoreBoardRecordByOrganizationId(orgId);
	}
	
	public List<ScoreBoardCourse> getCourseResultByOrganizationId(long orgId){
		return commonDao.getCourseResultByOrganizationId(orgId);
	}
	
	public Member getOrganizationMember(long organizationId,long userId){
		return commonDao.getOrganizationMember(organizationId, userId);
	}
	
	public List<UserPayment> getFullPlanHistorybyOrgId(long organizationId){
		return commonDao.getFullPlanHistorybyOrgId(organizationId);
	}

	public List<UserPayment> getFullPaymentHistory(){
		return commonDao.getFullPaymentHistory();
	}
	
	public Deck getDeckSeriesById(String id){
		return commonDao.getDeckSeriesById(Long.valueOf(id));
	}
	
	public DeckCourse getDeckCourseById(String id){
		return commonDao.getDeckCourseById(Long.valueOf(id));
	}
	
	public List<Level> getAllLevelBySeriesId(long id){
		return commonDao.getAllLevelBySeriesId(id);
	}
	
	public void deleteDeckSeriesById(String id){
		commonDao.deleteDeckSeriesById(Long.valueOf(id));
	}
	
	public void deleteDeckCourseById(String id){
		commonDao.deleteDeckCourseById(Long.valueOf(id));
	}
	
	public void createblog(Blog blog,HttpServletRequest request ){
		HttpSession session=request.getSession();
		Member member =(Member)session.getAttribute("member");
		UserAccount account=commonDao.getUserAccountById(member.getMemberId());
		blog.setUsername(account);
		blog.setCreatedTime(new Date());
		commonDao.createblog(blog);
	}
	
	public List<Blog> getBlogDetails(){
		return commonDao.getBlogDetails();
	}

}
