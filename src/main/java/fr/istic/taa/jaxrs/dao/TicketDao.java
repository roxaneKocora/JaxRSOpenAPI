package fr.istic.taa.jaxrs.dao;

import java.util.List;
import fr.istic.taa.jaxrs.dao.generic.AbstractJpaDao;
import fr.istic.taa.jaxrs.domain.Ticket;
import fr.istic.taa.jaxrs.domain.TicketId;

public class TicketDao extends AbstractJpaDao<TicketId, Ticket> {

    public TicketDao() {
        this.setClazz(Ticket.class);
    }
	
	//Requete NamedQuery
	public List<Ticket> findByClientIdNamedQuery(Long id) {
		
		return entityManager
			.createNamedQuery("Ticket.findByClientId", Ticket.class)
	        .setParameter("client_id", id)
	        .getResultList();
    }

    // Requête JPQL
    public List<Ticket> findByEventId(Long eid) {
	    String query_jpql = "SELECT t FROM Ticket t WHERE t.event.eventId = :event_id";
    	
            return entityManager
            		.createQuery(query_jpql, Ticket.class)
                    .setParameter("event_id", eid)
                    .getResultList();  
    }
    
}
