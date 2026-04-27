package fr.istic.taa.jaxrs.domain;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

@Entity
public class Client extends User implements Serializable{

	private boolean client_newsletter;
	
	@OneToMany(mappedBy = "client", cascade = CascadeType.PERSIST)
	@JsonManagedReference
    private List<Ticket> tickets; 

	
	//GETTERS - SETTERS
	

	public boolean isClient_newsletter() {
		return client_newsletter;
	}
	public void setClient_newsletter(boolean client_newsletter) {
		this.client_newsletter = client_newsletter;
	}
	
	
    public List<Ticket> getTickets() {
        return tickets;
    }
    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
    
    //ToString Client
	@Override
	public String toString() {
		return "Client [tickets=" + tickets + ", userId=" + userId + ", nom=" + nom + ", prenom=" + prenom + ", email="
				+ email + ", telephone=" + telephone + ", date_naissance=" + date_naissance + ", password=" + password
				+ ", statut_User=" + statut_user + "]";
	}
	
}