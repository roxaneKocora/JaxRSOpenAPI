package fr.istic.taa.jaxrs.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.istic.taa.jaxrs.dao.EventDao;
import fr.istic.taa.jaxrs.dao.ManagerDao;
import fr.istic.taa.jaxrs.domain.Event;
import fr.istic.taa.jaxrs.domain.Manager;
import fr.istic.taa.jaxrs.domain.User;
import fr.istic.taa.jaxrs.domain.enumeration.StatutConcert;
import fr.istic.taa.jaxrs.dto.EventDto;
import fr.istic.taa.jaxrs.dto.EventReponseDto;
import fr.istic.taa.jaxrs.dto.UserResponseDto;
import jakarta.persistence.EntityNotFoundException;

public class EventService {
	
    private EventDao eventDao;
	private ManagerDao managerDao;

    // Injection par constructeur
    public EventService(ManagerDao managerDao, EventDao eventDao) {
        this.managerDao = managerDao;
        this.eventDao = eventDao;
    }
    
    //creer un evenement
    public EventReponseDto creerEvent(EventDto dto) {
        Manager manager = managerDao.findOne(dto.getManagerId());
        if (manager == null) {
            throw new EntityNotFoundException("Manager introuvable");
        }

        Event event = new Event();
        event.setNom(dto.getNom());
        event.setDescription(dto.getDescription());
        event.setArtiste(dto.getArtiste());
        event.setLieu(dto.getLieu());
        event.setDate_concert(dto.getDateConcert());
        event.setGenreMusical(dto.getGenreMusical());
        event.setNb_place_disponible(dto.getNbPlaceDispo());
        event.setDureeConcert(dto.getDureeConcert());
        event.setPrix_ticket(dto.getPrix_ticket());
        event.setStatut_concert(StatutConcert.ATTENTE_VALIDATION);
        event.setManager(manager);

        eventDao.save(event);
        
        // Mapper vers DTO de réponse
        EventReponseDto response = new EventReponseDto();
        response.setEventId(event.getEventId());
        response.setNom(event.getNom());
        response.setArtiste(event.getArtiste());
        response.setDescription(event.getDescription());
        response.setDateConcert(event.getDate_concert());
        response.setDureeConcert(event.getDureeConcert());
        response.setGenreMusical(event.getGenreMusical());
        response.setLieu(event.getLieu());
        response.setNbPlaceDispo(event.getNb_place_disponible());
        response.setStatut_concert(event.getStatut_concert());
        response.setPrix_ticket(event.getPrix_ticket());
        response.setManagerId(event.getManager().getUserId());
        
        if (event.getAdmin() != null) {
            response.setAdminId(event.getAdmin().getUserId());
        } else {
            response.setAdminId(null);
        }
        
        return response;
    }
     
    
    //liste des evenements par date
    public List<EventReponseDto> findEventsByDate(LocalDate date) {
    	
	    List<Event> events = eventDao.findByDateEventNamedQuery(date);
	    List<EventReponseDto> dtos = new ArrayList<>();
 

    	    for (Event e : events) {
    	    	EventReponseDto dto = new EventReponseDto();
    	    	dto.setEventId(e.getEventId());
    	    	dto.setNom(e.getNom());
    	    	dto.setArtiste(e.getArtiste());
    	    	dto.setDescription(e.getDescription());
    	    	dto.setDateConcert(e.getDate_concert());
    	    	dto.setDureeConcert(e.getDureeConcert());
    	    	dto.setGenreMusical(e.getGenreMusical());
    	        dto.setLieu(e.getLieu());
    	        dto.setNbPlaceDispo(e.getNb_place_disponible());
    	        dto.setStatut_concert(e.getStatut_concert());
    	        dto.setManagerId(e.getManager().getUserId());
   	        
    	        if (e.getAdmin() != null) {
    	        	dto.setAdminId(e.getAdmin().getUserId());
    	        } else {
    	        	dto.setAdminId(null);
    	        }
    	        
    	        dtos.add(dto);
    	    }
    	    return dtos;
    }

    
  //liste des evenements pour un manager
    public List<EventReponseDto> findEventsByManagerId(Long managerId) {
    	
        if (this.managerDao.findOne(managerId) == null) {
            throw new EntityNotFoundException("Manager Introuvable");
        }

	    List<Event> events = eventDao.findByManagerId(managerId);
	    List<EventReponseDto> dtos = new ArrayList<>();

    	    for (Event e : events) {
    	    	EventReponseDto dto = new EventReponseDto();
    	    	dto.setEventId(e.getEventId());
    	    	dto.setNom(e.getNom());
    	    	dto.setArtiste(e.getArtiste());
    	    	dto.setDescription(e.getDescription());
    	    	dto.setDateConcert(e.getDate_concert());
    	    	dto.setDureeConcert(e.getDureeConcert());
    	    	dto.setGenreMusical(e.getGenreMusical());
    	        dto.setLieu(e.getLieu());
    	        dto.setNbPlaceDispo(e.getNb_place_disponible());
    	        dto.setStatut_concert(e.getStatut_concert());
    	        dto.setManagerId(e.getManager().getUserId());
   	        
    	        if (e.getAdmin() != null) {
    	        	dto.setAdminId(e.getAdmin().getUserId());
    	        } else {
    	        	dto.setAdminId(null);
    	        }
    	        
    	        dtos.add(dto);
    	    }
    	    return dtos;
    }

}
