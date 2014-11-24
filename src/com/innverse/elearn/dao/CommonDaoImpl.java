package com.innverse.elearn.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.innverse.elearn.eenum.MemberRole;
import com.innverse.elearn.eenum.ProficiencyType;
import com.innverse.elearn.eenum.RegistrationType;
import com.innverse.elearn.eenum.SubscriptionMode;
import com.innverse.elearn.eenum.SubscriptionType;
import com.innverse.elearn.eenum.ViewType;
import com.innverse.elearn.json.UserForm;
import com.innverse.elearn.model.Address;
import com.innverse.elearn.model.Blog;
import com.innverse.elearn.model.Category;
import com.innverse.elearn.model.ContactRequest;
import com.innverse.elearn.model.Content;
import com.innverse.elearn.model.Course;
import com.innverse.elearn.model.Deck;
import com.innverse.elearn.model.DeckCourse;
import com.innverse.elearn.model.Feature;
import com.innverse.elearn.model.Friend;
import com.innverse.elearn.model.Group;
import com.innverse.elearn.model.Invitation;
import com.innverse.elearn.model.Level;
import com.innverse.elearn.model.MediaFile;
import com.innverse.elearn.model.Member;
import com.innverse.elearn.model.MemberFALPermission;
import com.innverse.elearn.model.MultipleChoiceAnswer;
import com.innverse.elearn.model.Organization;
import com.innverse.elearn.model.Question;
import com.innverse.elearn.model.Role;
import com.innverse.elearn.model.ScoreBoard;
import com.innverse.elearn.model.ScoreBoardCourse;
import com.innverse.elearn.model.Series;
import com.innverse.elearn.model.Subscription;
import com.innverse.elearn.model.SubscriptionPlan;
import com.innverse.elearn.model.TextQuestion;
import com.innverse.elearn.model.UploadedScorm;
import com.innverse.elearn.model.UserAccount;
import com.innverse.elearn.model.UserPayment;
import com.innverse.elearn.model.UserProfile;
import com.innverse.elearn.model.YesNoAnswer;
import com.innverse.elearn.pojo.CategoryData;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;


@repository
public class CommonDaoImpl {

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
	

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean saveUserDetails(UserProfile userProfile,UserAccount userAccount){
		em.persist(userAccount);
		em.persist(userProfile);
		return true;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public UserAccount createUserAccount(UserAccount userAccount){
		userAccount = em.merge(userAccount);
		return userAccount;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public UserAccount getUserAccountById(long userId){
		UserAccount userAccount = em.find(UserAccount.class, userId);
		return userAccount;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean isUserExist(String email){
		 List userEmails = em.createQuery("Select u From UserProfile u Where u.email=:email",UserProfile.class)
		.setParameter("email", email)
		.getResultList();
		return (userEmails != null && userEmails.size() > 0 ? true : false);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveLoginDetails(UserAccount userAccount){
		em.merge(userAccount);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public UserAccount getUserDetails(UserForm userAccount){
		UserAccount accountDetails = em.createQuery("Select u From UserAccount u Where u.username=:username",UserAccount.class)
		.setParameter("username", userAccount.getUsername())
		.getSingleResult();
		return accountDetails;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public UserAccount validateUser(UserForm userForm){
		UserAccount accountDetails = em.createQuery("Select u From UserAccount u Where u.username=:username and password=:password",UserAccount.class)
		.setParameter("username", userForm.getUsername())
		.setParameter("password", userForm.getPassword())
		.getSingleResult();
		return accountDetails;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveContentDetails(Content content){
		em.merge(content);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveQuestions(TextQuestion textQuestion){
		em.persist(textQuestion);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveAnswers(YesNoAnswer ynAnswer){
	em.persist(ynAnswer);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveAnswersTypes(Content content){
	em.persist(content);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void mcqList(MultipleChoiceAnswer mcq){
		em.persist(mcq);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<Content> getQuestions(long profile,long categoryId){
		List<Content> questions = em.createQuery("Select c From Content c where c.subCategoryId=:categoryId ",Content.class)
		.setParameter("categoryId", categoryId)
		.getResultList();
		return questions;
	}
	
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<YesNoAnswer> getYNAnswers(){
		List<YesNoAnswer> answers = em.createQuery("Select y from YesNoAnswer y",YesNoAnswer.class)
		.getResultList();
		return answers;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Member getUserDetails(long organization_id){
		Member memberDetails = em.createQuery("Select m From Member m Where m.organization_id=:organization_id",Member.class)
		.setParameter("organization_id", organization_id)		
		.getSingleResult();
		return memberDetails;
	}

	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<MultipleChoiceAnswer> getMCQAnswers(){
		List<MultipleChoiceAnswer> answers = em.createQuery("Select m from MultipleChoiceAnswer m",MultipleChoiceAnswer.class)
		.getResultList();
		return answers;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<Content> getAnswerTypes(){
		List<Content> answerTypes = em.createQuery("Select t from Content t",Content.class)
		.getResultList();
		return answerTypes;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<Content> getAllContents(){
		List<Content> allContent = em.createQuery("Select c From Content c",Content.class)
		.getResultList();
		return allContent;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<Member> getAllMembers(Long organizationId){
		 List<Member>  members = em.createQuery("Select u From Member u where u.organization.id=:orgId ",Member.class)
		 .setParameter("orgId", organizationId)
		 .getResultList();
		return members;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void createMember(Member member){
		em.merge(member);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Invitation getInviteeDeatil(String friendEmailid){
		Invitation inviteeDetails = em.createQuery("Select u From Invitation  u Where u.friendEmailId=:friendEmailId Order By id Desc",Invitation.class)
		.setParameter("friendEmailId", friendEmailid)		
		.getResultList().get(0);
		return inviteeDetails;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Invitation setInvite(Invitation invitations){
		return em.merge(invitations);
	}	

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean getSubscriptionPlan(SubscriptionPlan plan){
		 em.persist(plan);
		return true;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public UserProfile getUserDetailsById(long Id){
		UserProfile accountDetails = em.createQuery("Select u From UserProfile u Where u.id=:id",UserProfile.class)
		.setParameter("id", Id)		
		.getSingleResult();		
		return accountDetails;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Member getMemberById(long Id){
		Member accountDetails = em.createQuery("Select u From Member u Where u.id=:id",Member.class)
		.setParameter("id", Id)		
		.getSingleResult();		
		System.out.println(accountDetails);
		return accountDetails;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean saveMemberDetails(Member member){		
		em.merge(member);
		return true;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<CategoryData> getAllCategories(){
		TypedQuery<CategoryData> query = em.createQuery("select new com.innverse.elearn.pojo.CategoryData(c1.id,c2.id,c1.description,c2.description) from Category c1, Category c2 Where c1.id = c2.parentCategory Order By c1.description,c2.description",CategoryData.class);
		List<CategoryData> list = query.getResultList();
		return list;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<Category> getAllSubCategories(){
		List<Category> query = em.createQuery("select c1 from Category c1  Where  c1.parentCategory!=null Order By c1.id ",Category.class)
		.getResultList();
		return query;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<Category> getAllSubCategories(long subId){
		List<Category> query = em.createQuery("select c1 from Category c1  Where c1.parentCategory.id=:subid Order By c1.id ",Category.class)
		.setParameter("subid", subId)
		.getResultList();
		return query;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<Category> getAllMainCategories(){
		List<Category> query = em.createQuery("select c1 from Category c1  Where c1.parentCategory=null Order By c1.id",Category.class)
		.getResultList();
		return query;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveUserProfile(UserProfile userProfile){
		em.merge(userProfile);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveGroup(Group groups){
		em.merge(groups);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<Category> getSubCategories(long parentId){
		List<Category> item = em.createQuery("Select c From Category c where c.parentCategory.id=:parent_id",Category.class)
		.setParameter("parent_id", parentId)
		.getResultList();
		return item;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<Category> getTestCenter(){
		List<Category> query = em.createQuery("select c from Category c Where c.catagoryType=0",Category.class)
		.getResultList();
		return query;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveNewCategory(Category categoryName){
		em.persist(categoryName);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Category saveOrUpdateNewCategory(Category category){
		return em.merge(category);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Series saveSeries(Series series){
		 series = em.merge(series);
		 return series;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public long updateSeries(Series series){
		series = em.merge(series);
		return series.getId();
		 
	}
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void createLevel(Level level){
		 em.merge(level);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public long getTokenId(String token){
		Query id = em.createQuery("Select i.organizationId From Invitation i Where i.token=:token")
		.setParameter("token", token);
		long organizationId = (Long) id.getSingleResult();
		return organizationId;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Content getContent(long questionId){
		 Content  content = em.createQuery("Select i From Content i Where i.id=:id",Content.class)
		.setParameter("id", questionId)		
		.getSingleResult();		
		return content;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Series getSeriesById(long seriesId){
		Series query = em.createQuery("select s from Series s WHERE s.id=:sid",Series.class)
		.setParameter("sid", seriesId)
		.getSingleResult();
		return query;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public  Series  getSeriesById(long seriesId, long orgId){
		Series query = em.createQuery("select s from Series s WHERE s.id=:id AND s.createBy.id=:orgId",Series.class)
		.setParameter("id", seriesId)
		.setParameter("orgId", orgId)
		.getSingleResult();
		return query;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public  Series  getSeriesBySeriesName(String seriesname){
		Series query = em.createQuery("select s from Series s WHERE s.seriesname=:sName",Series.class)
		.setParameter("sName", seriesname)
		.getSingleResult();
		return query;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<Group> getAllGroups(long organizationId){
		List<Group> groupList = em.createQuery("Select g From Group g Where g.createby.id=:userId",Group.class)
				.setParameter("userId", organizationId)
				.getResultList();
		return groupList;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Group groupDetailById(long Id){
		Group groupdetail = em.createQuery("Select g From Group g Where g.id=:id",Group.class)
		.setParameter("id", Id)		
		.getSingleResult();
		return groupdetail;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<Feature> getAllFeature(){
		List<Feature> query = em.createQuery("select c1 from Feature c1 Order By c1.id",Feature.class)
		.getResultList();
		return query;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<Feature> getAllFeaturebyViewType(ViewType viewType){
		List<Feature> query = em.createQuery("select c1 from Feature c1 Where c1.viewType=:viewType Order By c1.id",Feature.class)
		.setParameter("viewType", viewType)
		.getResultList();
		return query;
	}
	
	public void saveSubcriptionPlan(SubscriptionPlan subscriptionPlan){
		em.merge(subscriptionPlan);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Feature getFeatureById(long Id){
		Feature details = em.createQuery("Select u From Feature u Where u.id=:id",Feature.class)
		.setParameter("id", Id)		
		.getSingleResult();		
		return details;
	}
	
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteGroup(long Id){		
		Group group = em.find(Group.class, Id);
		group.removeAllMembers();
		em.remove(group);
 	}
	
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<SubscriptionPlan> getAllSubscriptionPlans(long id){
		List<SubscriptionPlan> items = em.createQuery("Select s from SubscriptionPlan s where s.id=:planForMemberType",SubscriptionPlan.class)
		.setParameter("planForMemberType", id)
		.getResultList();
		return items;
	}
	

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<SubscriptionPlan> getTotalSubscriptionPlans(){
		List<SubscriptionPlan> items = em.createQuery("Select s from SubscriptionPlan s",SubscriptionPlan.class)
		.getResultList();
		return items;
	}

	@Transactional(readOnly = false, propagation=Propagation.REQUIRED)
	public boolean isUsernameExist(String userName){
		List<UserProfile> details = em.createQuery("Select u from UserProfile u where u.username=:username",UserProfile.class)
		.setParameter("username", userName)
		.getResultList();
		return (details != null && details.size() > 0 ? true : false);
	}
	

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<Series> getAllSeriesByOrgId(long orgId){
		List<Series> items = em.createQuery("Select s from Series s where s.createBy.id=:id",Series.class)
		.setParameter("id", orgId)
		.getResultList();
		return items;
	}
	

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public UserProfile getUserProfileByEmailAdd(String email){
		UserProfile userDetails = em.createQuery("Select u from UserProfile u where u.email=:email",UserProfile.class)
		.setParameter("email", email)
		.getSingleResult();
		return userDetails;
	}


	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveUserFormDetails(UserAccount userAccount){
		em.merge(userAccount);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public UserAccount getUserDetailsByEmailAdd(String userName){
		UserAccount userAccount = em.createQuery("Select u From UserAccount u where u.username=:username",UserAccount.class)
		.setParameter("username", userName)
		.getSingleResult();
		return userAccount;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Address getcurrentAddressbyId(long currentAddressid){
		Address address = em.createQuery("Select a from Address a Where a.id=:id",Address.class)
		.setParameter("id", currentAddressid)
		.getSingleResult();
		return address;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Address getpermanentAddressbyId(long permanentAddressid){
		Address address = em.createQuery("Select a from Address a Where a.id=:id",Address.class)
		.setParameter("id", permanentAddressid)
		.getSingleResult();
		return address;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public UserProfile getUserDetailsByName(String firstName){
		UserProfile accountDetails = em.createQuery("Select u From UserProfile u Where u.firstName=:firstName",UserProfile.class)
		.setParameter("firstName", firstName)		
		.getSingleResult();
		return accountDetails;
	}
	
	
	@Transactional(readOnly = false, propagation=Propagation.REQUIRED)
	public SubscriptionPlan getSubscriptionPlanbyId(Long id){
		SubscriptionPlan subcriptionPlan = em.createQuery("Select s from SubscriptionPlan s Where id=:id",SubscriptionPlan.class)
		.setParameter("id", id)
		.getSingleResult();
		return subcriptionPlan;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveUserOrganizationDetails(Member ownerMember){
		em.merge(ownerMember);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Category getCategoryById(long categoryId){
		return em.find(Category.class,categoryId);
	}	
	
	@Transactional(readOnly = false, propagation=Propagation.REQUIRED)
	public boolean isUserEmailExist(String email){
		List<UserProfile> details = em.createQuery("Select u from UserProfile u where u.email=:email",UserProfile.class)
		.setParameter("email", email)
		.getResultList();
		return (details != null && details.size() > 0 ? true : false);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public UserAccount getUserDetailsByEmail(String email){
		UserAccount accountDetails = em.createQuery("Select u From UserAccount u Where u.username=:username",UserAccount.class)
		.setParameter("username", email)
		.getSingleResult();
		return accountDetails;
	}	
	
	@Transactional(readOnly = false, propagation=Propagation.REQUIRED)
	public List<SubscriptionPlan> getAdminSideSubPlanList(){
		List<SubscriptionPlan> subPlanList = em.createQuery("Select sb from SubscriptionPlan sb",SubscriptionPlan.class)		
		.getResultList();
		return subPlanList;
	}
	
	@Transactional(readOnly = false, propagation=Propagation.REQUIRED)
	public List<SubscriptionPlan> getSubPlanByAuthorId(Long id){
		List<SubscriptionPlan> subPlan = em.createQuery("Select sb From SubscriptionPlan sb Where sb.createdBy_id=:id", SubscriptionPlan.class)
				.setParameter("id", id)
				.getResultList();
		return subPlan;
	}
	
	@Transactional(readOnly = false, propagation=Propagation.REQUIRED)
	public SubscriptionPlan getSubscriptionPlanById(Long id){
		SubscriptionPlan subPlan = em.createQuery("Select sb From SubscriptionPlan sb Where sb.id=:id", SubscriptionPlan.class)
				.setParameter("id", id)
				.getSingleResult();
		return subPlan;
	}
		
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteSubPlan(long Id){		
		SubscriptionPlan plan = em.find(SubscriptionPlan.class, Id);		
		em.remove(plan);
	}
	

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Series getSeriesDetailById(long Id){	
		Series series = em.createQuery("Select s from Series s Where s.id=:id",Series.class)
				.setParameter("id", Id)
				.getSingleResult();
				return series;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<Series> editSeriesDetail(long Id){	
		List<Series> series = em.createQuery("Select s from Series s Where id=:id",Series.class)
				.setParameter("id", Id)
				.getResultList();
				return series;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteSeries(long Id){
		Series series = em.find(Series.class, Id);
		em.remove(series);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateSeriesDetail(Series series){
		em.merge(series);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Subscription getDefaultSubscriptionPlan(SubscriptionType subscriptionType, RegistrationType registrationType){
		 Subscription plan = em.createQuery("Select s from Subscription s where s.subscriptionPlan.subscriptionType=:subscriptionType and s.subscriptionPlan.planForMemberType=:planForMemberType",Subscription.class)
		.setParameter("subscriptionType", subscriptionType)
		.setParameter("planForMemberType", registrationType)
		.getSingleResult();
		return plan;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteMemberById(long Id){		
		Member member = em.find(Member.class, Id);		
		em.remove(member);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<SubscriptionPlan> getAllSubscriptionPlans(){
		List<SubscriptionPlan> allSubscription = em.createQuery("Select s from SubscriptionPlan s",SubscriptionPlan.class)
		.getResultList();
		return allSubscription;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public SubscriptionPlan getDefaultSubscriptionPlan(SubscriptionMode sMode,RegistrationType rType){
		List<SubscriptionPlan> allSubscription = em.createQuery("Select s from SubscriptionPlan s Where s.subscriptionMode=:sMode and s.registrationType=:rType and s.checkDefault=true Order By s.id DESC",SubscriptionPlan.class)
		.setParameter("rType", rType)
		.setParameter("sMode", sMode)
		.getResultList();
		if(allSubscription != null && allSubscription.size() > 0){
			return allSubscription.get(0);
		}
		return null;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Subscription getSubscriptionById(long id){
		Subscription subscription = em.createQuery("Select s from Subscription s where s.id=:id",Subscription.class)
		.setParameter("id", id)
		.getSingleResult();
		return subscription;
	}
	
	public Organization getUserOrganizationById(long id){
		Organization organizationDetails = em.createQuery("Select o from Organization o Where o.userProfile.id=:id",Organization.class)
		.setParameter("id", id)
		.getSingleResult();
		return organizationDetails;
	}
	
	public Organization getOrganizationDetailsById(long id){
		Organization organizationDetails = em.createQuery("Select o from Organization o Where o.id=:id",Organization.class)
		.setParameter("id", id)
		.getSingleResult();
		return organizationDetails;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public  List<Series>  getSeriesList(long orgId){
		List<Series> seriesLlist = em.createQuery("select s from Series s WHERE s.createBy.id=:organization",Series.class)
		.setParameter("organization", orgId)
		.getResultList();
		return seriesLlist;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public  Level  getLevelById(long levelId){
		Level level = em.createQuery("select l from Level l WHERE l.id=:id Order By l.id asc", Level.class)
		.setParameter("id", levelId)
		.getSingleResult();
		return level;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public boolean isUserAlreadyMember(String email){
		List<Member> memberList =  em.createQuery("Select m from Member m where m.account.email=:email",Member.class)
		.setParameter("email", email)
		.getResultList();
		return (memberList != null && memberList.size() > 0 ? true : false);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Invitation getInvitedMemberDetails(String token){
		Invitation invitationDetails = em.createQuery("Select i from Invitation i where i.token=:token",Invitation.class)
		.setParameter("token", token)
		.getSingleResult();
		return invitationDetails;
	}
	
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public UserProfile getMemberProfilebyEmail(String email){
		UserProfile userProfile = em.createQuery("Select u from UserProfile u where u.email=:email",UserProfile.class)
		.setParameter("email", email)
		.getSingleResult();
		return userProfile;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Organization getOrganizationById(long organizationId){
		Organization organization = em.createQuery("Select o from Organization o Where o.id=:id",Organization.class)
		.setParameter("id", organizationId)
		.getSingleResult();
		return organization;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Organization getOrganizationByName(String organizationName){
		Organization organization = em.createQuery("Select o from Organization o Where o.name=:name",Organization.class)
		.setParameter("name", organizationName)
		.getSingleResult();
		return organization;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<Member> getMemberListbyProfileId(long profileId){
		List<Member> memberList = em.createQuery("Select m from Member m where m.account.id=:id",Member.class)
		.setParameter("id", profileId)
		.getResultList();
		return memberList;
	}
		
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public  Question  getQuestionById(long queId){
		Question question = em.createQuery("select q from Question q WHERE q.id=:id", Question.class)
		.setParameter("id", queId)
		.getSingleResult();
		return question;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<SubscriptionPlan> getFreeDefaultPlan(SubscriptionType subsType){
	List<SubscriptionPlan> planList = em.createQuery("Select s from SubscriptionPlan s where s.checkDefault=:checkDefault and s.subscriptionType=:subscriptionType",SubscriptionPlan.class)
		.setParameter("checkDefault", true)
		.setParameter("subscriptionType", subsType)
		.getResultList();
	return planList;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveUserSubscribe(UserProfile user){
		em.merge(user);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Category saveCategory(Category category){
		category = em.merge(category);
		return category;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<SubscriptionPlan> getSubscriptionPlanByRegistration(RegistrationType registrationType){
		List<SubscriptionPlan> avaliablePlans =  em.createQuery("Select s from SubscriptionPlan s where s.registrationType=:registrationType",SubscriptionPlan.class)
		.setParameter("registrationType", registrationType)
		.getResultList();
		return avaliablePlans;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	 public  List<Level>  getLeveListlById(long levelId){
	  List<Level> level = em.createQuery("select l from Level l WHERE l.id=:id Order By l.id asc", Level.class)
	  .setParameter("id", levelId)
	  .getResultList();
	  return level;
	 }
	
	@Transactional(readOnly = false, propagation=Propagation.REQUIRED)
	 public List<Organization> getOrganizationList(){
	  List<Organization> organizations = em.createQuery("Select sb from Organization sb",Organization.class)  
	  .getResultList();
	  return organizations;
	 }
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Member getOrganizationMember(long organizationId, long profileId, MemberRole memberRole){
		Member member = em.createQuery("Select m from Member m Where m.organization.id=:organizationId AND m.account.id=:id and m.isDefaultMemberOfOrganization=:isDefaultMemberOfOrganization and  memberRole=:memberRole",Member.class)
		.setParameter("id", profileId)
		.setParameter("isDefaultMemberOfOrganization", true)
		.setParameter("memberRole", memberRole)
		.setParameter("organizationId", organizationId)
		.getSingleResult();
		return member;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<MemberFALPermission> getMemberFALPermissionsbyRole(MemberRole memberRole){
		List<MemberFALPermission> MFALPersmission = em.createQuery("Select mfal from MemberFALPermission mfal where mfal.memberRole=:memberRole",MemberFALPermission.class)
		.setParameter("memberRole", memberRole)
		.getResultList();
		return MFALPersmission;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void createMemberAccount(Member member){
		em.merge(member);
	}
	
	@Transactional(readOnly = false, propagation=Propagation.REQUIRED)
	 public void insertQuizData(ScoreBoard sb){
	  em.persist(sb);
	 }
	
	@Transactional(readOnly = false, propagation=Propagation.REQUIRED)
	 public ScoreBoard getScoreBoardBylevelid(long levelId,Long memberId, Long orgId){
		ScoreBoard scoreBoards = null;
		try{
			scoreBoards = em.createQuery("Select sb from ScoreBoard sb Where sb.levelId=:levelId AND sb.memberId=:memberId AND sb.organizationId=:orgId ",ScoreBoard.class)
					.setParameter("levelId", levelId)
					.setParameter("memberId", memberId)
					.setParameter("orgId", orgId)
					.getSingleResult();
	    } catch(NoResultException e) {
	        return null;
	    }
		return scoreBoards;
	 }
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Content getContentsById(long id){
		Content contentById = em.createQuery("Select c From Content c where c.question.id=:id ",Content.class)
		.setParameter("id", id)
		.getSingleResult();
		return contentById;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Organization getAllScore(long organizationId){
		Organization organization = em.createQuery("Select o from Organization o Where o.id=:id",Organization.class)
		.setParameter("id", organizationId)
		.getSingleResult();
		return organization;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public ScoreBoard getScoreBoard(long levelId, Long memberId, Long orgId){
		List<ScoreBoard> scoreBoards = em.createQuery("Select sb from ScoreBoard sb Where sb.levelId=:levelId AND sb.memberId=:memberId AND sb.organizationId=:orgId Order By sb.id Desc",ScoreBoard.class)
		.setParameter("levelId", levelId)
		.setParameter("memberId", memberId)
		.setParameter("orgId", orgId)
		.getResultList();
		if(scoreBoards != null && scoreBoards.size() > 0){
			return scoreBoards.get(0);
		}
		ScoreBoard scoreBoard = new ScoreBoard();
		scoreBoard.setScore(0);
		scoreBoard.setTotalTryOrHits(0);
		return new ScoreBoard();
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public SubscriptionPlan getDefaultFreeSubscriptionPlan(RegistrationType registrationType, double amount){
		SubscriptionPlan subscriptionPlan = em.createQuery("Select s from SubscriptionPlan s where s.amount=:amount and s.registrationType=:registrationType",SubscriptionPlan.class)
		.setParameter("amount", amount)
		.setParameter("registrationType", registrationType)
		.getSingleResult();
		return subscriptionPlan;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<Deck> getAllDecksByOrgId(long organizationId){
		List<Deck> deckList = em.createQuery("Select d from Deck d where d.organization.id=:id",Deck.class)
		.setParameter("id", organizationId)
		.getResultList();
		return deckList;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveNewDeck(Group group, Series series){
		em.persist(group);
		em.persist(series);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveNewDeck(Deck deck){
		em.merge(deck);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Group getGroupById(long groupId){
		Group group = em.createQuery("Select g from Group g where g.id=:id",Group.class)
		.setParameter("id", groupId)
		.getSingleResult();
		return group;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<Deck> getDeckListbyGroupId(long groupId){
		List<Deck> groupList = em.createQuery("Select d from Deck d Where d.group.id=:id",Deck.class)
		.setParameter("id", groupId)
		.getResultList();
		return groupList;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<DeckCourse> getDeckCoursesbyGroupId(long groupId){
		List<DeckCourse> courseList = em.createQuery("Select dc from DeckCourse dc where dc.group.id=:id",DeckCourse.class)
		.setParameter("id", groupId)
		.getResultList();
		return courseList;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveContactReq(ContactRequest contactRequest){
		em.merge(contactRequest);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public SubscriptionPlan getSubscriptionPlanByName(String planName){
		SubscriptionPlan subscriptionPlan = em.createQuery("Select s from SubscriptionPlan s where s.planName=:planName",SubscriptionPlan.class)
		.setParameter("planName", planName)
		.getSingleResult();
		return subscriptionPlan;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveBook(MediaFile mediaFile){
		em.persist(mediaFile);
	}
	
	@Transactional(readOnly = false, propagation=Propagation.REQUIRED)
	public List<UserPayment> getPaymentDetailsByUsername(String username){
		List<UserPayment> userPaymentDetails = em.createQuery("Select u from UserPayment u where u.username=:username",UserPayment.class)
		.setParameter("username", username)
		.getResultList();
		return userPaymentDetails;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<Subscription> getAllSubscriptionsByOrgId(long organizationId){
		List<Subscription> subscriptionHistory = em.createQuery("Select s from Subscription s Where s.organizationId=:organizationId",Subscription.class)
		.setParameter("organizationId", organizationId)
		.getResultList();
		return subscriptionHistory;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<MediaFile> getAllMediaDatails(){
		List<MediaFile> file = em.createQuery("Select m from MediaFile m order By m.id desc",MediaFile.class)
		.getResultList();
		return file;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
		public void deleteMediaFileById(long id){		
			MediaFile file = em.find(MediaFile.class, id);		
			em.remove(file);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public MediaFile editMediaFileById(long id){		
		MediaFile file =em.createQuery("Select m from MediaFile m where m.id=:id",MediaFile.class)	
				.setParameter("id", id)
				.getSingleResult();
				return file;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveEditMediaFile(MediaFile file){		
		em.merge(file);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveUploadedScorm(UploadedScorm uploadedScorm){
		em.merge(uploadedScorm);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveCourse(Course course){
		em.merge(course);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Member getOrganizationMember(long organizationId, long profileId){
		Member member = em.createQuery("Select m from Member m Where m.organization.id=:organizationId AND m.account.id=:id and m.isDefaultMemberOfOrganization=:isDefaultMemberOfOrganization",Member.class)
		.setParameter("id", profileId)
		.setParameter("isDefaultMemberOfOrganization", true)
		.setParameter("organizationId", organizationId)
		.getSingleResult();
		return member;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Member getOrganizationMemberById(long organizationId, long memberId){
		Member member = em.createQuery("Select m from Member m Where m.organization.id=:organizationId AND m.id=:id",Member.class)
		.setParameter("id", memberId)
		.setParameter("organizationId", organizationId)
		.getSingleResult();
		return member;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveInvitation(Friend friend){
		em.persist(friend);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveUpdateInvitation(Friend friend){
		em.merge(friend);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<Course> getAllCourses(long orgId){		
		List<Course> courses =em.createQuery("Select c from Course c where c.organization.id=:orgId",Course.class)	
				.setParameter("orgId", orgId)
				.getResultList();
		return courses;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Course getCourseById(long courseId){		
		Course course =em.createQuery("Select c from Course c where c.id=:courseId",Course.class)	
				.setParameter("courseId", courseId)
				.getSingleResult();
		return course;
	}
	
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<Friend> getallUnapprovedFriends(Member member){
		List<Friend> friend = em.createQuery("Select m from Friend m where m.approved=false and m.friend.id=:friendMemberId ",Friend.class)
				.setParameter("friendMemberId", member.getMemberId())
				.getResultList();
		return friend;
	}
	
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateForApproval(Friend approval){
					  em.merge(approval);
		
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Friend getApprovedFriendsById(long id){
		Friend friend = em.createQuery("Select m from Friend m where m.id=:id ",Friend.class)
				.setParameter("id", id)
				.getSingleResult();
		return friend;
		
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<Friend> getAllApprovedFriends(Long memberId){
		List<Friend> friend = new ArrayList<Friend>();
		try{
			friend = em.createQuery("Select m from Friend m where m.approved=true AND m.member.id=:memberId ",Friend.class)
			.setParameter("memberId", memberId)
			.getResultList();
		}catch(Exception e){
			e.printStackTrace();
		}
		return friend;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateFriend(Friend friend){
		em.merge(friend);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Friend getFriendById(long id){
		Friend friend = em.createQuery("Select m from Friend m where m.id=:id ",Friend.class)
				.setParameter("id", id)
				.getSingleResult();
		return friend;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteFollowFriend(Long friendId){
		Friend friend = em.find(Friend.class, friendId);
		em.remove(friend);
	}
	

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateFollowFriendStatus(Friend friend){
		em.merge(friend);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<ScoreBoard> getScoreBoardRecord(long memberId){
		List<ScoreBoard> scoreBoards = em.createQuery("Select sb from ScoreBoard sb where sb.memberId=:memberId Order By sb.id Desc",ScoreBoard.class)
				.setParameter("memberId", memberId)
		.getResultList();
		return scoreBoards;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<ScoreBoard> getScoreBoardRecordByOrganizationId(long orgId){
		List<ScoreBoard> scoreBoards = em.createQuery("Select sb from ScoreBoard sb where sb.organizationId=:orgId Order By sb.id Desc",ScoreBoard.class)
				.setParameter("orgId", orgId)
		.getResultList();
		return scoreBoards;
	}
	
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<ScoreBoardCourse> getCourseResultByOrganizationId(long orgId){
		List<ScoreBoardCourse> scoreBoardCourse = em.createQuery("Select sbc from ScoreBoardCourse sbc where sbc.organizationId=:orgId Order By sbc.id Desc",ScoreBoardCourse.class)
				.setParameter("orgId", orgId)
		.getResultList();
		return scoreBoardCourse;
	}
	
	@Transactional(readOnly = false, propagation=Propagation.REQUIRED)
	 public void updateQuizData(ScoreBoard scoreBoard){
	  em.merge(scoreBoard);
	 }
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<Content> getAllQuestionsWithProficiency(long orgId, long subCatId, String value){
		
		ProficiencyType pro = null;
		
		if(value.equalsIgnoreCase("0")){
			pro = ProficiencyType.BEGINNER;
		}
		if(value.equalsIgnoreCase("1")){
			pro = ProficiencyType.INTERMIDIATE;
		}
		if(value.equalsIgnoreCase("2")){
			pro = ProficiencyType.ADVANCED;
		}
		if(value.equalsIgnoreCase("3")){
			pro = ProficiencyType.EXPERT;
		}
		
		System.out.println("DAODAODAO "+orgId +"--" +subCatId+"--" +pro) ;
		List<Content> questions = em.createQuery("Select c From Content c where c.createBy.id=:orgId and c.subCategoryId=:subCatId and c.minProficiency<=:value and c.maxProficiency>=:value",Content.class)
				.setParameter("orgId", orgId)
				.setParameter("subCatId", subCatId)
				.setParameter("value", pro)
				.getResultList();
		return questions;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<Content> getAllQuestionsForAutoGenerate(long orgId, long catagoryId, String value){
		
		ProficiencyType pro = null;
		if(value.equalsIgnoreCase("0")){
			pro = ProficiencyType.BEGINNER;
		}
		if(value.equalsIgnoreCase("1")){
			pro = ProficiencyType.INTERMIDIATE;
		}
		if(value.equalsIgnoreCase("2")){
			pro = ProficiencyType.ADVANCED;
		}
		if(value.equalsIgnoreCase("3")){
			pro = ProficiencyType.EXPERT;
		}
		List<Content> questions = em.createQuery("Select c From Content c where c.createBy.id=:orgId and c.catagoryId=:catagoryId and c.minProficiency<=:value and c.maxProficiency>=:value",Content.class)
				.setParameter("orgId", orgId)
				.setParameter("catagoryId", catagoryId)
				.setParameter("value", pro)
				.getResultList();
		return questions;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveNewRole(Role role){
		em.merge(role);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<Deck> getDeckListbyOrgId(long organizationId){
		List<Deck> deckList = em.createQuery("Select d from Deck d where d.organization.id:=id",Deck.class)
		.setParameter("id", organizationId)
		.getResultList();
		return deckList;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveNewDeckCourse(DeckCourse deckCourse){
		em.merge(deckCourse);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<DeckCourse> getAllDecksCoursesByOrgId(long organizationId){
		List<DeckCourse> deckCourseList = em.createQuery("Select dc from DeckCourse dc where dc.organization.id=:id",DeckCourse.class)
		.setParameter("id", organizationId)
		.getResultList();
		return deckCourseList;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<UserPayment> getFullPlanHistorybyOrgId(long organizationId){
		List<UserPayment> paymentHistory = em.createQuery("Select u from UserPayment u where u.organizationId=:organizationId",UserPayment.class)
		.setParameter("organizationId", organizationId)
		.getResultList();
		return paymentHistory;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<UserPayment> getFullPaymentHistory(){
		List<UserPayment> allPayments = em.createQuery("Select u from UserPayment u",UserPayment.class)
		.getResultList();
		return allPayments;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Deck getDeckSeriesById(long id){
		Deck deck = em.createQuery("Select d from Deck d where d.id=:id",Deck.class)
		.setParameter("id", id)
		.getSingleResult();
		return deck;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public DeckCourse getDeckCourseById(long id){
		DeckCourse deckCourse = em.createQuery("Select dc from DeckCourse dc where dc.id=:id",DeckCourse.class)
		.setParameter("id", id)
		.getSingleResult();
		return deckCourse;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<Level> getAllLevelBySeriesId(long id){
		List<Level> levelList = em.createQuery("Select l from Level l where l.series.id=:id",Level.class)
		.setParameter("id", id)
		.getResultList();
		return levelList;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteDeckSeriesById(long Id){
		Deck deck = em.find(Deck.class, Id);
		em.remove(deck);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteDeckCourseById(long Id){
		DeckCourse deckCourse = em.find(DeckCourse.class, Id);
		em.remove(deckCourse);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void createblog( Blog blog){
		em.persist(blog);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<Blog> getBlogDetails(){
		List<Blog> blogList = em.createQuery("Select b from Blog b",Blog.class)
				.getResultList();
				return blogList;
	}
}