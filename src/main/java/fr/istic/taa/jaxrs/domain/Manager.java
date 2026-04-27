package fr.istic.taa.jaxrs.domain;

import java.io.Serializable;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;

@Entity
//Requete NamedQuery
@NamedQuery(
	    name = "Manager.findByEmail",
	    query = "SELECT m FROM Manager m WHERE m.email = :emailValue"
)
public class Manager extends User implements Serializable {
	
	private String manager_agence;
	@OneToMany(mappedBy = "manager", cascade = CascadeType.PERSIST)
	//@JsonManagedReference
    private List<Event> events;


    //GETTERS - SETTERS
	
	
	public List<Event> getEvents() {
		return events;
	}
	public void setEvents(List<Event> events) {
		this.events = events;
	}
	
    //ToString Manager
	@Override
	public String toString() {
		return "Manager [events=" + events + ", userId=" + userId + ", nom=" + nom + ", prenom=" + prenom + ", email="
				+ email + ", telephone=" + telephone + ", date_naissance=" + date_naissance + ", password=" + password
				+ ", statut_User=" + statut_user + "]";
	}
	public String getManager_agence() {
		return manager_agence;
	}
	public void setManager_agence(String manager_agence) {
		this.manager_agence = manager_agence;
	}
	
}
