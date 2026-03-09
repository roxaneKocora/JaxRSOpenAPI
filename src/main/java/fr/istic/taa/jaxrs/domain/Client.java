package fr.istic.taa.jaxrs.domain;

import java.io.Serializable;
import java.util.List;
import jakarta.persistence.*;

@Entity
public class Client extends User implements Serializable{

    private String statutClient;

    // L'annotation doit être ICI, sur le champ, pas sur le getter
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Ticket> tickets; 
    
    public Client() {}

    // Méthodes métier...
    public void acheterTicket() {}
    public void annulerTicket() {}
    public void demanderRemboursement() {}
    public void modifierTicket() {}
    public void telechargerTicket() {}
    public void rechercherConcert(String critere) {}

    // Getters et Setters standards (sans annotations JPA ici)
    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}