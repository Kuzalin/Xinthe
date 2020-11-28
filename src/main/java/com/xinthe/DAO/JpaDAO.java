package com.xinthe.DAO;

import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



public abstract class JpaDAO<K,E> {		
	
	protected Class<E> entityClass;
	
	@SuppressWarnings("unchecked")
	public JpaDAO() {
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
		this.entityClass = (Class<E>) genericSuperclass.getActualTypeArguments()[1];
	}	
	
	@Autowired
	EntityManagerFactory entityManagerFactory;
	
	ThreadLocal<EntityManager> thread = new ThreadLocal<EntityManager>();
	
	public EntityManager getEntityManager() {
		EntityManager em =  thread.get();
		if(em != null && em.isOpen()) {			
			return em;
		} else {
			EntityManager entityManager = entityManagerFactory.createEntityManager();
			thread.set(entityManager);
			return entityManager;
		}
	}
	
	public void closeEntityManager() {
		EntityManager em =  thread.get();
		if(em != null && em.isOpen()) {	
			em.close();
			thread.set(null);
		}
	}
	
	public void commitTransaction()
	{
		EntityManager em=thread.get();
		EntityTransaction transaction=em.getTransaction();
		if(transaction.isActive())
		{
			transaction.commit();	
			closeEntityManager();
		}
	}
	
	public void beginTransaction()
	{
		EntityManager em=getEntityManager();
		EntityTransaction transaction=em.getTransaction();
		if(!transaction.isActive())
		{
			transaction.begin();
		}
	}
	
	public void rollbackTransaction()
	{
		EntityManager em=thread.get();
		EntityTransaction transaction=em.getTransaction();
		if(transaction.isActive())
		{
			transaction.rollback();	
			closeEntityManager();
		}
	}

	@Transactional
	public E merge(E entity)
	{
		
		EntityManager em=getEntityManager();
		if(!em.getTransaction().isActive())
		{
			em.getTransaction().begin();
			em.detach(entity);
		}
		entity=em.merge(entity);		
		commitTransaction();
		return entity;
	}
	
	
}
