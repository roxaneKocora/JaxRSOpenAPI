package fr.istic.taa.jaxrs.domain;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import fr.istic.taa.jaxrs.domain.enumeration.StatutTicket;
import jakarta.persistence.*;


@Entity
@NamedQuery(
	    name = "Ticket.findByClientId",
	    query = "SELECT t FROM Ticket t WHERE t.client.userId = :client_id"
	)
public class Ticket implements Serializable {
    
    @EmbeddedId  
    private TicketId id;

	@Enumerated(EnumType.STRING)
    private StatutTicket statut_ticket;
	
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateAchat;

	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateAnnulation;
    
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate dateRemboursement;
    
    @ManyToOne
    @MapsId("userId") 
    @JoinColumn(name = "userId", nullable = false)
    @JsonBackReference
    private Client client;
    
    @ManyToOne
    @MapsId("eventId") 
    @JoinColumn(name = "eventId", nullable = false)
    @JsonBackReference
    private Event event;
    
    // Getters et Setters
    public TicketId getId() { return id; }
    public void setId(TicketId id) { this.id = id; }
    
    public Client getClient() { return client; }
    public void setClient(Client client) { this.client = client; }
    
    public Event getEvent() { return event; }
    public void setEvent(Event event) { this.event = event; }
    
    
	public StatutTicket getStatut() {
		return statut_ticket;
	}
	public void setStatut(StatutTicket statut_ticket) {
		this.statut_ticket = statut_ticket;
	}
	
	public LocalDate getDateAchat() {
		return dateAchat;
	}
	public void setDateAchat(LocalDate dateAchat) {
		this.dateAchat = dateAchat;
	}
	
	
	public LocalDate getDateAnnulation() {
		return dateAnnulation;
	}
	public void setDateAnnulation(LocalDate dateAnnulation) {
		this.dateAnnulation = dateAnnulation;
	}
	
	
	public LocalDate getDateRemboursement() {
		return dateRemboursement;
	}
	public void setDateRemboursement(LocalDate dateRemboursement) {
		this.dateRemboursement = dateRemboursement;
	}
	
	@Override
	public String toString() {
		return "Ticket [id=" + id + ", numeroPlace=" + "statut_ticket= " + statut_ticket
				+ ", dateAchat=" + dateAchat + ", dateAnnulation=" + dateAnnulation + ", dateRemboursement="
				+ dateRemboursement + ", client=" + client + ", event=" + event + "]";
	}
    
}