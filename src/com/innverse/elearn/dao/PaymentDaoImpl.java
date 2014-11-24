package com.innverse.elearn.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.innverse.elearn.model.UserPayment;

@Transactional
public class PaymentDaoImpl {

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
	public void savePaymentTransaction(UserPayment paymentTransaction){
		em.persist(paymentTransaction);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void mergePaymentTransaction(UserPayment userPayment){
		em.merge(userPayment);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void finalPaymentTransaction(UserPayment paymentTransaction){
		em.merge(paymentTransaction);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public UserPayment getUserPaymentByToken(String token){
		UserPayment userPayment =  em.createQuery("Select pp From UserPayment pp Where pp.token=:token",UserPayment.class)
		.setParameter("token", token)
		.getSingleResult();
		
		return userPayment; 
	}
}
