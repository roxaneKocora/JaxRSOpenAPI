package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.dao.ClientDao;
import fr.istic.taa.jaxrs.dao.UserDao;
import fr.istic.taa.jaxrs.dto.InscriptionClientDto;
import fr.istic.taa.jaxrs.dto.UserResponseDto;
import fr.istic.taa.jaxrs.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("client")
@Produces({"application/json", "application/xml"})
@Tag(name = "Client", description = "Gestion des Clients")
public class ClientResource {

    private ClientService clientService;

    public ClientResource() {
        ClientDao clientDao = new ClientDao();
        UserDao userDao = new UserDao();
        this.clientService = new ClientService(clientDao, userDao);
    }
    

	
	@POST
	@Path("/inscription")
	@Consumes("application/json")
	@Operation(summary = "S'inscrire",  description = "Permet à un utilisateur de S'inscrire")
	@ApiResponses({
	    @ApiResponse(responseCode = "201", description = "Inscrition réussie",
	        content = @Content(schema = @Schema(implementation = UserResponseDto.class))),
	    @ApiResponse(responseCode = "400", description = "Email déjà utilisé")
	})
	public Response inscription(
	        @RequestBody(
	                required = true,
	                content = @Content(schema = @Schema(implementation = InscriptionClientDto.class))
	            )
	        InscriptionClientDto dto) {
		
			  try {
				  UserResponseDto response = clientService.inscription(dto);
				  return Response.status(Response.Status.CREATED).entity(response).build();
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
				return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
			}
	}

}




