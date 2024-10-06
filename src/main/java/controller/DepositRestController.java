package controller;

import DTO.CardDTO;
import DTO.CustomerDTO;
import DTO.DepositDTO;
import entity.Customer;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import service.DepositService;

import java.util.List;

@Path("/deposits")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DepositRestController {

    @Inject
    private DepositService depositService;

    @POST
    public Response createDeposit(DepositDTO depositDTO) {
        DepositDTO createdDeposit = depositService.createDeposit(depositDTO);
        return Response.status(Response.Status.CREATED).entity(createdDeposit).build();
    }

    @GET
    @Path("/{id}")
    public Response getDeposit(@PathParam("id") Integer id) {
        DepositDTO depositDTO = depositService.findDepositById(id);
        if (depositDTO != null) {
            return Response.ok(depositDTO).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("/customers/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CustomerDTO> getCustomers(@PathParam("id") Integer id) {  // Return DTO list
        return depositService.findCustomers(id);
    }

    @GET
    @Path("/cards/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CardDTO> getCards(@PathParam("id") Integer id) {  // Return DTO list
        return depositService.findCards(id);
    }

    @GET
    @Path("/customersByDepositNumber/{depositnumber}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomersByDepositNumber(@PathParam("depositnumber") String depositnumber) {
        List<CustomerDTO> customerDTOS = depositService.findCustomersByDepositNumber(depositnumber);  // Use DTOs
        if (!customerDTOS.isEmpty()) {
            return Response.ok(customerDTOS).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    public List<DepositDTO> getAllDeposits() {
        return depositService.findAllDeposits();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteDeposit(@PathParam("id") Integer id) {
        if (depositService.deleteDeposit(id)) {
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateDeposit(@PathParam("id") Integer id, DepositDTO depositDTO) {
        depositDTO.setDepositId(id); // Ensure the ID is set for the update
        DepositDTO updatedDeposit = depositService.updateDeposit(depositDTO);
        return Response.ok(updatedDeposit).build();
    }

    @GET
    @Path("/count")
    public Response getCount() {
        long count = depositService.countDeposits();
        return Response.ok(count).build();
    }

    @GET
    @Path("/depositnumber/{depositnumber}")
    public Response findByDepositNumber(@PathParam("depositnumber") String depositNumber) {
        List<DepositDTO> deposits = depositService.findByDepositNumber(depositNumber);
        if (!deposits.isEmpty()) {
            return Response.ok(deposits).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}