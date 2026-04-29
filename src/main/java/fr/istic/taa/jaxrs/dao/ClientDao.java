package fr.istic.taa.jaxrs.dao;

import fr.istic.taa.jaxrs.dao.generic.AbstractJpaDao;
import fr.istic.taa.jaxrs.domain.Client;
import jakarta.persistence.*;

public class ClientDao extends AbstractJpaDao<Long, Client>{

	public ClientDao() {
		this.setClazz(Client.class);
	}
	
	
}
