package fr.istic.taa.jaxrs.domain;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Manager extends User implements Serializable {

//	@Id
//	@GeneratedValue
//    private int managerId;
	
    private String statutManager;
    
  
    @OneToMany(mappedBy = "manager", cascade = CascadeType.ALL)
    private List<Event> events;


    
    public Manager() {}

    public void creerEvent() {
    }

    public void modifierEvent() {
    }

    public void deleteEvent() {
    }

    public void voirStats() {
    }

    public void notifierClient() {
    }

    
	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}
}
