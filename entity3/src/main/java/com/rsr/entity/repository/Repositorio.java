package com.rsr.entity.repository;

import java.util.List;

//import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rsr.entity.model.Tree;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query; 

@Repository
public class Repositorio {
	
	@PersistenceContext 
	private EntityManager entityManager;
	
	@Transactional(readOnly=true )
	public int nodos() {
		String sqlcmd = "SELECT COUNT(*) FROM TREE";
		Query query = entityManager.createNativeQuery(sqlcmd);
		return ((Number) query.getSingleResult()).intValue();		
	}

	@Transactional(readOnly=true )
	public List<Tree> ordenMayor() {
		String sqlcmd = "SELECT t FROM Tree t WHERE t.orden > :orden";
		int parametro = 10000;
		Query query = entityManager.createQuery(sqlcmd, Tree.class);
		query.setParameter("orden", parametro);
		return query.getResultList();		
	}
	
	@Transactional(readOnly=true )
	public int insertArea() {
		String sqlcmd = "INSERT INTO Area (area, orden) values('insertNewArea', 10)";
		Query query = entityManager.createQuery(sqlcmd);
		return query.executeUpdate();		
	}
	
}
