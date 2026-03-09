package fr.istic.taa.jaxrs.domain;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected long userId;
	
    protected String nom;
    protected String prenom;
    protected String email;
    protected long tel;
    protected LocalDate dateNaiss;
    protected String mdp;

    public User(long userId, String nom, String prenom, String email, long tel, LocalDate dateNaiss, String mdp) {
		super();
		this.userId = userId;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.tel = tel;
		this.dateNaiss = dateNaiss;
		this.mdp = mdp;
	}
    
    public User() {}

    public void seConnecter() {
        // logique de connexion
    }

    public void seDeconnecter() {
        // logique de déconnexion
    }

	public long getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	public long getTel() {
		return tel;
	}

	public void setTel(long tel) {
		this.tel = tel;
	}

	public LocalDate getDateNaiss() {
		return dateNaiss;
	}

	public void setDateNaiss(LocalDate dateNaiss) {
		this.dateNaiss = dateNaiss;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
}
