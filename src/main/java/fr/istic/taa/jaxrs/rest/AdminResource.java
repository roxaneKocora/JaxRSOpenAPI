package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.dao.AdminDao;
import fr.istic.taa.jaxrs.dao.ManagerDao;
import fr.istic.taa.jaxrs.dao.UserDao;
import fr.istic.taa.jaxrs.domain.User;
import fr.istic.taa.jaxrs.dto.EnregistrerAdminDto;
import fr.istic.taa.jaxrs.dto.UserResponseDto;
import fr.istic.taa.jaxrs.service.AdminService;
import fr.istic.taa.jaxrs.service.ManagerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;


@Path("admin")
@Produces({"application/json", "application/xml"})
@Tag(name = "Admin", description = "Gestion des Admin")
public class AdminResource {

    private AdminService adminService;

    public AdminResource() {
        AdminDao adminDao = new AdminDao();
        UserDao userDao = new UserDao();
        this.adminService = new AdminService(adminDao, userDao);
    }
    
    @POST
	@Path("/ajouter")
	@Consumes("application/json")
	@Operation(summary = "Ajouter Admin", description = "Enregistrer un Admin")
	@ApiResponses({
	    @ApiResponse(responseCode = "201", description = "Ajout réussie",
	        content = @Content(schema = @Schema(implementation = UserResponseDto.class))),
	    @ApiResponse(responseCode = "400", description = "Email déjà utilisé")
	})
	public Response ajouterManager(
		    @RequestBody(
		            description = "body de l'api",
		            required = true,
		            content = @Content(schema = @Schema(implementation = EnregistrerAdminDto.class))
		        )
		    EnregistrerAdminDto dto) {
		  try {
			  UserResponseDto response = adminService.ajouterAdmin(dto);
			  return Response.status(Response.Status.CREATED).entity(response).build();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}


	  //liste des utilisateurs
	    @GET
	    @Path("/all")
	    @Operation(summary = "Lister tous les Admins", description = "Retourne la liste de tous les Admins")
	    @ApiResponses({
	        @ApiResponse(responseCode = "200", description = "Admins trouvés avec succes",
	            content = @Content(schema = @Schema(implementation = UserResponseDto.class)))
	    })
	    public Response listeUtilisateurs() {
	        return Response.ok(adminService.listeAdmins()).build();
	    }

}
