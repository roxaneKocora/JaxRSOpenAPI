package fr.istic.taa.jaxrs.service;

import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import fr.istic.taa.jaxrs.dao.AdminDao;
import fr.istic.taa.jaxrs.dao.UserDao;
import fr.istic.taa.jaxrs.domain.Admin;
import fr.istic.taa.jaxrs.domain.Manager;
import fr.istic.taa.jaxrs.domain.User;
import fr.istic.taa.jaxrs.domain.enumeration.NiveauAdmin;
import fr.istic.taa.jaxrs.domain.enumeration.Role;
import fr.istic.taa.jaxrs.dto.EnregistrerAdminDto;
import fr.istic.taa.jaxrs.dto.EnregistrerManagerDto;
import fr.istic.taa.jaxrs.dto.UserResponseDto;

public class AdminService {
    
    private AdminDao adminDao;
    private UserDao userDao;

    // Injection par constructeur
    public AdminService(AdminDao adminDao, UserDao userDao) {
        this.adminDao = adminDao;
        this.userDao = userDao;
    }


	//1-Methode metier ajouter Manager
	public UserResponseDto ajouterAdmin(EnregistrerAdminDto dto) throws Exception {
		
	    if (this.userDao.findByEmailNamedQuery(dto.getEmail()) != null) {
	        throw new Exception("Email déjà utilisé");
	    }

	    Admin admin = new Admin();
	    
	    admin.setNom(dto.getNom());
	    admin.setPrenom(dto.getPrenom());
	    admin.setEmail(dto.getEmail());
	    admin.setTelephone(dto.getEmail());
	    admin.setDate_naissance(dto.getDate_naissance());
	    admin.setRole(Role.ADMIN);
	    admin.setAdmin_niveau(NiveauAdmin.ADMIN);

		//Utilise le password envoyé par le formulaire
	    admin.setPassword(BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt()));
	    
	    this.adminDao.save(admin); 
	    
	    
        // Mapper vers DTO de réponse
	    UserResponseDto response = new UserResponseDto();
        response.setId(admin.getUserId());
        response.setNom(admin.getNom());
        response.setPrenom(admin.getPrenom());
        response.setEmail(admin.getEmail());
        response.setTelephone(admin.getTelephone());
        response.setStatut_user(admin.isStatut_user());
        response.setDate_naissance(admin.getDate_naissance());
        response.setRole(admin.getRole());
        response.setAdmin_niveau(admin.getAdmin_niveau());
        
        return response;
	}
	
	
	// Liste des Admins
	public List<UserResponseDto> listeAdmins() {
	    List<Admin> admins = adminDao.findAll();
	    List<UserResponseDto> dtos = new ArrayList<>();

	    for (Admin a : admins) {
	        UserResponseDto dto = new UserResponseDto();
	        dto.setId(a.getUserId());
	        dto.setNom(a.getNom());
	        dto.setPrenom(a.getPrenom());
	        dto.setEmail(a.getEmail());
	        dto.setTelephone(a.getTelephone());
	        dto.setRole(a.getRole());
	        dto.setStatut_user(a.isStatut_user());
	        dto.setAdmin_niveau(a.getAdmin_niveau());
	        dtos.add(dto);
	    }
	    return dtos;
	}
	
}
