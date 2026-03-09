package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.dao.UserDao;
import fr.istic.taa.jaxrs.domain.User;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

@Path("/user")
@Produces({"application/json", "application/xml"})
public class UserResource {
	
	UserDao user = new UserDao();

  @GET
  @Path("/{id}")
  public User getUserById(@PathParam("id") Long userId)  {
	  try {
	        return user.findOne(userId);
	    } catch (Exception e) {
	        // Cette ligne va FORCER l'erreur à s'afficher en rouge dans votre console
	        e.printStackTrace(); 
	        throw e; // On relance l'erreur pour garder le comportement d'origine
	    }
  }

//  @GET
//  @Path("/")
//  public Pet getPet(Long petId)  {
//      return new Pet();
//  }
//
//  
//  @POST
//  @Consumes("application/json")
//  public Response addPet(
//      @Parameter(description = "Pet object that needs to be added to the store", required = true) Pet pet) {
//    // add pet
//    return Response.ok().entity("SUCCESS").build();
//  }
}