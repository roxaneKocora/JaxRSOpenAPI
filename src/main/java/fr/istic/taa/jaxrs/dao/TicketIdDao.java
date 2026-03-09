package fr.istic.taa.jaxrs.dao;

import fr.istic.taa.jaxrs.dao.generic.AbstractJpaDao;
import fr.istic.taa.jaxrs.domain.TicketId;

public class TicketIdDao extends AbstractJpaDao <Long , TicketId>{

	public TicketIdDao() {
		this.setClazz(TicketId.class);
	}
}
