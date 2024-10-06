package controller;

import entity.CustomerDeposit;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import service.CustomerDepositService;

import java.util.List;

@Path("/customer-deposits")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerDepositRestController {

    @Inject
    private CustomerDepositService customerDepositService;

    @POST
    public Response createCustomerDeposit(CustomerDeposit customerDeposit) {
        CustomerDeposit createdCustomerDeposit = customerDepositService.createCustomerDeposit(customerDeposit);
        return Response.status(Response.Status.CREATED).entity(createdCustomerDeposit).build();
    }

    @GET
    @Path("/{id}")
    public Response getCustomerDeposit(@PathParam("id") Integer id) {
        CustomerDeposit customerDeposit = customerDepositService.findCustomerDepositById(id);
        if (customerDeposit != null) {
            return Response.ok(customerDeposit).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    public List<CustomerDeposit> getAllCustomerDeposits() {
        return customerDepositService.findAllCustomerDeposits();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteCustomerDeposit(@PathParam("id") Integer id) {
        if (customerDepositService.deleteCustomerDeposit(id)) {
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateCustomerDeposit(@PathParam("id") Integer id, CustomerDeposit customerDeposit) {
        customerDeposit.setCustomerDepositId(Math.toIntExact(id));  // Ensure the ID is set for the update
        customerDepositService.updateCustomerDeposit(customerDeposit);
        return Response.ok(customerDeposit).build();
    }
    @GET
    @Path("/count")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCount() {
        long count = customerDepositService.countCustomerDeposits();
        return Response.ok(count).build();
    }
}