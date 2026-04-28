package fr.istic.taa.jaxrs.rest;


import fr.istic.taa.jaxrs.dao.ClientDao;
import fr.istic.taa.jaxrs.dao.EventDao;
import fr.istic.taa.jaxrs.dao.TicketDao;
import fr.istic.taa.jaxrs.domain.Ticket;
import fr.istic.taa.jaxrs.dto.AchatTicketDto;
import fr.istic.taa.jaxrs.dto.EventDto;
import fr.istic.taa.jaxrs.service.TicketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("ticket")
@Produces({"application/json", "application/xml"})
@Tag(name = "Ticket", description = "Gestion des tickets")
public class TicketResource {
	
    private TicketService ticketService;

    public TicketResource() {
    	ClientDao clientDao = new ClientDao();
    	EventDao eventDao = new EventDao();
    	TicketDao ticketDao = new TicketDao();
        this.ticketService = new TicketService(clientDao, eventDao, ticketDao);
    }   
    

    //Acheter un ticket
	@POST
	@Path("/acheter")
	@Consumes("application/json")
    @Operation(
            summary = "Acheter un ticket",
            description = "Permet à un client d'acheter un ticket pour un événement"
        )
        @ApiResponses({
            @ApiResponse(
                responseCode = "201",
                description = "Ticket acheté avec succès",
                content = @Content(schema = @Schema(implementation = Ticket.class))
            ),
            @ApiResponse(
                responseCode = "400",
                description = "Erreur lors de l'achat (Plus de places, Vous avez déjà un ticket pour cet event ou client introuvable...)",
                content = @Content(schema = @Schema(implementation = String.class))
            )
        })
	public Response acheterTicket(
		    @RequestBody(
		            description = "Email et mot de passe de l'utilisateur",
		            required = true,
		            content = @Content(schema = @Schema(implementation = AchatTicketDto.class))
		        )
		    AchatTicketDto dto) {
		  try {
			  Ticket ticket = ticketService.acheterTicket(dto);
			  return Response.status(Response.Status.CREATED).entity(ticket).build();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}
    
	

    //liste des tickets par client
    @GET
    @Path("/all/{clientId}")
    @Operation(
            summary = "Récupérer les tickets d'un client",
            description = "Retourne la liste de tous les tickets achetés par un client"
        )
        @ApiResponses({
            @ApiResponse(
                responseCode = "200",
                description = "Liste des tickets du client",
                content = @Content(schema = @Schema(implementation = Ticket.class))
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Client introuvable"
            )
        })
    public Response findEventsByManagerId(
            @Parameter(description = "ID du manager", required = true)
            @PathParam("clientId") Long clientId) {

        try {
        	return Response.ok(ticketService.findTicketsByClientId(clientId)).build();
            
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND)
                           .entity(e.getMessage())
                           .build();
        }
    }
    	

}
