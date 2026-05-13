package fr.istic.taa.jaxrs.domain;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TicketId implements Serializable {
    private Long userId;
    private Long eventId;
    

	//CONSTRUCTEURS
    public TicketId() {
    	super();
    }
    public TicketId(Long userId, Long eventId) {
        this.userId = userId;
        this.eventId = eventId;
    }
    

    //GETTERS - SETTERS
    
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    
    public Long getEventId() { return eventId; }
    public void setEventId(Long eventId) { this.eventId = eventId; }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketId ticketId = (TicketId) o;
        return userId == ticketId.userId && eventId == ticketId.eventId;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(userId, eventId);
    }
}