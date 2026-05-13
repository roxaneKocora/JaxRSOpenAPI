package fr.istic.taa.jaxrs.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import jakarta.validation.constraints.Positive;

public class EventDto {
    private String nom;
    private String description;
    private String artiste;
    private String lieu;
    private LocalDate dateConcert;
    private String genreMusical;
    private int nbPlaceDispo;
    private BigDecimal prix_ticket;
    private int dureeConcert;
    private Long managerId; 
	
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
	

	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
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
	
	public Long getManagerId() {
		return managerId;
	}
	public void setManagerId(Long managerId) {
		this.managerId = managerId;
	}
	
	public BigDecimal getPrix_ticket() {
		return prix_ticket;
	}
	public void setPrix_ticket(BigDecimal prix_ticket) {
		this.prix_ticket = prix_ticket;
	}
}
