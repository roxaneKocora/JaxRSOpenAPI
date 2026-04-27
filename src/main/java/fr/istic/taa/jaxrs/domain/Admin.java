package fr.istic.taa.jaxrs.domain;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import fr.istic.taa.jaxrs.domain.enumeration.NiveauAdmin;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.OneToMany;

@Entity
public class Admin extends User implements Serializable  {

	@Enumerated(EnumType.STRING)
	private NiveauAdmin admin_niveau;

	@OneToMany(mappedBy = "admin", cascade = CascadeType.PERSIST)
	//@JsonManagedReference
    private List<Event> events;


    //GETTERS - SETTERS


	public NiveauAdmin getAdmin_niveau() {
		return admin_niveau;
	}
	public void setAdmin_niveau(NiveauAdmin admin_niveau) {
		this.admin_niveau = admin_niveau;
	}
	
	
	public List<Event> getEvents() {
		return events;
	}
	public void setEvents(List<Event> events) {
		this.events = events;
	}

    //ToString Admin
	@Override
	public String toString() {
		return "Admin [events=" + events + ", userId=" + userId + ", nom=" + nom + ", prenom=" + prenom + ", email="
				+ email + ", telephone=" + telephone + ", date_naissance=" + date_naissance + ", password=" + password
				+ ", statut_User=" + statut_user + "]";
	}
	
}

