package com.innverse.elearn2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.innverse.elearn.model.Category;
import com.innverse.elearn.model.Content;
import com.innverse.elearn.model.Deck;
import com.innverse.elearn.model.Group;
import com.innverse.elearn.model.Invitation;
import com.innverse.elearn.model.Organization;
import com.innverse.elearn.model.Series;
import com.innverse.elearn.model.UserProfile;
import com.innverse.elearn2.dao.TestDao;

public class TestService {

	@Autowired
	private TestDao testDao;
	
	public void saveUserProfile(UserProfile userProfile){
		testDao.saveUserProfile(userProfile);
	}
	
	public void updateUserProfile(){
		
	}
	
	public Organization getOrganization(String organizationId){
		return testDao.getOrganization(Long.valueOf(organizationId));
	}
	
	public UserProfile getUserProfile(String accountId){
		return testDao.getUserProfile(Long.valueOf(accountId));
	}
	
	public void deleteUserProfile(){
		
	}
	
	public void deleteOrganizationByUserId(String accountId){
		testDao.deleteOrganizationByUserId(Long.valueOf(accountId));
	}
	
	public Category saveOrUpdateCategory(Category category){
		return testDao.saveOrUpdateCategory(category);
	}
	
	public Category getCategory(String categoryId){
		return testDao.getCategory(Long.valueOf(categoryId));
	}
	
	public Content saveContent(Content content){
		return testDao.saveContent(content);
	}
	
	public Series saveOrUpdateSeries(Series series){
		return testDao.saveOrUpdateSeries(series);
	}
		
	public List<Content> getContentList(String subCategoryId){
		return testDao.getContentList(Long.valueOf(subCategoryId));
	}
	
	public Series getSeries(String id){
		return testDao.getSeries(Long.valueOf(id));
	}
	
	public Invitation getInvitation(Invitation invite){
		return testDao.getInvitation(invite);
	}
	
	public Group getGroupById(long groupId){
		return testDao.getGroupById(groupId);
	}
	
	public List<Deck> getDeckListbyGroupId(long groupId){
		return testDao.getDeckListbyGroupId(groupId);
	}

	public void saveDeck(Deck deck){
		testDao.saveNewDeck(deck);
	}

}
