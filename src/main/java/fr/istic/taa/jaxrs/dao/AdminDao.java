package fr.istic.taa.jaxrs.dao;

import fr.istic.taa.jaxrs.dao.generic.AbstractJpaDao;
import fr.istic.taa.jaxrs.domain.Admin;

public class AdminDao extends AbstractJpaDao<Long, Admin>{

	public AdminDao() {
		this.setClazz(Admin.class);
	}
}
