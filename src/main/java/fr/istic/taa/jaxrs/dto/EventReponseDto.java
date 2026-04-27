package fr.istic.taa.jaxrs.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import fr.istic.taa.jaxrs.domain.enumeration.StatutConcert;

public class EventReponseDto {

    private long eventId;
    private String nom;
    private String description;
    private String artiste;
    private String lieu;
    private String genreMusical;
    private int nbPlaceDispo;
    private int dureeConcert;
    private Long managerId;
    private Long adminId;
    private BigDecimal prix_ticket;
    private StatutConcert statut_concert;

	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateConcert;
	
	
    // GETTERS-SETTERS

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
	

	public LocalDate getDateConcert() {
		return dateConcert;
	}
	public void setDateConcert(LocalDate dateConcert) {
		this.dateConcert = dateConcert;
	}
	
	
	public String getGenreMusical() {
		return genreMusical;
	}
	public void setGenreMusical(String genreMusical) {
		this.genreMusical = genreMusical;
	}
	
	public int getNbPlaceDispo() {
		return nbPlaceDispo;
	}
	public void setNbPlaceDispo(int nbPlaceDispo) {
		this.nbPlaceDispo = nbPlaceDispo;
	}
	
	public int getDureeConcert() {
		return dureeConcert;
	}
	public void setDureeConcert(int dureeConcert) {
		this.dureeConcert = dureeConcert;
	}
	

	public StatutConcert getStatut_concert() {
		return statut_concert;
	}
	public void setStatut_concert(StatutConcert statut_concert) {
		this.statut_concert = statut_concert;
	}
	
	
	
	public Long getManagerId() {
		return managerId;
	}
	public void setManagerId(Long managerId) {
		this.managerId = managerId;
	}
	
	
	public Long getAdminId() {
		return adminId;
	}
	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}
	

	
	public BigDecimal getPrix_ticket() {
		return prix_ticket;
	}
	public void setPrix_ticket(BigDecimal prix_ticket) {
		this.prix_ticket = prix_ticket;
	}

}
