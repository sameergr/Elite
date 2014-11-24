package com.innverse.elearn.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import javax.persistence.NoResultException;
import javax.servlet.ServletContext;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.innverse.elearn.bo.CreateNewMember;
import com.innverse.elearn.config.Config;
import com.innverse.elearn.contants.Constants;
import com.innverse.elearn.eenum.FeatureType;
import com.innverse.elearn.eenum.RegistrationType;
import com.innverse.elearn.eenum.SubscriptionMode;
import com.innverse.elearn.eenum.SubscriptionType;
import com.innverse.elearn.eenum.ViewType;
import com.innverse.elearn.json.UserForm;
import com.innverse.elearn.model.ContactRequest;
import com.innverse.elearn.model.Feature;
import com.innverse.elearn.model.FeatureAccessLevel;
import com.innverse.elearn.model.Invitation;
import com.innverse.elearn.model.Member;
import com.innverse.elearn.model.Organization;
import com.innverse.elearn.model.SubscriptionPlan;
import com.innverse.elearn.model.UserAccount;
import com.innverse.elearn.model.UserProfile;
import com.innverse.elearn.services.CommonServiceImpl;


@Controller
@SessionAttributes
@RequestMapping(value="/site")
public class EliteController extends AbstractController{
	
	@Autowired
	protected CommonServiceImpl commonService;
	
	
	@RequestMapping(value="/signUp{id}", method = RequestMethod.GET)
	public ModelAndView signUpPage(@RequestParam("p") long id, HttpServletRequest req){
		ModelAndView mv = new ModelAndView("guest_user_signup");
		SubscriptionPlan plan = commonService.getSubscriptionPlanById(id);
		mv.addObject("registrationType", plan.getRegistrationType());
		mv.addObject("subsPlan", id);
		return mv;
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView registerUser(@ModelAttribute("registration") UserForm userForm, HttpServletRequest req) {
				ModelAndView mv = new ModelAndView("guest_user_home");
				List<FeatureAccessLevel> featureAccessLevel= null;
				if (commonService.isUserExist(userForm.getEmail())) {
					mv.addObject("regerr", "User Already Exist");
					mv.setViewName("guest_user_signup");
					return mv;
				}
				if (commonService.isUsernameExist(userForm.getUsername())) {
					mv.addObject("regerr", "UserName Already Exist");
					mv.setViewName("guest_user_signup");
					return mv;
				}
				if (userForm.getUsername() == "" || userForm.getPassword() == "" || userForm.getFirstName() == "" 
						&& userForm.getLastName() == "") {
					mv.addObject("regerr", Constants.EMPTY_FIELDS);
					mv.setViewName("guest_user_signup");
					return mv;
				} 
				else{
					if(userForm.getOrganizationId() != ""){//It is invited member
						userForm.setInvited(true);
						UserAccount userAccount = commonService.registerUser(userForm, userForm.getPlan(), userForm.getRegType());
						UserProfile memberProfile = commonService.getUserProfileByEmailAdd(userAccount.getUserProfile().getEmail());
						new CreateNewMember(memberProfile, userForm.getOrganizationId(), userForm.getToken(), commonService);
						mv.addObject("err", Constants.SUCCESSFULLY_REGISTERED);
						return mv;
					}
					else{ // fresh registration
					SubscriptionPlan avaliablePlans = commonService.getSubscriptionPlanById(userForm.getPlan());
					//featureAccessLevel = avaliablePlans.getFeatureAccessLevelList();
					UserAccount registerUser = commonService.registerUser(userForm, userForm.getPlan(), userForm.getRegType());
					try{
					if( !registerUser.getUserProfile().getOrganization().equals(null) ){
						mv.addObject("user", userForm);
						mv.addObject("plans", avaliablePlans);
						mv.addObject("name", userForm.getFirstName());
						mv.addObject("id", String.valueOf(userForm.getPlan()));
						mv.addObject("userEmail", userForm.getEmail());
						mv.addObject("err", Constants.SUCCESSFULLY_REGISTERED);
						mv.addObject("organizationName", registerUser.getUserProfile().getOrganization().getName());
						mv.setViewName("guest_user_create_organization");
						return mv;
					}
					}
					catch(Exception e){
						mv.addObject("err", Constants.SUCCESSFULLY_REGISTERED);
						mv.setViewName("guest_user_home");
						return mv;
					}
					}
					mv.setViewName("guest_user_home");
					return mv;
				}
	}
	
	@RequestMapping(value="/signUp/{token}", method = RequestMethod.GET)
	public ModelAndView signUpPage(@PathVariable("token") String token, HttpSession session){
		ModelAndView mv = new ModelAndView("guest_user_signup");	
		try{
			Invitation invitationDetails = commonService.getInvitedMemberDetails(token);  //Getting Invitation Details
			if(invitationDetails != null){  //Checking if user is invited or not
				
				UserAccount invitedMemberUserAccount = commonService.getUserDetailsByEmailAdd(invitationDetails.getFriendEmailId());  //Getting invited Member account details.
				
				if(invitedMemberUserAccount != null){ //if invited member has useraccount
				Long invitedMemberProfileId = invitedMemberUserAccount.getUserProfile().getId();
				List<Member> memberList = commonService.getMemberListbyProfileId(invitedMemberProfileId); // Getting MemberList of that user to know for which organization user is added.
				if(memberList != null){// if member is available in list
					for(Member invitedMember : memberList){// Checking is not already member of that user
						if( (String.valueOf(invitedMember.getOrganization().getId()) ).equals( String.valueOf(invitationDetails.getOrganizationId() ) )){ //if it added in user organization
							mv.addObject("err", Constants.REGISTER_USER_EXIST_IN_LIST);
							return mv;
						}
					}
					new CreateNewMember(invitationDetails, commonService);// if not then create a new member for that organization
					mv.addObject("err", Constants.ADDED_SUCCESSFULLY);
					return mv;
				}
				else {// If Member is not available in List then create new Member for organization
					new CreateNewMember(invitationDetails, commonService);
					mv.addObject("err", Constants.ADDED_SUCCESSFULLY);
					return mv;
				}
			}
		}
	}
		catch(NoResultException n){  // for fresh registration
			List<SubscriptionPlan> subscriptionPlan = commonService.getFreeDefaultPlan(SubscriptionType.FREE);
			Invitation invitationDetails = commonService.getInvitedMemberDetails(token);
			mv.addObject("invitationDetail", invitationDetails);
			mv.addObject("subscriptionPlanType", subscriptionPlan);
			return mv;
		}
		catch (Exception e) {
			e.printStackTrace();
			mv.addObject("err", Constants.INVALID_LINK);
			return mv;
		}
		return mv;
	}
	
	@RequestMapping(value="/category", method = RequestMethod.GET)
	public ModelAndView getCategoryPage(){
		ModelAndView mv = new ModelAndView("guest_user_category");
		mv.addObject("categoryMapData",commonService.getAllCategories()); 
		return mv;
	}

	@RequestMapping(value="/contactus", method = RequestMethod.GET)
	public String getContactUsPage(){
		return "guest_user_contactus";
	}
	
	@RequestMapping(value="/subscription", method = RequestMethod.GET)
	public ModelAndView getSubscriptionPage(){
		ModelAndView mv = new ModelAndView("guest_user_subscription");
		List<SubscriptionPlan> allSubscriptions = new ArrayList<SubscriptionPlan>();
		allSubscriptions.add(commonService.getDefaultSubscriptionPlan(SubscriptionMode.ONLINE, RegistrationType.FREE));
		allSubscriptions.add(commonService.getDefaultSubscriptionPlan(SubscriptionMode.ONLINE, RegistrationType.STANDARD));
		allSubscriptions.add(commonService.getDefaultSubscriptionPlan(SubscriptionMode.ONLINE, RegistrationType.PREMIUM));
		List<Feature> featureList = commonService.getAllFeaturebyViewType(ViewType.PUBLIC);
		mv.addObject("subscriptions", createSubscriptionFeatureMap(allSubscriptions,featureList));
		mv.addObject("planDetails", allSubscriptions);
		return mv;
	}
	
	private HashMap<String,ArrayList<FeatureType>> createSubscriptionFeatureMap(List<SubscriptionPlan> subscriptionPlanList,List<Feature> featureList){
		HashMap<String,ArrayList<FeatureType>> subFeatureMap = new HashMap<String,ArrayList<FeatureType>>();
		for(Feature feature : featureList){
			subFeatureMap.put(feature.getFeatureName(), new ArrayList<FeatureType>());
		}
		for(SubscriptionPlan plan : subscriptionPlanList){
			for(FeatureAccessLevel level : plan.getFeatureAccessLevelList()){
				if((level.getFeature().getViewType().toString()).equals("PUBLIC")){
					ArrayList<FeatureType> featureValue = subFeatureMap.get(level.getFeature().getFeatureName());
					featureValue.add(level.getFeatureType());	
				}
			}
		}
		return subFeatureMap;
	}

	@RequestMapping(value="/howtous", method = RequestMethod.GET)
	public String getHowtoUsePage(){
		return "guest_user_howtous";
	}

	@RequestMapping(value="/home", method = RequestMethod.GET)
	public String getHomePage(){
		return "guest_user_home";
	}
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String homePage(){
		return "guest_user_home";
	}
	
	@RequestMapping(value="/article", method = RequestMethod.GET)
	public String createArticle(){
		return "guest_user_article";
	}
	
	@RequestMapping(value="/saveUserOrganization", method = RequestMethod.POST)
	public ModelAndView saveUserOrganization(@ModelAttribute("registration") UserForm userForm,	@RequestParam(value="userEmail") String userEmail,
	@RequestParam(value="planId") String PurchasedPlanId, HttpServletRequest req, BindingResult result){
		ModelAndView mv = new ModelAndView("guest_user_home");
		UserProfile userProfile = commonService.getUserProfileByEmailAdd(userEmail);
		commonService.saveUserOrganizationDetails(userProfile, userForm);		
		mv.addObject("err", "You Have Been Successfully Registered");
		return mv;
	}
	
	@RequestMapping(value="/saveContactReq", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView saveContactRequest(@ModelAttribute("saveContactReq") ContactRequest contactRequest, HttpServletRequest request, HttpServletResponse response){
		ModelAndView modelAndView = new ModelAndView("guest_user_home");
		commonService.saveContactReq(contactRequest);
		modelAndView.addObject("err","Your Contact Us Request has been sent successfully.");
		return modelAndView;
	}
	
	@RequestMapping(value = "/privacyPolicy", method = RequestMethod.GET)
	public ModelAndView viewPrivacyPolicy(HttpServletRequest req){
		ModelAndView mv = new ModelAndView("guest_user_view_Privacy_Policy_page");
		return mv;
	}
	
	@RequestMapping(value = "/term&Condition", method = RequestMethod.GET)
	public ModelAndView viewTermCondition(HttpServletRequest req){
		ModelAndView mv = new ModelAndView("guest_user_view_Term&Condition_page");
		return mv;
	}
	
}
