package com.innverse.elearn.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.innverse.elearn.model.CourseTrack;
import com.innverse.elearn.model.ScoreBoardCourse;

@Transactional
public class ScormDao {

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
	public List<CourseTrack> getCourseTracks(long memberId, long courseId, long orgId){
		 List<CourseTrack>  courseTracks = em.createQuery("Select ct From CourseTrack ct where ct.organization.id=:orgId and ct.member.id=:memberId and ct.course.id=:courseId",CourseTrack.class)
			 .setParameter("orgId", orgId)
			 .setParameter("courseId", courseId)
			 .setParameter("memberId", memberId)
			 .getResultList();
		return courseTracks;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public CourseTrack getCourseTrack(long memberId, long courseId, long orgId , String element){
		CourseTrack  courseTrack = null;
		try{
	        courseTrack = 	em.createQuery("Select ct From CourseTrack ct where ct.organization.id=:orgId and ct.member.id=:memberId and ct.course.id=:courseId and ct.element=:element",CourseTrack.class)
			 .setParameter("orgId", orgId)
			 .setParameter("courseId", courseId)
			 .setParameter("memberId", memberId)
			 .setParameter("element", element)
			 .getSingleResult();

	    } catch(NoResultException e) {
	        return null;
	    }
		return courseTrack;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveUpdateCourseTrack(CourseTrack courseTrack){
		em.merge(courseTrack);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void saveUpdateCourseScoreBoard(ScoreBoardCourse courseScoreBoard){
		em.merge(courseScoreBoard);
	}
	
	public ScoreBoardCourse getCourseScoreBoard(long memberId, long courseId, long orgId ){
		ScoreBoardCourse  courseScoreBoard = null;
		try{
			courseScoreBoard = 	em.createQuery("Select sbc From ScoreBoardCourse sbc where sbc.organizationId=:orgId and sbc.memberId=:memberId and sbc.courseId=:courseId",ScoreBoardCourse.class)
			 .setParameter("orgId", orgId)
			 .setParameter("courseId", courseId)
			 .setParameter("memberId", memberId)
			 .getSingleResult();

	    } catch(NoResultException e) {
	        return null;
	    }
		return courseScoreBoard;
	}

}
