package controller;

import DTO.CardDTO; // Importing the DTO
import DTO.CustomerDTO;
import DTO.DepositDTO;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import service.CardService;

import java.util.List;

@Path("/cards")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CardRestController {

    @Inject
    private CardService cardService;

    @POST
    public Response createCard(CardDTO cardDTO) { // Use CardDTO instead of Card entity
        CardDTO createdCard = cardService.createCard(cardDTO); // Create card using CardDTO
        return Response.status(Response.Status.CREATED).entity(createdCard).build();
    }

    @GET
    @Path("/{id}")
    public Response getCard(@PathParam("id") Integer id) {
        var cardDTO = cardService.findCardById(id);
        if (cardDTO.isPresent()) { // Check if card is present
            return Response.ok(cardDTO.get()).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    public Response getAllCards() {
        List<CardDTO> cardDTOs = cardService.findAllCards(); // Use DTO list
        return Response.ok(cardDTOs).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteCard(@PathParam("id") Integer id) {
        if (cardService.deleteCard(id)) {
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateCard(@PathParam("id") Integer id, CardDTO cardDTO) { // Use CardDTO
        cardDTO.setCardId(id); // Ensure the ID is set for the update
        cardService.updateCard(cardDTO);
        return Response.ok(cardDTO).build();
    }

    @GET
    @Path("/count")
    public Response getCount() {
        long count = cardService.countCards();
        return Response.ok(count).build();
    }

    @GET
    @Path("/cardnumber/{cardNumber}")
    public Response findByCardNumber(@PathParam("cardNumber") String cardNumber) {
        List<CardDTO> cards = cardService.findByCardNumber(cardNumber); // Use DTO
        if (!cards.isEmpty()) { // Check if cards list is not empty
            return Response.ok(cards).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
    @GET
    @Path("/deposit/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public DepositDTO getDeposit(@PathParam("id") Integer id) {  // Return DTO list
        return  cardService.findDeposit(id);
    }

    @GET
    @Path("/customers/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CustomerDTO> getCustomer(@PathParam("id") Integer id) {  // Return DTO list
        return  cardService.findCustomers(id);
    }
}