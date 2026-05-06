package fr.istic.taa.jaxrs.service;

import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import fr.istic.taa.jaxrs.dao.UserDao;
import fr.istic.taa.jaxrs.domain.*;
import fr.istic.taa.jaxrs.dto.ConnexionDto;
import fr.istic.taa.jaxrs.dto.PasswordDto;
import fr.istic.taa.jaxrs.dto.StatutUserDto;
import fr.istic.taa.jaxrs.dto.UserResponseDto;
import jakarta.persistence.EntityNotFoundException;

public class UserService {
    
    private UserDao userDao;

    // Injection par constructeur
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }
	

	//1-Methode seConnecter
	public UserResponseDto seConnecter(ConnexionDto dto) {
	    // 1. On demande au DAO de nous donner l'utilisateur par son email
	    User user = userDao.findByEmailNamedQuery(dto.getEmail());

	    if (user == null) {
	        throw new RuntimeException("Utilisateur non trouvé");
	    }

	    // 2. on va utiliser la fonction de comparaison
	    boolean match = BCrypt.checkpw(dto.getPassword(), user.getPassword());

	    if (!match) {
	        throw new RuntimeException("Mot de passe incorrect");
	    }

	    
        // 3. Mapper vers UserResponseDTO 
	    UserResponseDto response = new UserResponseDto();
        response.setId(user.getUserId());
        response.setNom(user.getNom());
        response.setPrenom(user.getPrenom());
        response.setEmail(user.getEmail());
        response.setTelephone(user.getTelephone());
        response.setStatut_user(user.isStatut_user());
        response.setDate_naissance(user.getDate_naissance());
        response.setRole(user.getRole()); 
        
        return response; // Connexion réussie
	}


	//2- Modifier MDP
	public void changerMdp(Long user_id, PasswordDto dto) throws Exception {
	
	    User user = userDao.findOne(user_id);

	    if (user == null) {
	        throw new RuntimeException("Utilisateur non trouvé");
	    }

	    boolean match = BCrypt.checkpw(dto.getOldPassword(), user.getPassword());

	    if (!match) {
	        throw new RuntimeException("Ancien mot de passe incorrect  ou données invalides");
	    }

	    String hashed = BCrypt.hashpw(dto.getNewPassword(), BCrypt.gensalt());
	    user.setPassword(hashed);
	    
	    this.userDao.update(user);
	    
	}
	

	//3- Suspendre Utilisateur
    public UserResponseDto suspendreUtilisateur(Long userId, StatutUserDto dto) {
        User user = userDao.findOne(userId);

        if (user == null) {
            throw new EntityNotFoundException("Utilisateur introuvable.");
        }

        if (user.isStatut_user()) {
        	
            user.setStatut_user(false);       	
        }else {
            user.setStatut_user(true);
        }
        
        userDao.update(user);
        // Mapper vers UserResponseDTO 
	    UserResponseDto response = new UserResponseDto();
        response.setId(user.getUserId());
        response.setNom(user.getNom());
        response.setPrenom(user.getPrenom());
        response.setEmail(user.getEmail());
        response.setTelephone(user.getTelephone());
        response.setStatut_user(user.isStatut_user());
        response.setDate_naissance(user.getDate_naissance());
        response.setRole(user.getRole()); 
        
        return response;
    }
    
    
	//4- Liste des utilisateurs
	public List<UserResponseDto> listeUtilisateurs() {
	    List<User> users = userDao.findAllUser();
	    List<UserResponseDto> dtos = new ArrayList<>();

	    for (User u : users) {
	        UserResponseDto dto = new UserResponseDto();
	        dto.setId(u.getUserId());
	        dto.setNom(u.getNom());
	        dto.setPrenom(u.getPrenom());
	        dto.setEmail(u.getEmail());
	        dto.setTelephone(u.getTelephone());
	        dto.setRole(u.getRole());
	        dto.setStatut_user(u.isStatut_user());
	        dtos.add(dto);
	    }
	    return dtos;
	}
	

    //5- afficher un User
    public UserResponseDto findUserById(Long userId) {

	    User user = userDao.findOne(userId);
        
        if (user == null) {
            throw new EntityNotFoundException("User introuvable");
        }

        UserResponseDto dto = new UserResponseDto();
        dto.setId(user.getUserId());
        dto.setNom(user.getNom());
        dto.setPrenom(user.getNom());
        dto.setEmail(user.getEmail());
        dto.setTelephone(user.getTelephone());
        dto.setRole(user.getRole());
        dto.setStatut_user(user.isStatut_user());

        return dto;
    }
}
