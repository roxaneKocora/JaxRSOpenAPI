package fr.istic.taa.jaxrs.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import fr.istic.taa.jaxrs.domain.enumeration.Role;


@Entity
@NamedQuery(
	    name = "User.findByEmail",
	    query = "SELECT u FROM User u WHERE u.email = :emailValue"
	)
@Inheritance(strategy = InheritanceType.JOINED)
public class User implements Serializable {

	@Id
	@GeneratedValue
	protected Long userId;

	@Enumerated(EnumType.STRING)
    protected Role role;

	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    protected LocalDate date_naissance;
	
	protected boolean statut_user = false;
    protected String nom;
    protected String prenom;
    protected String email;
    protected String password;
    protected String telephone;

    
    //Getters and Setters
    
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}


	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}


	public LocalDate getDate_naissance() {
		return date_naissance;
	}
	public void setDate_naissance(LocalDate date_naissance) {
		this.date_naissance = date_naissance;
	}


	public boolean isStatut_user() {
		return statut_user;
	}
	public void setStatut_user(boolean statut_user) {
		this.statut_user = statut_user;
	}


	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}


	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	
    //ToString User
	@Override
	public String toString() {
		return "User [userId=" + userId + ", nom=" + nom + ", prenom=" + prenom + "]";
	}


}
