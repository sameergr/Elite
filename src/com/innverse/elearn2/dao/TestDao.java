package com.innverse.elearn2.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.innverse.elearn.model.Category;
import com.innverse.elearn.model.Content;
import com.innverse.elearn.model.Deck;
import com.innverse.elearn.model.Group;
import com.innverse.elearn.model.Invitation;
import com.innverse.elearn.model.Organization;
import com.innverse.elearn.model.Series;
import com.innverse.elearn.model.UserProfile;



@Transactional
public class TestDao {

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
	public void saveUserProfile(UserProfile userProfile){
		em.persist(userProfile);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void updateUserProfile(){
	}
	
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public Organization getOrganization(Long organizationId){
		return em.find(Organization.class, organizationId);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public UserProfile getUserProfile(Long accountId){
		return  em.find(UserProfile.class, accountId);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteUserProfile(){
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void deleteOrganizationByUserId(Long accountId){
		UserProfile userProfile = em.find(UserProfile.class, accountId);
		userProfile.setOrganization(null);
		em.merge(userProfile);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Category saveOrUpdateCategory(Category category){
		return em.merge(category);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public Category getCategory(Long categoryId){
		return em.find(Category.class, categoryId);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Content saveContent(Content content){
		return em.merge(content);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Series saveOrUpdateSeries(Series series){
		return em.merge(series);
	}
	
	public List<Content> getContentList(Long subCategoryId){
		List<Content> contentList = em.createQuery("Select c From Content c Where c.subCategoryId=:subCategoryId",Content.class)
				.setParameter("subCategoryId", subCategoryId)
				.getResultList();
		return contentList;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
	public Series getSeries(Long id){
		Series series = em.createQuery("Select s From Series s Where s.id=:id",Series.class)
				.setParameter("id", id)
				.getSingleResult();
		return series;
	}
	
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Invitation getInvitation(Invitation invite){
		return em.merge(invite);
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
	public void saveNewDeck(Deck deck){
		em.merge(deck);
	}

}
