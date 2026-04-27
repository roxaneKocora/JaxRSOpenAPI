package fr.istic.taa.jaxrs.rest;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import fr.istic.taa.jaxrs.dao.EventDao;
import fr.istic.taa.jaxrs.dao.ManagerDao;
import fr.istic.taa.jaxrs.domain.Event;
import fr.istic.taa.jaxrs.dto.ConnexionDto;
import fr.istic.taa.jaxrs.dto.EventDto;
import fr.istic.taa.jaxrs.dto.EventReponseDto;
import fr.istic.taa.jaxrs.dto.UserResponseDto;
import fr.istic.taa.jaxrs.service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;


@Path("event")
@Produces({"application/json", "application/xml"})
@Tag(name = "Event", description = "Gestion des evenements (concerts)")
public class EventResource {
	
    private EventService eventService;

    public EventResource() {
    	EventDao eventDao = new EventDao();
    	ManagerDao managerDao = new ManagerDao();
        this.eventService = new EventService(managerDao, eventDao);
    } 
    
    //Créer un événement
    @POST
    @Path("/create")
	@Consumes("application/json")
    @Operation(summary = "Créer un événement")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Événement créé",
            content = @Content(schema = @Schema(implementation = EventReponseDto.class))),
        @ApiResponse(responseCode = "400", description = "Manager introuvable ou Données invalides")
    })
    public Response creerEvent(
		    @RequestBody(
		            description = "Email et mot de passe de l'utilisateur",
		            required = true,
		            content = @Content(schema = @Schema(implementation = EventDto.class))
		        )
		    EventDto dto) {
    	    	
	        try {
	        	EventReponseDto newEvent = eventService.creerEvent(dto);
	            return Response.status(Response.Status.CREATED)
	                           .entity(newEvent)
	                           .build();
	        }catch (Exception e) {
	            return Response.status(Response.Status.BAD_REQUEST)
                        .entity(e.getMessage())
                        .build();
	        }
    }
    
    
    //Événements par manager
    @GET
    @Path("/all/{managerId}")
    @Operation(summary = "Événements par manager")
    @ApiResponses({
    	@ApiResponse(responseCode = "200", description = "Liste des événements du manager",
		        content = @Content(schema = @Schema(implementation = EventReponseDto.class))),
    	@ApiResponse(responseCode = "404", description = "Manager introuvable")
    })
    public Response findEventsByManagerId(
            @Parameter(description = "ID du manager", required = true)
            @PathParam("managerId") Long manager_Id) {
    	
	        try {
	        	return Response.ok(eventService.findEventsByManagerId(manager_Id)).build();
	            
	        } catch (Exception e) {
	            return Response.status(Response.Status.NOT_FOUND)
	                           .entity(e.getMessage())
	                           .build();
	        }
        
    }
    
    
    //Événements par date
    @GET
    @Path("all/date")
    @Operation(summary = "Événements par date")
    @ApiResponses({
    	@ApiResponse(responseCode = "200", description = "Liste des événements",
		        content = @Content(schema = @Schema(implementation = EventReponseDto.class))),
    	@ApiResponse(responseCode = "400", description = "Date obligatoire ou format invalide (attendu: YYYY-MM-DD)")
    })
    public Response findEventsByDate(
            @Parameter(description = "Date au format YYYY-MM-DD", required = true)
            @QueryParam("date") String dateStr) {
    	
        try {
            if (dateStr == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                               .entity("La date est obligatoire")
                               .build();
            }
            LocalDate date = LocalDate.parse(dateStr);
            return Response.ok(eventService.findEventsByDate(date)).build();
            
        } catch (DateTimeParseException e) {
            return Response.status(Response.Status.NOT_FOUND)
                           .entity("Format de date invalide, utilisez YYYY-MM-DD")
                           .build();
        }
    }
    

}
