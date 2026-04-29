package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.dao.ManagerDao;
import fr.istic.taa.jaxrs.dao.UserDao;
import fr.istic.taa.jaxrs.dto.EnregistrerManagerDto;
import fr.istic.taa.jaxrs.dto.UserResponseDto;
import fr.istic.taa.jaxrs.service.ManagerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;


@Path("manager")
@Produces({"application/json", "application/xml"})
@Tag(name = "Manager", description = "Gestion des Manager")
public class ManagerResource {

    private ManagerService managerService;

    public ManagerResource() {
    	ManagerDao managerDao = new ManagerDao();
        UserDao userDao = new UserDao();
        this.managerService = new ManagerService(managerDao, userDao);
    }
    

	
	@POST
	@Path("/inscription")
	@Consumes("application/json")
	@Operation(summary = "Ajouter Manager", description = "Enregistrer un Manager")
	@ApiResponses({
	    @ApiResponse(responseCode = "201", description = "Ajout réussie",
	        content = @Content(schema = @Schema(implementation = UserResponseDto.class))),
	    @ApiResponse(responseCode = "400", description = "Email déjà utilisé")
	})
	public Response ajouterManager(
//		    @RequestBody(
//		            description = "body de l'api",
//		            required = true,
//		            content = @Content(schema = @Schema(implementation = InscriptionDto.class))
//		        )
			EnregistrerManagerDto dto) {
		  try {
			  UserResponseDto response = managerService.ajouterManager(dto);
			  return Response.status(Response.Status.CREATED).entity(response).build();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

}
