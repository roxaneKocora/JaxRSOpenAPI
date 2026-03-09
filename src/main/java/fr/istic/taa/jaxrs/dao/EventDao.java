package fr.istic.taa.jaxrs.dao;

import fr.istic.taa.jaxrs.dao.generic.AbstractJpaDao;
import fr.istic.taa.jaxrs.domain.Event;

public class EventDao extends AbstractJpaDao <Long , Event>{
	
	public EventDao() {
		this.setClazz(Event.class);
	}

}
