package fr.istic.taa.jaxrs.service;

import java.util.List;

import fr.istic.taa.jaxrs.dao.ClientDao;
import fr.istic.taa.jaxrs.dao.EventDao;
import fr.istic.taa.jaxrs.dao.TicketDao;
import fr.istic.taa.jaxrs.domain.*;
import fr.istic.taa.jaxrs.domain.enumeration.StatutTicket;
import fr.istic.taa.jaxrs.dto.AchatTicketDto;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

public class TicketService {
    

    private ClientDao clientDao;
    private EventDao eventDao;
    private TicketDao ticketDao;

    // Injection par constructeur
    public TicketService(ClientDao clientDao, EventDao eventDao, TicketDao ticketDao) {
        this.clientDao = clientDao;
        this.eventDao = eventDao;
        this.ticketDao = ticketDao;
    }
    


    // Acheter Ticket
    @Transactional
    public Ticket acheterTicket(AchatTicketDto dto) {

        Client client = clientDao.findOne(dto.getClientId());
        Event event = eventDao.findOne(dto.getEventId());

        // 1. VERIFICATION EXISTENCE 
           if (client == null) throw new EntityNotFoundException("Client introuvable");

           if (event == null) throw new EntityNotFoundException("Événement introuvable");
           
           
        // 2. ÉTAT DU MÉTIER (verification du nombre de places)
        if (event.getNb_place_disponible() <= 0) {
            throw new IllegalStateException("Plus de places disponibles");
        }

        
        // 3. DOUBLON
        
        // Construire la clé composite
        TicketId ticket_id = new TicketId(dto.getClientId(), dto.getEventId()); 
        
        if (ticketDao.findOne(ticket_id) != null) {
            throw new IllegalStateException("Vous avez déjà un ticket pour cet event");
        }
        
        // 3. CREATION DU TICLKET
        Ticket ticket = new Ticket();
        ticket.setId(ticket_id);
        ticket.setStatut(StatutTicket.ACHETE);
        ticket.setDateAchat(dto.getDateAchat());
        ticket.setEvent(event);
        ticket.setClient(client);
        ticketDao.save(ticket);

        event.diminuerPlaces();
        eventDao.save(event);

        return ticket;
    }
    
    
    

    // Liste des Tickets par client
    public List<Ticket> findTicketsByClientId(Long clientId) {
    	
        if (this.clientDao.findOne(clientId) == null) {
            throw new EntityNotFoundException("Client Introuvable");
        }
        return ticketDao.findByClientIdNamedQuery(clientId);
    }
    
	
}
