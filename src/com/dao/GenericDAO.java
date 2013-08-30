package com.dao;

import java.io.Serializable;
import java.util.*;
import java.util.Map.*;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaQuery;

abstract class GenericDAO<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final EntityManagerFactory emf = Persistence
			.createEntityManagerFactory("JSFCrudPU");
	private EntityManager em;

	private Class<T> entityClass;

	public void beginTransaction() {
		em = emf.createEntityManager();
		em.getTransaction().begin();
	}

	public void commit() {
		em.getTransaction().commit();
	}

	public void roolback() {
		em.getTransaction().rollback();
	}

	public void closeTransaction() {
		em.close();
	}

	public void commitAndCloseTransaction() {
		commit();
		closeTransaction();
	}

	public void flush() {
		em.flush();
	}

	public void joinTransaction() {
		em = emf.createEntityManager();
		em.joinTransaction();
	}

	public GenericDAO(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	public void save(T entity) {
		em.persist(entity);
	}

	protected void delete(Object id, Class<T> entity) {
		T entityToRemove = em.getReference(entity, id);

		em.remove(entityToRemove);
	}

	public T update(T entity) {
		return em.merge(entity);
	}

	public T find(int entityID) {
		return em.find(entityClass, entityID);
	}

	public T findReferenceOnly(int entityID) {
		return em.getReference(entityClass, entityID);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<T> findAll() {
		CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
		cq.select(cq.from(entityClass));
		return em.createQuery(cq).getResultList();
	}

	@SuppressWarnings("unchecked")
	protected T findOneResult(String namedQuery, Map<String, Object> paramenters) {
		T result = null;

		try {
			Query query = em.createNamedQuery(namedQuery);
			if (paramenters != null & !paramenters.isEmpty()) {
				populateQueryParameters(query, paramenters);
			}

			result = (T) query.getSingleResult();

		} catch (NoResultException e) {
			System.out
					.println("No result found for named query: " + namedQuery);
		} catch (Exception e) {
			System.out.println("Erro while running query: " + e.getMessage());
		}
		
		return result;
	}
	
	
	private void populateQueryParameters(Query query, Map<String, Object> paramenters){
		for(Entry<String, Object> entry: paramenters.entrySet()){
			query.setParameter(entry.getKey(), entry.getValue());
		}
	}
	

}
