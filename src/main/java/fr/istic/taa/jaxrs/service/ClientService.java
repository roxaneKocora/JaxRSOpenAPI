package fr.istic.taa.jaxrs.service;

import org.mindrot.jbcrypt.BCrypt;

import fr.istic.taa.jaxrs.dao.ClientDao;
import fr.istic.taa.jaxrs.dao.UserDao;
import fr.istic.taa.jaxrs.domain.Client;
import fr.istic.taa.jaxrs.domain.enumeration.Role;
import fr.istic.taa.jaxrs.dto.*;

public class ClientService {
    
    private ClientDao clientDao;
    private UserDao userDao;

    // Injection par constructeur
    public ClientService(ClientDao clientDao, UserDao userDao) {
        this.clientDao = clientDao;
        this.userDao = userDao;
    }
    
    
	//Methode metier S'inscrire
	public UserResponseDto inscription(InscriptionClientDto dto) throws Exception {
		
	    if (this.userDao.findByEmailNamedQuery(dto.getEmail()) != null) {
	        throw new Exception("Email déjà utilisé");
	    }
	    
	    Client client = new Client();
	    
	    client.setNom(dto.getNom());
	    client.setPrenom(dto.getPrenom());
	    client.setEmail(dto.getEmail());
	    client.setTelephone(dto.getTelephone());
	    client.setDate_naissance(dto.getDate_naissance());
	    client.setClient_newsletter(dto.isClient_newsletter());
	    client.setRole(Role.CLIENT);
	    
	    String hashed = BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt());
	    client.setPassword(hashed);
	    
	    this.clientDao.save(client); // Appel simple au DAO
	    
        // Mapper vers DTO de réponse
	    UserResponseDto response = new UserResponseDto();
        response.setId(client.getUserId());
        response.setNom(client.getNom());
        response.setPrenom(client.getPrenom());
        response.setEmail(client.getEmail());
        response.setTelephone(client.getTelephone());
        response.setStatut_user(client.isStatut_user());
        response.setDate_naissance(client.getDate_naissance());
        response.setRole(client.getRole());
        response.setClient_newsletter(client.isClient_newsletter());
        
        return response;
	}
	

}
