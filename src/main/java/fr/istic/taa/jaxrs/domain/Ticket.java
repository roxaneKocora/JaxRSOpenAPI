package fr.istic.taa.jaxrs.domain;

import java.io.Serializable;
import java.time.LocalDate;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

@Entity
public class Ticket implements Serializable {
    
    @EmbeddedId  
    private TicketId id;
    
    private int numeroPlace;
    private long prix;
    private String statut;
    private LocalDate dateAchat;
    private LocalDate dateAnnulation;
    private LocalDate dateRemboursement;
    
    @ManyToOne
    @MapsId("userId") 
    @JoinColumn(name = "userId", nullable = false)
    private Client client;
    
    @ManyToOne
    @MapsId("eventId")  // ✅ Map à TicketId.eventId
    @JoinColumn(name = "eventId", nullable = false)
    private Event event;
    
    public Ticket() {}
    
    public Ticket(int userId, int eventId) {
        this.id = new TicketId(userId, eventId);
    }
    
    // Getters et Setters
    public TicketId getId() { return id; }
    public void setId(TicketId id) { this.id = id; }
    
    public Client getClient() { return client; }
    public void setClient(Client client) { this.client = client; }
    
    public Event getEvent() { return event; }
    public void setEvent(Event event) { this.event = event; }
    
    public void diminuer() {
        // diminuer le nombre de places disponibles
    }
}