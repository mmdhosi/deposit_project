package controller;

import DTO.CardDTO;
import DTO.CustomerDTO;
import DTO.DepositDTO;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import service.CustomerService;

import java.util.List;

@Path("/customers")
public class CustomerController {

    @Inject
    CustomerService customerService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCustomer(CustomerDTO customerDTO) {
        CustomerDTO createdCustomer = customerService.save(customerDTO);  // Use DTO for saving
        return Response.status(Response.Status.CREATED).entity(createdCustomer).build();
    }

//    @GET
//    @Path("{id}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getCustomer(@PathParam("id") Integer id) {
//        return customerService.findById(id)
//                .map(customer -> Response.ok(customer).build())
//                .orElse(Response.status(Response.Status.NOT_FOUND).build());
//    }
    @GET
    @Path("/{id}")
    public Response getCustomer(@PathParam("id") Integer id) {
        CustomerDTO customerDTO = customerService.findById(id);
        if (customerDTO != null) {
            return Response.ok(customerDTO).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<CustomerDTO> getAllCustomers() {  // Return DTO list
        return customerService.findAll();
    }

    @GET
    @Path("/deposits/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<DepositDTO> getDeposits(@PathParam("id") Integer id) {  // Return DTO list
        return customerService.findDeposits(id);
    }

    @GET
    @Path("/cards/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<CardDTO> getCards(@PathParam("id") Integer id) {  // Return DTO list
        return customerService.findCards(id);
    }
    @GET
    @Path("/depositsByCustomerNumber/{customernumber}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDepositsByCustomerNumber(@PathParam("customernumber") String customerNumber) {
        List<DepositDTO> depositDTOS = customerService.findDepositsByCustomerNumber(customerNumber);  // Use DTOs
        if (!depositDTOS.isEmpty()) {
            return Response.ok(depositDTOS).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }


    @DELETE
    @Path("{id}")
    public Response deleteCustomer(@PathParam("id") Integer id) {
        if (customerService.deleteById(id)) {
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("/count")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCount() {
        long count = customerService.count();
        return Response.ok(count).build();
    }

    @GET
    @Path("/exists/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response exists(@PathParam("id") Integer id) {
        boolean exists = customerService.existsById(id);
        return Response.ok(exists).build();
    }

    @GET
    @Path("/customernumber/{customernumber}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByCustomerNumber(@PathParam("customernumber") String customerNumber) {
        List<CustomerDTO> customers = customerService.findByCustomerNumber(customerNumber);  // Use DTOs
        if (!customers.isEmpty()) {  // Check if list is not empty
            return Response.ok(customers).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("/email/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByEmail(@PathParam("email") String email) {
        List<CustomerDTO> customers = customerService.findByEmail(email);  // Use DTOs
        if (!customers.isEmpty()) {  // Check if list is not empty
            return Response.ok(customers).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("/phone/{phone}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByPhone(@PathParam("phone") String phone) {
        List<CustomerDTO> customers = customerService.findByPhoneNumber(phone);  // Use DTOs
        if (!customers.isEmpty()) {  // Check if list is not empty
            return Response.ok(customers).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}