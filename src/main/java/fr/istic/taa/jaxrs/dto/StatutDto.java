package fr.istic.taa.jaxrs.dto;

import fr.istic.taa.jaxrs.domain.enumeration.StatutEvent;

public class StatutDto {

    private StatutEvent statut_concert;

    
	public StatutEvent getStatut_concert() {
		return statut_concert;
	}

	public void setStatut_concert(StatutEvent statut_concert) {
		this.statut_concert = statut_concert;
	}

}
