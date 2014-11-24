package com.innverse.elearn.services;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.innverse.elearn.dao.CommonDaoImpl;
import com.innverse.elearn.dao.AdminDaoImpl;
import com.innverse.elearn.eenum.FeatureType;
import com.innverse.elearn.eenum.MemberRole;
import com.innverse.elearn.eenum.RegistrationType;
import com.innverse.elearn.eenum.PlanType;
import com.innverse.elearn.eenum.RoleType;
import com.innverse.elearn.eenum.SubscriptionType;
import com.innverse.elearn.json.AdminAccountForm;
import com.innverse.elearn.json.EditSubscriptionForm;
import com.innverse.elearn.json.FeatureAccessLevelForm;
import com.innverse.elearn.json.OrganizationForm;
import com.innverse.elearn.json.SubscriptionOfferForm;
import com.innverse.elearn.json.UserForm;
import com.innverse.elearn.mail.ContactUsResponseMail;
import com.innverse.elearn.mail.ContactUsResquestMail;
import com.innverse.elearn.model.AdminAccount;
import com.innverse.elearn.model.Category;
import com.innverse.elearn.model.ContactRequest;
import com.innverse.elearn.model.Content;
import com.innverse.elearn.model.Feature;
import com.innverse.elearn.model.FeatureAccessLevel;
import com.innverse.elearn.model.MemberFALPermission;
import com.innverse.elearn.model.Organization;
import com.innverse.elearn.model.Permission;
import com.innverse.elearn.model.Series;
import com.innverse.elearn.model.Subscription;
import com.innverse.elearn.model.SubscriptionOffer;
import com.innverse.elearn.model.SubscriptionPlan;
import com.innverse.elearn.model.UserAccount;
import com.innverse.elearn.model.UserProfile;
import com.innverse.elearn.util.DateTimeUtils;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

public class AdminServiceImpl {

	@Autowired
	AdminDaoImpl adminDao;
	
	@Autowired
	CommonDaoImpl commonDao;
	
	@SuppressWarnings("unchecked")
	public void createAdminAccount(AdminAccountForm adminAccountForm){
		AdminAccount adminAccount = new AdminAccount();
		UserProfile adminProfile = new UserProfile();
		//TODO
		adminAccount.setUsername(adminAccountForm.getUsername());
		adminAccount.setPassword(adminAccountForm.getPassword());
		
		Permission permission = new Permission();
		permission.setCreateData(true);
		permission.setViewData(true);
		permission.setEditData(true);
		permission.setDeleteData(true);
		adminAccount.setPermission(permission);
		
		adminProfile.setFirstName(adminAccountForm.getName());
		adminProfile.setActive(true);
		adminProfile.setRoleType(RoleType.ADMIN);
		adminAccount.setUserProfile(adminProfile);
		
		
		adminDao.saveAdminDetails(adminAccount);
	}

	public void saveNewFeatures(Feature feature){
		adminDao.saveNewFeatures(feature);
	}
	
	public void saveSuperAdmin(UserForm userForm, UserProfile userProfile, HttpServletRequest req){
		userProfile.setFirstName(userForm.getFirstName());
		userProfile.setLastName(userForm.getLastName());
		userProfile.setRoleType(RoleType.SUPERADMIN);
		
		Permission permission = new Permission();
		String [] result = req.getParameterValues("permission");
				for(String s : result){
					if(s.equalsIgnoreCase("createData")){
							permission.setCreateData(true);
						}
					if(s.equalsIgnoreCase("viewData")){
							permission.setViewData(true);
						}
					if(s.equalsIgnoreCase("editData")){
							permission.setEditData(true);
						}
					if(s.equalsIgnoreCase("deleteData")){
							permission.setDeleteData(true);
						}
		}
		Permission savedPermission = adminDao.savePermission(permission);

		AdminAccount adminAccount = new AdminAccount();
		adminAccount.setPassword(userForm.getPassword());
		adminAccount.setUsername(userForm.getUsername());
		adminAccount.setUserProfile(userProfile);
		adminAccount.setPermission(savedPermission);
		adminDao.saveSuperAdmin(adminAccount);
	}
	
	public AdminAccount getAdminAccountbyUserName(String username){
		return adminDao.getAdminAccountbyUserName(username);
	}
	
	
	public RoleType getRoleById(long userId){
		return adminDao.getRoleById(userId);
	}
	
	public void saveSubOffers(SubscriptionOfferForm subOffer, HttpSession session, HttpServletRequest req) throws ParseException{	
		StringBuffer baseUrl = new StringBuffer(req.getParameter("basePath"));
		baseUrl.append(subOffer.getOfferImageURL());
		SubscriptionOffer subscriptionOffer = new SubscriptionOffer();
		subscriptionOffer.setCode(subOffer.getOfferCode());
		subscriptionOffer.setAdImageURL(baseUrl.toString());
		subscriptionOffer.setOfferAmount(subOffer.getOfferAmount());
		subscriptionOffer.setPlanType(subOffer.getPlanType());
		subscriptionOffer.setRegistrationType(subOffer.getRegistrationType());
		Date date = new Date();
		subscriptionOffer.setCreationTime(new Timestamp(date.getTime()));
		subscriptionOffer.setValid(true);
		subscriptionOffer.setOfferType(subOffer.getOfferType());		
		subscriptionOffer.setDescription(subOffer.getDescription());
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        date = sdf.parse(subOffer.getExpiryDate());            
		subscriptionOffer.setExpiryDate(date);
		UserProfile uPAccount = adminDao.getUserDetails(String.valueOf(session.getAttribute("userfullname")));
		subscriptionOffer.setPlanTime(subOffer.getPlanTime());		
		subscriptionOffer.setCreatedBy(uPAccount);
		adminDao.saveSubOffers(subscriptionOffer);
	}
	
	public boolean isAdminExist(String userName){
		return adminDao.isAdminExist(userName);
	}
	
	public List<SubscriptionOffer> getSubOfferList(){
		List<SubscriptionOffer> teacher = adminDao.getSubOfferList();
		return teacher;
	}
	
	public SubscriptionOffer getsubOfferDetailById(String id){
		return adminDao.getsubOfferDetailById(Long.valueOf(id));
	}
	
	public void saveEditOffer(SubscriptionOfferForm subOffer, HttpSession session, String offerId) throws ParseException{			
		
		SubscriptionOffer subscriptionOffer = adminDao.getsubOfferDetailById(Long.valueOf(offerId));
		
		subscriptionOffer.setCode(subOffer.getOfferCode());
		subscriptionOffer.setAdImageURL(subOffer.getOfferImageURL());
		subscriptionOffer.setOfferAmount(subOffer.getOfferAmount());		
		
		Date date = new Date();
		subscriptionOffer.setCreationTime(new Timestamp(date.getTime()));			
		subscriptionOffer.setDescription(subOffer.getDescription());
		
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        
        date = sdf.parse(subOffer.getExpiryDate());            
		subscriptionOffer.setExpiryDate(date);		
		
		subscriptionOffer.setPlanTime(subOffer.getPlanTime());			
		adminDao.saveSubOffers(subscriptionOffer);
	}
	
	public void deleteSubOfferById(String offerId){
		adminDao.deleteSubOfferById(Long.valueOf(offerId));
	}
	
	public List<Feature> getFeatureList(){
		return adminDao.getAllFeaturesList();
	}
	
	public Feature getFeatureById(String featureId){
		return adminDao.getFeatureById(Long.valueOf(featureId));
	}
	
	public void saveEditfeature(Feature feature, HttpSession session, String featureId, HttpServletRequest req){
		Feature feat = getFeatureById(featureId);
		
		StringBuffer imageUrl = new StringBuffer(req.getParameter("basePath"));
		imageUrl.append(feature.getImageURL());
		
		feat.setActionName(feature.getActionName());
		feat.setActive(true);
		feat.setDescription(feature.getDescription());
		feat.setImageURL(imageUrl.toString());
		feat.setFeatureName(feature.getFeatureName());
		adminDao.saveNewFeatures(feat);
	}
	
	public void deleteFeatureById(String featureId){
		adminDao.deleteFeatureById(Long.valueOf(featureId));		
	}
	

	public List<Category> getAllCategories(){
		return adminDao.getAllCategories();
	}
	
	public List<Series> getAllSeries(){
		return adminDao.getAllSeries();
	}
	
	public List<Content> getAllContents(){
		return adminDao.getAllContents();
	}
	
	public List<AdminAccount> getSuperAdminList(){
		return adminDao.getSuperAdminList();
	}
	
	public AdminAccount getSuperAdminById(String id){
		return adminDao.getSuperAdminById(Long.valueOf(id));
	}
	
	public void deleteSuperAdminById(String superAdminId){
		adminDao.deleteSuperAdminById(Long.valueOf(superAdminId));		
	}
	
	public void saveEditSuperAdmin(String id, UserForm userForm, HttpServletRequest req){
		
		AdminAccount editSuperAdmin = getSuperAdminById(id);
		
		UserProfile userProfile = adminDao.getUserProfile(editSuperAdmin.getUserProfile().getId());
		userProfile.setFirstName(userForm.getFirstName());
		userProfile.setLastName(userForm.getLastName());
		
		Permission permission = adminDao.getPermissionById(editSuperAdmin.getPermission().getId());
		permission.setCreateData(false);
		permission.setDeleteData(false);
		permission.setEditData(false);
		permission.setViewData(false);
		String [] result = req.getParameterValues("permission");
				for(String s : result){
					if(s.equalsIgnoreCase("createData")){
							permission.setCreateData(true);
						}
					if(s.equalsIgnoreCase("viewData")){
							permission.setViewData(true);
						}
					if(s.equalsIgnoreCase("editData")){
							permission.setEditData(true);
						}
					if(s.equalsIgnoreCase("deleteData")){
							permission.setDeleteData(true);
						}
		}
				editSuperAdmin.setUsername(userForm.getUsername());
				editSuperAdmin.setUserProfile(userProfile);
				editSuperAdmin.setPermission(permission);
				adminDao.saveSuperAdmin(editSuperAdmin);
	}
	
	public List<Organization> getOrganizationList(){
		List<Organization> organizationList = adminDao.getOrganizationList();
		return organizationList;
	}
	
	public Organization getOrganizationById(String orgId){
		return adminDao.getOrganizationById(Long.valueOf(orgId));
	}
	
	public void saveNewOrganization(OrganizationForm orgForm){
		SubscriptionPlan plan = adminDao.getSubscriptionPlanByName(orgForm.getPlanName());
		
		Subscription subscription = new Subscription();
		subscription.setSubscriptionPlan(plan);
		
		Organization organization = new Organization();
		organization.setName(orgForm.getOrgName());
		organization.setWebsite(orgForm.getWebsite());
		organization.setAboutOrganization(orgForm.getAboutOrganization());
		organization.setTagLine(orgForm.getTagLine());
		organization.setSubscription(subscription);
		organization.setApproved(true);
		organization.setActive(true);
		
		UserProfile userProfile = new UserProfile();
		userProfile.setEmail(orgForm.getEmail());
		userProfile.setRoleType(RoleType.USER);
		userProfile.setOrganization(organization);
		userProfile.setFirstName("createdByAdmin");
		
		UserAccount userAccount = new UserAccount();
		userAccount.setUserProfile(userProfile);
		userAccount.setUsername(orgForm.getEmail());
		userAccount.setPassword(orgForm.getPassword());
		
		adminDao.createUserAccount(userAccount);
	}
	
	public void saveEditOrganizationById(String id, OrganizationForm orgForm){
		
		Organization organization = adminDao.getOrganizationById(Long.valueOf(id));
		
		organization.setName(orgForm.getOrgName());
		organization.setWebsite(orgForm.getWebsite());
		organization.setAboutOrganization(orgForm.getAboutOrganization());
		organization.setTagLine(orgForm.getTagLine());
		organization.setApproved(true);
		organization.setActive(true);
		
		UserProfile userProfile = adminDao.getUserProfile(organization.getUserProfile().getId());
		userProfile.setEmail(orgForm.getEmail());
		userProfile.setRoleType(RoleType.USER);
		userProfile.setOrganization(organization);
		userProfile.setFirstName("createdByAdmin");
		
		UserAccount userAccount = adminDao.getUserAcount(userProfile.getId());
		userAccount.setUserProfile(userProfile);
		userAccount.setUsername(orgForm.getEmail());
		userAccount.setPassword(orgForm.getPassword());
		
		adminDao.createUserAccount(userAccount);
	}
	
	public void deleteOrganizationById(String id){
		Organization organization = adminDao.getOrganizationById(Long.valueOf(id));

		organization.setActive(false);
		
		UserProfile userProfile = adminDao.getUserProfile(organization.getUserProfile().getId());
		userProfile.setOrganization(organization);
		
		UserAccount userAccount = adminDao.getUserAcount(userProfile.getId());
		userAccount.setUserProfile(userProfile);
		
		adminDao.createUserAccount(userAccount);		
	}
	
	public List<ContactRequest> getConatctReqList(){
		return adminDao.getConatctReqList();
	}
	
	public ContactRequest getConatctReqById(String id){
		return adminDao.getConatctReqById(Long.valueOf(id));
	}
	
	public void replyContactReqById(ContactRequest contactRequest, String id){
		ContactRequest contactReq = adminDao.getConatctReqById(Long.valueOf(id));
		contactReq.setStatus("Completed");
		contactReq.setSuggetion(contactRequest.getSuggetion());
		Date date = new Date();
		contactReq.setResDate(new Timestamp(date.getTime()));
		try{
			ContactUsResponseMail contactUsResMail = new ContactUsResponseMail();
			contactUsResMail.email(contactReq.getEmail(), contactReq.getUserName(), contactReq.getDescription(), contactRequest.getSuggetion(), contactReq.getMobile());
		}catch(Exception e){
			e.printStackTrace();
		}
		adminDao.updateContactReq(contactReq);
	}
	
	public void deleteContactById(String id){
		adminDao.deleteContactById(Long.valueOf(id));		
	}
	
	public void editSubscriptionPlan(EditSubscriptionForm editForm, SubscriptionPlan subscriptionPlan){
		Set<FeatureAccessLevel> featureAccessLevels = new HashSet<FeatureAccessLevel>();	// New FAL Set
		List<FeatureAccessLevel> newFeatureAccessLevels = new ArrayList<FeatureAccessLevel>();	// New FAL for newly added features
		if(editForm.getAvailableFeatures() != null){		// If New Features Available
			for(FeatureAccessLevelForm form : editForm.getAvailableFeatures()){
				FeatureAccessLevel newFALList = new FeatureAccessLevel();
					Feature feature = commonDao.getFeatureById(Long.valueOf(form.getFeatureid()));
					newFALList.setFeature(feature);
					newFALList.setFeatureType(form.getFeatureType());
					newFALList.setValue(form.getValue());
					featureAccessLevels.add(newFALList);
			}
		}
			for(FeatureAccessLevelForm form : editForm.getAccessLevels()){	// For saving the changes of current Features 
				if(form.getFeatureid() != null){
					FeatureAccessLevel FALLevel = new FeatureAccessLevel();
					Feature feature = commonDao.getFeatureById(Long.valueOf(form.getFeatureid()));
					FALLevel.setFeature(feature);
					FALLevel.setFeatureType(form.getFeatureType());
					FALLevel.setValue(form.getValue());
					newFeatureAccessLevels.add(FALLevel);
				}
		}
			
		featureAccessLevels.addAll(newFeatureAccessLevels);		// Adding Both Lists
		subscriptionPlan.setFeatureAccessLevelList(featureAccessLevels);	// Saving to a Selected Plan
		commonDao.saveSubcriptionPlan(subscriptionPlan);					//
		
		List<Organization> organizations = adminDao.getOrganizationByplan(subscriptionPlan);
		// Getting Selected organization of a particular Plan Type.
		for(Organization planOrganization : organizations){		// For update the organizations that has this Plan. 
			for(MemberFALPermission memberPermission : planOrganization.getMemberFALPermissions()){
				if( (memberPermission.getMemberRole()).equals(MemberRole.AdminRole) ){
					memberPermission.setFeatureAccessLevelList(featureAccessLevels);
				}
			}
			adminDao.updateOrganization(planOrganization);		// Saved the Changes
		}
}
	
}
