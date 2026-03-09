package fr.istic.taa.jaxrs.domain;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;

@Entity
public class Admin extends User implements Serializable  {
	
//	@GeneratedValue
//    private int adminId;

    public Admin() {}

    public void validerConcert() {
    }

    public void suspendreUtilisateur() {
    }
}

