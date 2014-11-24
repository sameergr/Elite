package com.innverse.elearn.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.innverse.elearn.contants.Constants;
import com.innverse.elearn.eenum.FeatureType;
import com.innverse.elearn.json.EditSubscriptionForm;
import com.innverse.elearn.json.OrganizationForm;
import com.innverse.elearn.json.SubscriptionOfferForm;
import com.innverse.elearn.json.SubscriptionPlanForm;
import com.innverse.elearn.json.UserForm;
import com.innverse.elearn.model.AdminAccount;
import com.innverse.elearn.model.Category;
import com.innverse.elearn.model.ContactRequest;
import com.innverse.elearn.model.Content;
import com.innverse.elearn.model.Feature;
import com.innverse.elearn.model.FeatureAccessLevel;
import com.innverse.elearn.model.Member;
import com.innverse.elearn.model.Organization;
import com.innverse.elearn.model.Series;
import com.innverse.elearn.model.SubscriptionOffer;
import com.innverse.elearn.model.SubscriptionPlan;
import com.innverse.elearn.model.UserAccount;
import com.innverse.elearn.model.UserPayment;
import com.innverse.elearn.model.UserProfile;
import com.innverse.elearn.services.AdminServiceImpl;
import com.innverse.elearn.services.CommonServiceImpl;
import com.innverse.elearn.session.SessionConfig;

@Controller
@SessionAttributes
@RequestMapping(value = "/admin")
public class AdminController extends AbstractController{
	
	@Autowired
	AdminServiceImpl adminservice; 
	
	@Autowired
	CommonServiceImpl commonservice;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String adminLogin(HttpServletRequest req){
		return "admin_login";
	}
	
	@RequestMapping(value = "/createFeature", method = RequestMethod.GET)
	public String createFeaturePage(@ModelAttribute("features") Feature feature, HttpServletRequest req){
		ModelAndView mv = new ModelAndView();
		mv.addObject("viewType", feature.getViewType());
		return "admin_createFeature_page";
	}
	
	@RequestMapping(value = "/manageContent", method = RequestMethod.GET)
	public ModelAndView manageContentPage(HttpServletRequest req){
		ModelAndView mv = new ModelAndView("admin_manage_content");
		List<Category> allCategories = adminservice.getAllCategories();
		List<Series> allSeries = adminservice.getAllSeries();
		List<Content> contentList = adminservice.getAllContents();
		mv.addObject("contentList", contentList);
		mv.addObject("allSeries", allSeries);
		mv.addObject("allCategories", allCategories);
		return mv;
	}

	
	@RequestMapping(value = "/auth", method = RequestMethod.POST)
	public ModelAndView adminHome(@ModelAttribute("adminLogin") UserForm userForm, HttpServletRequest req){
		ModelAndView mv = new ModelAndView("admin_login");
		if((userForm.getUsername()).equals("") && (userForm.getPassword()).equals("")){
			mv.addObject("err",Constants.EMPTY_FIELDS);
			mv.setViewName("admin_login");
			return mv;
		}
		else{
			try{
				AdminAccount admin = adminservice.getAdminAccountbyUserName(userForm.getUsername());
				SessionConfig sessionConfig = new SessionConfig();
				HttpSession session = sessionConfig.createNewSession(req);
				session.setAttribute("userName", admin.getUsername());
				session.setAttribute("admin", admin);
				session.setAttribute("userfullname", admin.getUserProfile().getFirstName());
// Admin or Super Admin's Permissions
				session.setAttribute("cp", admin.getPermission().isCreateData());
				session.setAttribute("vp", admin.getPermission().isViewData());
				session.setAttribute("ep", admin.getPermission().isEditData());
				session.setAttribute("dp", admin.getPermission().isDeleteData());
//-----------------------------------
				mv.addObject("name",admin.getUserProfile().getFirstName());
				mv.setViewName("admin_dashboard");
			}catch(Exception e){
				mv.addObject("err", Constants.USERNAME_PASSWORD_INCORRECT);
				mv.setViewName("admin_login");
			}
			return mv;
		}
	}
	
	@RequestMapping(value="/dashboard", method = RequestMethod.GET)
	public ModelAndView userDashboard(HttpServletRequest req){
		ModelAndView mv = new ModelAndView("admin_dashboard");
		HttpSession session = req.getSession(false);
		if(session.getAttribute("userName")!=null){
			return mv;
		}
		else{
			mv.setViewName("guest_user_home");
			return mv;
		}
	}
	
	@RequestMapping(value="/signOut", method = RequestMethod.GET)
	public ModelAndView userSignOut(HttpServletRequest req){
		ModelAndView mv = new ModelAndView("admin_signout");
		HttpSession session = req.getSession(false);
		session.removeAttribute("userName");
		session.removeAttribute("userfullname");
		session.removeAttribute("cp");
		session.removeAttribute("vp");
		session.removeAttribute("ep");
		session.removeAttribute("dp");
		session.invalidate();
		return mv;
	}
	
	
	@RequestMapping(value = "/createSuperAdminPage", method = RequestMethod.GET)
	public ModelAndView showSApage(HttpServletRequest req){
		ModelAndView mv = new ModelAndView("admin_superAdmin_page");
		return mv;
	}
	

	@RequestMapping(value="/registerSuperAdmin", method = RequestMethod.POST)
	public ModelAndView saveSuperAdmin(@ModelAttribute("superadminRegistration")UserForm userForm ,HttpServletRequest req){
		ModelAndView mv = new ModelAndView("admin_superAdmin_page");
		boolean status = adminservice.isAdminExist(userForm.getUsername());
		if(status){
			mv.addObject("err", Constants.ALREADY_EXIST);
			return mv;
		}
		else{
			UserProfile userProfile = new UserProfile();
			mv.addObject("err", Constants.SUPERADMIN_CREATED);
			adminservice.saveSuperAdmin(userForm,userProfile, req);
			return mv;
		}
	}
	
	@RequestMapping(value="/saveFeatures", method=RequestMethod.POST)
	public ModelAndView saveFeatures(@ModelAttribute("features") Feature feature, HttpServletRequest req, HttpServletResponse res){
		ModelAndView mv = new ModelAndView("admin_createFeature_page");
		HttpSession session = req.getSession(false);
		String authorUsername =	(String) session.getAttribute("userName");
		String fullName = (String) session.getAttribute("userfullname");
		feature.setActive(false);
		feature.setAuthername(fullName);
		feature.setAutherUsername(authorUsername);
		adminservice.saveNewFeatures(feature);
		mv.addObject("status", Constants.FEATURES_ADDED);
		return mv;
	}
	
	@RequestMapping(value="/createSubOffer", method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView createSubOffer(@ModelAttribute("subOffer") SubscriptionOfferForm subOffer, HttpServletRequest req, HttpServletResponse res){
		ModelAndView mv = new ModelAndView();
		mv.addObject("planType", subOffer);	
		mv.setViewName("admin_createSubOffer_page");
		return mv;
	}
	
	@RequestMapping(value="/saveSubOffer", method=RequestMethod.POST)
	public ModelAndView saveSubOffer(@ModelAttribute("subOffer") SubscriptionOfferForm subOffer, HttpServletRequest req, HttpServletResponse res, HttpSession session) throws ParseException{
		ModelAndView mv = new ModelAndView("admin_createSubOffer_page");				
		adminservice.saveSubOffers(subOffer, session, req);
		mv.addObject("status", Constants.SUBSCRIPTION_OFFER_CREATED);
		return mv;
	}
	
	@RequestMapping(value="/listOfSubOffers", method=RequestMethod.GET)
	public ModelAndView listOfSubOfferList() {
		ModelAndView mv = new ModelAndView("admin_listOfSubOfferList_page");		
		List<SubscriptionOffer> subOfferList = adminservice.getSubOfferList();
		mv.addObject("subOfferList", subOfferList);
		return mv;
	}
	
	@RequestMapping(value="/subOfferDetail/{id}", method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView subOfferDetailById(@PathVariable("id") String id){
		ModelAndView mv = new ModelAndView("admin_subOfferDetail_page");
		SubscriptionOffer offer = adminservice.getsubOfferDetailById(id);		
		mv.addObject("offer", offer);		
		return mv;
	}
	
	@RequestMapping(value="/editSubOffer/{id}", method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView subOfferEditById(@PathVariable("id") String id){
		ModelAndView mv = new ModelAndView("admin_editSubOffer_page");
		SubscriptionOffer offer = adminservice.getsubOfferDetailById(id);		
		mv.addObject("offer", offer);		
		return mv;
	}
	
	@RequestMapping(value="/saveEditSubOffer/{id}", method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView subOfferEditById(@PathVariable("id") String offerid,@ModelAttribute("subOffer") SubscriptionOfferForm subOfferForm, HttpSession session) throws ParseException{
		ModelAndView mv = new ModelAndView();
		if((subOfferForm!=null)&& (offerid!=null)){
			adminservice.saveEditOffer(subOfferForm, session, offerid);			
		}
		else{
			mv.addObject("err", Constants.OFFER_NOT_UPDATED);
			mv.setViewName("admin_editSubOffer_page");	
		}		
		List<SubscriptionOffer> subOfferList = adminservice.getSubOfferList();
		if((subOfferList!=null)){
		mv.addObject("subOfferList", subOfferList);
		mv.setViewName("admin_listOfSubOfferList_page");
		}
		return mv;			
	}	

	@RequestMapping(value="/deleteSubOffer/{id}", method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView subOfferDeleteById(@PathVariable("id") String offerid,HttpSession session){
		ModelAndView mv = new ModelAndView();
		if(offerid!=null){
			adminservice.deleteSubOfferById(offerid);		
		}
		else{
			mv.addObject("err", Constants.OFFER_NOT_UPDATED);
			mv.setViewName("admin_editSubOffer_page");	
		}		
		List<SubscriptionOffer> subOfferList = adminservice.getSubOfferList();
		if((subOfferList!=null)){
		mv.addObject("subOfferList", subOfferList);
		mv.setViewName("admin_listOfSubOfferList_page");
		}
		return mv;			
	}
	
	@RequestMapping(value="/listOfFeature", method=RequestMethod.GET)
	public ModelAndView listOfFeature() {
		ModelAndView mv = new ModelAndView("admin_listOfFeature_page");		
		List<Feature> featList = adminservice.getFeatureList();
		mv.addObject("featList", featList);	
		return mv;
	}
	
	@RequestMapping(value="/viewFeature/{id}", method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView viewFeatureById(@PathVariable("id") String featureId){
		ModelAndView mv = new ModelAndView("admin_View_Feature_page");
		Feature feature	= adminservice.getFeatureById(featureId);
		mv.addObject("feature", feature);		
		return mv;
	}
	
	@RequestMapping(value="/editFeature/{id}", method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView editFeatureById(@PathVariable("id") String featureId){
		ModelAndView mv = new ModelAndView("admin_editFeature_page");
		Feature feature	= adminservice.getFeatureById(featureId);
		mv.addObject("feature", feature);		
		return mv;
	}	
	
	@RequestMapping(value="/saveEditfeature/{id}", method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView saveEditfeature(@PathVariable("id") String featureId,@ModelAttribute("editfeature") Feature feature, HttpSession session, HttpServletRequest req){
		ModelAndView mv = new ModelAndView();
		if((featureId!=null)&& (feature!=null)){
			adminservice.saveEditfeature(feature, session, featureId, req);			
		}
		else{
			mv.addObject("err", Constants.FEATURE_NOT_UPDATED);
			mv.setViewName("admin_editFeature_page");	
		}
		List<Feature> featList = adminservice.getFeatureList();
		if(featList!=null){
			mv.addObject("featList", featList);	
			mv.setViewName("admin_listOfFeature_page");
		}
	return mv;			
	}
	
	@RequestMapping(value="/deleteFeature/{id}", method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView deleteFeatureById(@PathVariable("id") String featureId){
				
		ModelAndView mv = new ModelAndView();
		if(featureId!=null){
			adminservice.deleteFeatureById(featureId);			
		}
		else{
			mv.addObject("err", Constants.FEATURE_NOT_UPDATED);
			mv.setViewName("admin_listOfFeature_page");	
		}
		List<Feature> featList = adminservice.getFeatureList();
		if(featList!=null){
			mv.addObject("featList", featList);	
			mv.setViewName("admin_listOfFeature_page");
		}
	return mv;			
	}
	
	@RequestMapping(value="/listOfSuperAdmin", method=RequestMethod.GET)
	public ModelAndView listOfSuperAdmin() {
		ModelAndView mv = new ModelAndView("admin_listOfSuperAdmin_page");		
		List<AdminAccount> superAdminList = adminservice.getSuperAdminList();
		mv.addObject("superAdminList", superAdminList);
		return mv;
	}
	
	@RequestMapping(value="/viewSuperAdmin/{id}", method=RequestMethod.GET)
	public ModelAndView viewSuperAdminDeatilById(@PathVariable("id") String id) {
		ModelAndView mv = new ModelAndView("admin_view_SuperAdmin_Detail_page");		
		AdminAccount superAdmin = adminservice.getSuperAdminById(id);
		mv.addObject("superAdmin", superAdmin);
		return mv;
	}
	
	@RequestMapping(value="/editSuperAdmin/{id}", method=RequestMethod.GET)
	public ModelAndView editSuperAdminById(@PathVariable("id") String id) {
		ModelAndView mv = new ModelAndView("admin_edit_SuperAdmin_page");		
		AdminAccount superAdmin = adminservice.getSuperAdminById(id);
		mv.addObject("superAdmin", superAdmin);
		return mv;
	}
	
	@RequestMapping(value="/saveEditSuperAdmin/{id}", method=RequestMethod.POST)
	public ModelAndView saveEditSuperAdminById(@PathVariable("id") String id, UserForm userForm, HttpServletRequest req) {
		ModelAndView mv = new ModelAndView("admin_listOfSuperAdmin_page");
		if(userForm!=null){
			adminservice.saveEditSuperAdmin(id, userForm, req);
		}
		List<AdminAccount> superAdminList = adminservice.getSuperAdminList();
		mv.addObject("superAdminList", superAdminList);
		mv.addObject("err", Constants.EDIT_SUPERADMIN);
		return mv;
	}
	
	@RequestMapping(value="/deleteSuperAdmin/{id}", method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView deleteSuperAdminById(@PathVariable("id") String superAdminId){
		ModelAndView mv = new ModelAndView();
		if(superAdminId!=null){
			adminservice.deleteSuperAdminById(superAdminId);			
		}
		else{
			mv.addObject("err", Constants.SUPERADMIN_NOT_DELETED);
			mv.setViewName("admin_listOfSuperAdmin_page");	
		}
		List<AdminAccount> superAdminList = adminservice.getSuperAdminList();
		if(superAdminList!=null)
			mv.addObject("superAdminList", superAdminList);	
			mv.setViewName("admin_listOfSuperAdmin_page");
		return mv;			
	}
	
	@RequestMapping(value = "/organization", method = RequestMethod.GET)
	public ModelAndView showOrganizationpage(HttpServletRequest req){
		ModelAndView mv = new ModelAndView("admin_Organization_page");
		List<Organization> organizationList = adminservice.getOrganizationList();
		mv.addObject("orgList", organizationList);
		return mv;
	}
	
	@RequestMapping(value = "/viewOrganization/{id}", method = RequestMethod.GET)
	public ModelAndView viewOrganizationById(@PathVariable("id") String orgId){
		ModelAndView mv = new ModelAndView("admin_View_Organization_page");
		Organization organization = adminservice.getOrganizationById(orgId);
		mv.addObject("org", organization);
		return mv;
	}
	
	@RequestMapping(value="/editOrganization/{id}", method=RequestMethod.GET)
	public ModelAndView editOrganizationById(@PathVariable("id") String orgId) {
		ModelAndView mv = new ModelAndView("admin_edit_Organization_page");
		Organization organization = adminservice.getOrganizationById(orgId);
		mv.addObject("org", organization);
		return mv;
	}
	
	@RequestMapping(value="/saveEditOrganization/{id}", method=RequestMethod.POST)
	public ModelAndView saveEditOrganizationById(@PathVariable("id") String id, @ModelAttribute("saveNewOrganization") OrganizationForm orgForm ) {
		ModelAndView mv = new ModelAndView("admin_Organization_page");
		if(orgForm != null){
			adminservice.saveEditOrganizationById(id, orgForm);
		}
		List<Organization> organizationList = adminservice.getOrganizationList();
		mv.addObject("orgList", organizationList);
		return mv;
	}
	
	@RequestMapping(value="/deleteOrganization/{id}", method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView deleteOrganizationById(@PathVariable("id") String id){
		ModelAndView mv = new ModelAndView();
		if(id!=null){
			adminservice.deleteOrganizationById(id);			
		}
		else{
			mv.addObject("err", Constants.ORGANIZATION_NOT_DELETED);
			mv.setViewName("admin_Organization_page");	
		}
		List<Organization> organizationList = adminservice.getOrganizationList();
		if(organizationList!=null)
			mv.addObject("orgList", organizationList);
			mv.addObject("err", Constants.ORGANIZATION_DELETED);
			mv.setViewName("admin_Organization_page");
		return mv;			
	}
	
	@RequestMapping(value = "/createNewOrganization", method = RequestMethod.GET)
	public ModelAndView createNewOrganization(@ModelAttribute("saveNewOrganization") OrganizationForm orgForm, HttpServletRequest req){
		ModelAndView mv = new ModelAndView("admin_createNewOrganization_page");
		mv.addObject("planType", orgForm);	
		return mv;
	}
	
	@RequestMapping(value = "/saveNewOrganization", method = RequestMethod.POST)
	public ModelAndView saveNewOrganization(@ModelAttribute("saveNewOrganization") OrganizationForm orgForm ,HttpServletRequest req){
		ModelAndView mv = new ModelAndView("admin_Organization_page");
		if(orgForm!=null){
			adminservice.saveNewOrganization(orgForm);
		}
		List<Organization> organizationList = adminservice.getOrganizationList();
		mv.addObject("orgList", organizationList);
		return mv;
	}
	
	@RequestMapping(value = "/viewReports", method = RequestMethod.GET)
	public ModelAndView viewReportspage(HttpServletRequest req){
		ModelAndView mv = new ModelAndView("admin_viewReports_page");
		return mv;
	}
	
	@RequestMapping(value="/contactRequest", method = RequestMethod.GET)
	public ModelAndView contactRequestPage(HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView("admin_contactRequestList_page");
		List<ContactRequest> contactReqList = adminservice.getConatctReqList();
		modelAndView.addObject("contactReqList", contactReqList);
		return modelAndView;
	}
	
	@RequestMapping(value="/contactRequest/{id}", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView contactRequestById(@PathVariable("id") String id, HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView("admin_replyContactRequest_page");
		ContactRequest contactReq = adminservice.getConatctReqById(id);
		modelAndView.addObject("contactReq", contactReq);
		return modelAndView;
	}
	
	@RequestMapping(value="/replyContactReq/{id}", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView replyContactReqById(@PathVariable("id") String id,@ModelAttribute("replyContactReq") ContactRequest contactRequest, HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView("admin_contactRequestList_page");
		if(contactRequest!=null){
			adminservice.replyContactReqById(contactRequest, id);
		}
		List<ContactRequest> contactReqList = adminservice.getConatctReqList();
		modelAndView.addObject("contactReqList", contactReqList);
		return modelAndView;
	}
	
	@RequestMapping(value="/deleteContactReq/{id}", method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView deleteContactReqById(@PathVariable("id") String id){
		ModelAndView mv = new ModelAndView();
		if(id!=null){
			adminservice.deleteContactById(id);			
		}
		else{
			mv.addObject("err", Constants.CONTACT_NOT_DELETED);
			mv.setViewName("admin_contactRequestList_page");	
		}
		List<ContactRequest> contactReqList = adminservice.getConatctReqList();
		if(contactReqList!=null)
			mv.addObject("contactReqList", contactReqList);
			mv.addObject("err", Constants.CONTACT_DELETED);
			mv.setViewName("admin_contactRequestList_page");
		return mv;			
	}
	
	
	int defaultPlanCount = 0;
	@RequestMapping(value="/createSubscriptionPlan", method = RequestMethod.GET)
	public ModelAndView getplan(@ModelAttribute("createSubPlan") SubscriptionPlanForm subscriptionPlan, HttpServletRequest request){
		List<SubscriptionPlan> subscriptionPlans = commonService.getAllSubscriptionPlans();
		for(SubscriptionPlan plans : subscriptionPlans){
			if(plans.isCheckDefault()){
				defaultPlanCount++;
			}
		}
		List<Feature> featureList = commonService.getAllFeature();
		ModelAndView modelAndView = new ModelAndView("reg_subscription_plan_admin");
		subscriptionPlan.addFeatureList(featureList);
		modelAndView.addObject("plan",subscriptionPlan);
		if(defaultPlanCount >= 3){
			modelAndView.addObject("view", Constants.NO_DEFAULT);
		}
		return modelAndView;
	}
	
	@RequestMapping(value="/createSubPlan", method = {RequestMethod.POST})
	public ModelAndView saveSubcription(@ModelAttribute("createSubPlan") SubscriptionPlanForm subscriptionPlan, HttpServletRequest request, HttpSession session){
		commonService.saveSubcriptionPlan(subscriptionPlan, session);
		List<Feature> featureList = commonService.getAllFeature();
		ModelAndView modelAndView = new ModelAndView("admin_dashboard");
		subscriptionPlan.addFeatureList(featureList);
		modelAndView.addObject("plan", subscriptionPlan);
		return modelAndView;
	} 
	
	@RequestMapping(value="/listOfSubPlan", method = RequestMethod.GET)
	public ModelAndView listOfSubPlan(HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession(false);
		ModelAndView mv = new ModelAndView("reg_subscription_plan_List");
				if(session.getAttribute("userName")!=null){
					List<SubscriptionPlan> subPlan = commonService.getAdminSideSubPlanList();
					mv.addObject("subPlan", subPlan);					
					}
				else{
					mv.setViewName("admin_login");
				}		
		return mv;
	}
	
	@RequestMapping(value="/viewSubPlan/{id}", method = RequestMethod.GET)
	public ModelAndView viewSubPlan(@PathVariable("id") String subPlanId, HttpServletRequest request, HttpServletResponse response){
		ModelAndView mv = new ModelAndView("reg_view_subscription_plan");
		SubscriptionPlan subplan = commonService.getSubscriptionPlanById(Long.valueOf(subPlanId));		
		mv.addObject("subplan", subplan);						
		return mv;
	}
	

	@RequestMapping(value="/editSubPlan/{id}", method = RequestMethod.GET)
		public ModelAndView editSubPlan(@ModelAttribute("editSubscription") EditSubscriptionForm editSubscriptionForm, @PathVariable("id") String subPlanId, HttpServletRequest request, HttpServletResponse response){
			ModelAndView mv = new ModelAndView("reg_edit_subscription_plan");
			SubscriptionPlan subplan = commonService.getSubscriptionPlanById(Long.valueOf(subPlanId));//subPlanId
			List<Feature> featureList = commonService.getAllFeature();
			
			List<Feature> planFeatureList = new ArrayList<Feature>();
			for(FeatureAccessLevel plan : subplan.getFeatureAccessLevelList()){
				planFeatureList.add(plan.getFeature());
			}
			editSubscriptionForm.addPlan(subplan);
			
			featureList.removeAll(planFeatureList);
			editSubscriptionForm.addFeatures(featureList);
			
			mv.addObject("editSubscriptionForm", editSubscriptionForm);
			mv.addObject("subplan", subplan);
			mv.addObject("err", Constants.NO_FEATURES);
			return mv;
		}	

	@RequestMapping(value = "saveEditSubscription", method = RequestMethod.POST)
		public ModelAndView editSubscriptionPan(@ModelAttribute("editSubscription") EditSubscriptionForm editSubscriptionForm,
				@RequestParam("planId") String planId, HttpServletRequest req){
			SubscriptionPlan subscriptionPlan = commonService.getSubscriptionPlanById(Long.valueOf(planId));
			adminservice.editSubscriptionPlan(editSubscriptionForm, subscriptionPlan);
			ModelAndView mv = new ModelAndView("reg_subscription_plan_List");
			if(req.getSession(false).getAttribute("userName")!=null){
				List<SubscriptionPlan> subPlan = commonService.getAdminSideSubPlanList();
				mv.addObject("subPlan", subPlan);					
				}
			else{
				mv.setViewName("admin_login");
			}
			return mv;
		}
	
/*	@RequestMapping(value="/deleteSubPlan/{id}", method ={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView deleteSubPlan(@PathVariable("id") String subPlanId, HttpSession session){
		ModelAndView mv = new ModelAndView("reg_subscription_plan_List");
		if(subPlanId!=null){
			commonService.deleteSubPlan(subPlanId);
			if(session.getAttribute("name")!=null){
				List<SubscriptionPlan> subPlan = commonService.getAdminSideSubPlanList();
				mv.addObject("subPlan", subPlan);					
				}
			else{
				UserAccount user = (UserAccount)session.getAttribute("user");
				List<SubscriptionPlan> subPlan = commonService.getSubPlanByAuthorId(user.getUserProfile().getId());
				mv.addObject("subPlan", subPlan);
			}
		}		
		return mv;
	}*/
	
	@RequestMapping(value = "paymentHistory", method = RequestMethod.GET)
	public ModelAndView paymentHistory(HttpServletRequest req){
		ModelAndView mv = new ModelAndView("admin_allPaymentHistory_page");
		List<UserPayment> paymentHistory = commonService.getFullPaymentHistory();
		mv.addObject("allPayments", paymentHistory);
		return mv;
	}
}
