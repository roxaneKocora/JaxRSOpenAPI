package fr.istic.taa.jaxrs.dao;

import fr.istic.taa.jaxrs.dao.generic.AbstractJpaDao;
import fr.istic.taa.jaxrs.domain.Manager;
import jakarta.persistence.NoResultException;

public class ManagerDao extends AbstractJpaDao<Long, Manager>{

	public ManagerDao() {
		this.setClazz(Manager.class);
	}
	

}


