package fr.istic.taa.jaxrs.service;

import org.mindrot.jbcrypt.BCrypt;
import fr.istic.taa.jaxrs.dao.*;
import fr.istic.taa.jaxrs.domain.*;
import fr.istic.taa.jaxrs.domain.enumeration.Role;
import fr.istic.taa.jaxrs.dto.EnregistrerManagerDto;
import fr.istic.taa.jaxrs.dto.UserResponseDto;

public class ManagerService {  
	
    private ManagerDao managerDao;
    private UserDao userDao;

    public ManagerService(ManagerDao managerDao, UserDao userDao) {
        this.managerDao = managerDao;
        this.userDao = userDao;
    }
	
	//1-Methode metier ajouter Manager
	public UserResponseDto ajouterManager(EnregistrerManagerDto dto) throws Exception {
		
	    if (this.userDao.findByEmailNamedQuery(dto.getEmail()) != null) {
	        throw new Exception("Email déjà utilisé");
	    }

	    Manager manager = new Manager();
	    
	    manager.setNom(dto.getNom());
	    manager.setPrenom(dto.getPrenom());
	    manager.setEmail(dto.getEmail());
	    manager.setTelephone(dto.getTelephone());
	    manager.setDate_naissance(dto.getDate_naissance());
	    manager.setRole(Role.MANAGER);
	    manager.setManager_agence(dto.getManager_agence());

		//Utilise le password envoyé par le formulaire
		manager.setPassword(BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt()));
	    
	    this.managerDao.save(manager); 
	    

	    
        // Mapper vers DTO de réponse
	    UserResponseDto response = new UserResponseDto();
        response.setId(manager.getUserId());
        response.setNom(manager.getNom());
        response.setPrenom(manager.getPrenom());
        response.setEmail(manager.getEmail());
        response.setTelephone(manager.getTelephone());
        response.setStatut_user(manager.isStatut_user());
        response.setDate_naissance(manager.getDate_naissance());
        response.setRole(manager.getRole());
        response.setManager_agence(manager.getManager_agence());
        
        return response;
	}


}
