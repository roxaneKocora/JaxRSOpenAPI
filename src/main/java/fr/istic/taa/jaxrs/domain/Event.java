package fr.istic.taa.jaxrs.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import fr.istic.taa.jaxrs.domain.enumeration.Role;
import fr.istic.taa.jaxrs.domain.enumeration.StatutConcert;
import jakarta.persistence.*;

@Entity
//Requete NamedQuery
@NamedQuery(
	    name = "Event.findByDateEvent",
	    query = "SELECT e FROM Event e WHERE e.date_concert = :dateConcert"
	)
public class Event implements Serializable {

	@Id
    @GeneratedValue
    private long eventId;
	private String nom;
    private String description;
    private String artiste;
    private String lieu;
    private int dureeConcert;
    private String genreMusical;
    private int nb_place_disponible;
    
    @Enumerated(EnumType.STRING)
    private StatutConcert statut_concert;

    @Column(precision = 8, scale = 2)
    private BigDecimal prix_ticket;
    
    @JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date_concert;

    @ManyToOne
    @JoinColumn(name = "managerId", nullable = false)
    //@JsonBackReference
    private Manager manager;

    @ManyToOne
    @JoinColumn(name = "adminId", nullable = true)
    //@JsonBackReference
    private Admin admin;


    @OneToMany(mappedBy = "event", cascade = CascadeType.PERSIST)
	//@JsonManagedReference
    private List<Ticket> tickets;

    
    //GETTERS - SETTERS

	public long getEventId() {
		return eventId;
	}
	public void setEventId(long eventId) {
		this.eventId = eventId;
	}


	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}


	public String getArtiste() {
		return artiste;
	}
	public void setArtiste(String artiste) {
		this.artiste = artiste;
	}


	public String getLieu() {
		return lieu;
	}
	public void setLieu(String lieu) {
		this.lieu = lieu;
	}


	public int getDureeConcert() {
		return dureeConcert;
	}
	public void setDureeConcert(int dureeConcert) {
		this.dureeConcert = dureeConcert;
	}


	public String getGenreMusical() {
		return genreMusical;
	}
	public void setGenreMusical(String genreMusical) {
		this.genreMusical = genreMusical;
	}


	public int getNb_place_disponible() {
		return nb_place_disponible;
	}
	public void setNb_place_disponible(int nb_place_disponible) {
		this.nb_place_disponible = nb_place_disponible;
	}


	public StatutConcert getStatut_concert() {
		return statut_concert;
	}
	public void setStatut_concert(StatutConcert statut_concert) {
		this.statut_concert = statut_concert;
	}


	public BigDecimal getPrix_ticket() {
		return prix_ticket;
	}
	public void setPrix_ticket(BigDecimal prix_ticket) {
		this.prix_ticket = prix_ticket;
	}


	public LocalDate getDate_concert() {
		return date_concert;
	}
	public void setDate_concert(LocalDate date_concert) {
		this.date_concert = date_concert;
	}


	public Manager getManager() {
		return manager;
	}
	public void setManager(Manager manager) {
		this.manager = manager;
	}


	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}


	public List<Ticket> getTickets() {
		return tickets;
	}
	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	
    //ToString Event
	@Override
	public String toString() {
		return "Event [eventId=" + eventId + ", nom=" + nom + ", description=" + description + ", artiste=" + artiste
				+ ", lieu=" + lieu + ", dureeConcert=" + dureeConcert + ", genreMusical=" + genreMusical
				+ ", nb_place_disponible=" + nb_place_disponible + ", statut_concert=" + statut_concert
				+ ", prix_ticket=" + prix_ticket + ", date_concert=" + date_concert + ", manager=" + manager
				+ ", admin=" + admin + ", tickets=" + tickets + "]";
	}
	
	
    // diminuer le nombre de places disponibles
	public void diminuerPlaces() {
	    if (this.nb_place_disponible <= 0) {
	        throw new IllegalArgumentException("Plus de places disponibles");
	    }
	    this.nb_place_disponible--;
	}
	
    
}