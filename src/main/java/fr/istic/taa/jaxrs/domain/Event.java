package fr.istic.taa.jaxrs.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import fr.istic.taa.jaxrs.domain.enumeration.StatutEvent;
import jakarta.persistence.*;

@Entity
@NamedQuery(
	    name = "Event.findAvailableEvent",
	    query = "SELECT e FROM Event e WHERE e.date_concert > :now AND e.nb_place_disponible > 0"
	    		+ "AND e.is_deleted = false AND e.statut_event != ANNULE"
	)
public class Event implements Serializable {

	@Id
    @GeneratedValue
    private Long eventId;
	private String nom;
    private String description;
    private String artiste;
    private String lieu;
    private int duree_event;
    private String genreMusical;
    private int nb_place_disponible;
    private boolean is_deleted;
    
    @Enumerated(EnumType.STRING)
    private StatutEvent statut_event;

    @Column(precision = 8, scale = 2)
    private BigDecimal prix_ticket;
    
    @JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date_concert;

    @ManyToOne
    @JoinColumn(name = "managerId", nullable = false)
    private Manager manager;

    @ManyToOne
    @JoinColumn(name = "adminId", nullable = true)
    private Admin admin;


    @OneToMany(mappedBy = "event", cascade = CascadeType.PERSIST)
    private List<Ticket> tickets;

    
    //GETTERS - SETTERS

	public Long getEventId() {
		return eventId;
	}
	public void setEventId(Long eventId) {
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
		return duree_event;
	}
	public void setDureeConcert(int duree_event) {
		this.duree_event = duree_event;
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


	public StatutEvent getStatut_concert() {
		return statut_event;
	}
	public void setStatut_concert(StatutEvent statut_concert) {
		this.statut_event = statut_concert;
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

	
	public boolean isDeleted() {
		return is_deleted;
	}
	public void setDeleted(boolean is_deleted) {
		this.is_deleted = is_deleted;
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
				+ ", lieu=" + lieu + ", dureeConcert=" + duree_event + ", genreMusical=" + genreMusical
				+ ", nb_place_disponible=" + nb_place_disponible + ", statut_concert=" + statut_event
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