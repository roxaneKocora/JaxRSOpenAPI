package fr.istic.taa.jaxrs.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import jakarta.persistence.*;

@Entity
public class Event implements Serializable {

    @Id
    @GeneratedValue
    private long eventId;
    
    private String nom;
    private String description;
    private String artiste;
    private String lieu;
    private LocalDate dateConcert;
    private String genreMusical;
    private int nbPlaceDispo;
    private String dureeConcert;
    private String statut;
    private LocalDate dateValidation;
    private String commentairesAdmin;
   
    @ManyToOne
    @JoinColumn(name = "manager_id") // Le nom de la colonne dans la table Event
    private Manager manager;
    
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<Ticket> tickets;

    public Event() {}
    
    // Getters et Setters...
    public List<Ticket> getTickets() { return tickets; }
    public void setTickets(List<Ticket> tickets) { this.tickets = tickets; }
}