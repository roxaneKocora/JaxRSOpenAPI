package fr.istic.taa.jaxrs.dao;

import java.util.List;
import fr.istic.taa.jaxrs.dao.generic.AbstractJpaDao;
import fr.istic.taa.jaxrs.domain.Ticket;
import fr.istic.taa.jaxrs.domain.TicketId;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

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
	
	// Criteria Query 
    public List<Ticket> findByEventId(Long eid) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Ticket> cq = cb.createQuery(Ticket.class);
        
        Root<Ticket> ticket = cq.from(Ticket.class);
        
        Predicate eventCondition = cb.equal(ticket.get("event").get("eventId"), eid);
        
        cq.where(eventCondition);

        return entityManager.createQuery(cq).getResultList();
    }

    //Requete jpql
    public long countTicketsByEvent(Long eventId) {
        TypedQuery<Long> query = entityManager
        						.createQuery("SELECT COUNT(t) FROM Ticket t WHERE t.event.eventId = :event_id", Long.class)
        						.setParameter("event_id", eventId);
        
        return query.getSingleResult();
    }

    public List<Ticket> findByClientId(Long clientId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Ticket> cq = cb.createQuery(Ticket.class);

        Root<Ticket> ticket = cq.from(Ticket.class);

        Predicate clientCondition = cb.equal(ticket.get("client").get("userId"), clientId);

        cq.where(clientCondition);

        return entityManager.createQuery(cq).getResultList();
    }
    
}
