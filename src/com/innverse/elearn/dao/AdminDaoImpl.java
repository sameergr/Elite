package com.innverse.elearn.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.innverse.elearn.eenum.RoleType;
import com.innverse.elearn.model.AdminAccount;
import com.innverse.elearn.model.Category;
import com.innverse.elearn.model.ContactRequest;
import com.innverse.elearn.model.Content;
import com.innverse.elearn.model.Feature;
import com.innverse.elearn.model.Organization;
import com.innverse.elearn.model.Permission;
import com.innverse.elearn.model.Series;
import com.innverse.elearn.model.Subscription;
import com.innverse.elearn.model.SubscriptionOffer;
import com.innverse.elearn.model.SubscriptionPlan;
import com.innverse.elearn.model.UserAccount;
import com.innverse.elearn.model.UserProfile;


@Transactional
public class AdminDaoImpl {

	@Autowired
	@PersistenceContext
	protected EntityManager em;
	
	protected EntityManagerFactory entityManagerFactory;
	
	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory){
		this.entityManagerFactory = entityManagerFactory;
	}
	

	/*@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveAdminDetails(AdminAccount adminAccount, Subscription parentSubscription, Subscription studentSubscription, Subscription teacherSubscription){
		em.persist(adminAccount);
		em.persist(studentSubscription);
		em.persist(parentSubscription);
		em.persist(teacherSubscription);
	}*/
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveAdminDetails(AdminAccount adminAccount){
		em.persist(adminAccount);		
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveSuperAdmin(AdminAccount adminAccount){
		em.merge(adminAccount);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Permission savePermission(Permission permission){
		return em.merge(permission);
		//return permission;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public AdminAccount getAdminAccountbyUserName(String username){
		AdminAccount adminAccount =  em.createQuery("Select a from AdminAccount a Where a.username=:username",AdminAccount.class)
		.setParameter("username", username)
		.getSingleResult();
		 return adminAccount;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public RoleType getRoleById(long userId){
		RoleType roleType = null;
		try{
		 roleType =  em.createQuery("Select u.role from UserProfile u Where u.id=:id",RoleType.class)
		.setParameter("id", userId)
		.getSingleResult();
		}catch(Exception e){
			e.printStackTrace();
			roleType.setStatus("User");
			return roleType;
		}
		return roleType;
		
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean isAdminExist(String userName){
		List<AdminAccount> adminAccount = em.createQuery("Select a From AdminAccount a where a.username=:username",AdminAccount.class)
		.setParameter("username", userName)
		.getResultList();
		return (adminAccount != null && adminAccount.size() > 0 ? true : false);
	}
	

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public UserProfile getUserDetails(String name){
		UserProfile accountDetails = em.createQuery("Select u From UserProfile u Where u.firstName=:firstName",UserProfile.class)
		.setParameter("firstName", name)
		.getSingleResult();
		return accountDetails;
	}
	
	public void saveSubOffers(SubscriptionOffer subOffer){
		em.merge(subOffer);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveNewFeatures(Feature feature){
		em.merge(feature);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<SubscriptionOffer> getSubOfferList(){
		List<SubscriptionOffer> subOfferList = em.createQuery("Select s From SubscriptionOffer s ",SubscriptionOffer.class)				
		.getResultList();
		return subOfferList;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public SubscriptionOffer getsubOfferDetailById(long id){
		SubscriptionOffer sub = em.createQuery("Select s From SubscriptionOffer s Where s.id=:id", SubscriptionOffer.class)
				.setParameter("id", id)
				.getSingleResult();
				return sub;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteSubOfferById(Long id) {
		SubscriptionOffer suboffer =em.find(SubscriptionOffer.class, id);		
		em.remove(suboffer);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<Feature> getAllFeaturesList(){
		List<Feature> featureList = em.createQuery("Select f from Feature f",Feature.class)
		.getResultList();
		return featureList;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Feature getFeatureById(Long id){
		Feature feature = em.createQuery("Select f From Feature f Where f.id=:id", Feature.class)
				.setParameter("id", id)
				.getSingleResult();
		return feature;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteFeatureById(long id){
		Feature feature = em.find(Feature.class, id);
		em.remove(feature);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<Category> getAllCategories (){
		List<Category> allCategories = em.createQuery("Select c from Category c",Category.class)
		.getResultList();
		return allCategories;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<Series> getAllSeries(){
		List<Series> allSeries = em.createQuery("Select s From Series s",Series.class)
		.getResultList();
		return allSeries;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<Content> getAllContents(){
		List<Content> allContents = em.createQuery("Select c from Content c",Content.class)
		.getResultList();
		return allContents;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<AdminAccount> getSuperAdminList(){
		List<AdminAccount> superAdminList = em.createQuery("Select u from AdminAccount u Where u.userProfile.roleType =2",AdminAccount.class)
		.getResultList();
		return superAdminList;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public AdminAccount getSuperAdminById(long id){
		AdminAccount superAdmin = em.createQuery("Select a from AdminAccount a Where a.id =:id",AdminAccount.class)
				.setParameter("id", id)
				.getSingleResult();
		return superAdmin;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteSuperAdminById(long id){
		AdminAccount adminAccount = em.find(AdminAccount.class, id);
		em.remove(adminAccount);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Permission getPermissionById(long id){
		Permission permission = em.createQuery("Select p from Permission p where p.id =:id", Permission.class)
				.setParameter("id", id)
				.getSingleResult();
		return permission;
	}
	
	@Transactional(readOnly = false, propagation= Propagation.REQUIRED)
	public UserProfile getUserProfile(long id){
		UserProfile superAdminProfile = em.createQuery("Select u from UserProfile u where u.id =:id", UserProfile.class)
				.setParameter("id", id)
				.getSingleResult();
		return superAdminProfile;
	}
	
	@Transactional(readOnly = false, propagation= Propagation.REQUIRED)
	public List<Organization> getOrganizationList(){
		List<Organization> organizationList = em.createQuery("Select o from Organization o", Organization.class)
				.getResultList();
		return organizationList;
	}
	
	@Transactional(readOnly = false, propagation= Propagation.REQUIRED)
	public Organization getOrganizationById(long id){
		Organization organization = em.createQuery("Select o from Organization o where o.id=:id", Organization.class)
				.setParameter("id", id)
				.getSingleResult();
		return organization;
	}
	
	@Transactional(readOnly = false, propagation= Propagation.REQUIRED)
	public SubscriptionPlan getSubscriptionPlanByName(String planName){
		SubscriptionPlan plan = em.createQuery("Select s from SubscriptionPlan s where s.planName=:planName", SubscriptionPlan.class)
				.setParameter("planName", planName)
				.getSingleResult();
		return plan;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public UserAccount createUserAccount(UserAccount userAccount){
		em.merge(userAccount);
		return userAccount;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public UserAccount getUserAcount(long id){
		UserAccount plan = em.createQuery("Select ua from UserAccount ua where ua.userProfile.id=:id", UserAccount.class)
				.setParameter("id", id)
				.getSingleResult();
		return plan;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateContactReq(ContactRequest contactRequest){
		em.merge(contactRequest);
	}
	
	@Transactional(readOnly = false, propagation= Propagation.REQUIRED)
	public List<ContactRequest> getConatctReqList(){
		List<ContactRequest> contactReqList = em.createQuery("Select cr from ContactRequest cr", ContactRequest.class)
				.getResultList();
		return contactReqList;
	}
	
	@Transactional(readOnly = false, propagation= Propagation.REQUIRED)
	public ContactRequest getConatctReqById(long id){
		ContactRequest contactReq = em.createQuery("Select cr from ContactRequest cr where cr.id=:id", ContactRequest.class)
				.setParameter("id", id)
				.getSingleResult();
		return contactReq;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteContactById(long id){
		ContactRequest contactRequest = em.find(ContactRequest.class, id);
		em.remove(contactRequest);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateOrganization(Organization organization){
		em.merge(organization);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<Organization> getOrganizationByplan(SubscriptionPlan subscriptionPlan){
		List<Organization> organizations = em.createQuery("Select o from Organization o where o.subscription.subscriptionPlan.id=:id",Organization.class)
		.setParameter("id", subscriptionPlan.getId())
		.getResultList();
		return organizations;
	}
	
}