package fr.istic.taa.jaxrs.dao;

import java.time.LocalDate;
import java.util.List;

import fr.istic.taa.jaxrs.dao.generic.AbstractJpaDao;
import fr.istic.taa.jaxrs.domain.Event;
import fr.istic.taa.jaxrs.domain.Manager;
import fr.istic.taa.jaxrs.domain.Ticket;

public class EventDao extends AbstractJpaDao<Long, Event> {

	public EventDao() {
		this.setClazz(Event.class);
	}
	
	//Requete NamedQuery
	public List<Event> findByDateEventNamedQuery(LocalDate date) {
		
        		return entityManager
	        		.createNamedQuery("Event.findByDateEvent", Event.class)
	                .setParameter("dateConcert", date)
	                .getResultList();
    }
	

    // Requête JPQL
    public List<Event> findByManagerId(Long managerId) {
	    String query_jpql = "SELECT e FROM Event e WHERE e.manager.userId = :manager_id";
    	
            return entityManager
            		.createQuery(query_jpql, Event.class)
                    .setParameter("manager_id", managerId)
                    .getResultList();
            
    }

}
