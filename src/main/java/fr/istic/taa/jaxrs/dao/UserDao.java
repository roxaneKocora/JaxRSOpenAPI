package fr.istic.taa.jaxrs.dao;

import java.util.List;
import fr.istic.taa.jaxrs.dao.generic.AbstractJpaDao;
import fr.istic.taa.jaxrs.domain.*;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;


public class UserDao extends AbstractJpaDao<Long, User> {

	public UserDao() {
		this.setClazz(User.class);
	}
	
	//Requete NamedQuery
	public User findByEmailNamedQuery(String email) {
	    try {
    		return entityManager
        		.createNamedQuery("User.findByEmail", User.class)
                .setParameter("emailValue", email)
                .getSingleResult();
	    } catch (NoResultException e) {
	        return null; 
	    }
    }
	
	// Criteria Query 
	public List<User> findAllUser() {
	    
	    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
	    
	    CriteriaQuery<User> cq = cb.createQuery(User.class);
	    
	    Root<User> userRoot = cq.from(User.class);
	    
	    cq.select(userRoot);
	    
	    return entityManager.createQuery(cq).getResultList();
	}
	
}
