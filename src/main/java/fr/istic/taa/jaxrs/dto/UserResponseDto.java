package fr.istic.taa.jaxrs.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import fr.istic.taa.jaxrs.domain.enumeration.NiveauAdmin;
import fr.istic.taa.jaxrs.domain.enumeration.Role;
import io.swagger.v3.oas.annotations.media.Schema;

public class UserResponseDto {
	
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
	private Role role;
	private boolean statut_user;

    // attributs spécifique pour Manager
	@Schema(description = "Concerne seuleument CLIENT", nullable = true)
	private boolean client_newsletter;
	
    @Schema(description = "Concerne seuleument MANAGER", nullable = true)
    private String manager_agence;  

    @Schema(description = "Niveau de l'admin (uniquement si rôle ADMIN)", example = "ADMIN", nullable = true)
    private NiveauAdmin admin_niveau;
    
    @JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date_naissance;
    
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	
	
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}


	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	
	public boolean isStatut_user() {
		return statut_user;
	}
	public void setStatut_user(boolean statut_user) {
		this.statut_user = statut_user;
	}

	
	public LocalDate getDate_naissance() {
		return date_naissance;
	}
	public void setDate_naissance(LocalDate date_naissance) {
		this.date_naissance = date_naissance;
	}
	
	public boolean isClient_newsletter() {
		return client_newsletter;
	}
	public void setClient_newsletter(boolean client_newsletter) {
		this.client_newsletter = client_newsletter;
	}
	public String getManager_agence() {
		return manager_agence;
	}
	public void setManager_agence(String manager_agence) {
		this.manager_agence = manager_agence;
	}
	public NiveauAdmin getAdmin_niveau() {
		return admin_niveau;
	}
	public void setAdmin_niveau(NiveauAdmin admin_niveau) {
		this.admin_niveau = admin_niveau;
	}
	
	

}

