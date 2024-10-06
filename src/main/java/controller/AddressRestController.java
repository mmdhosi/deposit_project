package controller;

import DTO.AddressDTO; // Ensure you import the correct DTO
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import service.AddressService;

import java.util.List;
import java.util.Optional;

@Path("/addresses")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AddressRestController {

    @Inject
    private AddressService addressService;

    @POST
    public Response createAddress(AddressDTO addressDTO) {
        AddressDTO createdAddress = addressService.createAddress(addressDTO); // Use AddressDTO instead of Address
        return Response.status(Response.Status.CREATED).entity(createdAddress).build(); // Return the created DTO
    }

    @GET
    @Path("/{id}")
    public Response getAddress(@PathParam("id") Integer id) {
        Optional<AddressDTO> addressDTO = addressService.findAddressById(id);
        if (addressDTO.isPresent()) {
            return Response.ok(addressDTO.get()).build(); // Return found DTO
        }
        return Response.status(Response.Status.NOT_FOUND).build(); // Return NOT FOUND if address is not present
    }

    @GET
    public List<AddressDTO> getAllAddresses() {
        return addressService.findAllAddresses(); // Return list of AddressDTO
    }

    @DELETE
    @Path("/{id}")
    public Response deleteAddress(@PathParam("id") Integer id) {
        if (addressService.deleteAddress(id)) {
            return Response.noContent().build(); // Return NO CONTENT if deletion was successful
        }
        return Response.status(Response.Status.NOT_FOUND).build(); // Return NOT FOUND if the address was not found
    }

    @PUT
    @Path("/{id}")
    public Response updateAddress(@PathParam("id") Integer id, AddressDTO addressDTO) {
        // Set the ID from the path parameter to ensure the correct address is updated
        addressDTO.setAddressId(id); // Assuming there's a method to set the ID
        addressService.updateAddress(addressDTO);
        return Response.ok(addressDTO).build(); // Return the updated address DTO
    }

    @GET
    @Path("/search")
    public List<AddressDTO> searchAddresses(@QueryParam("city") String city,
                                            @QueryParam("country") String country,
                                            @QueryParam("postalCode") String postalCode) {
        if (city != null && country != null) {
            return addressService.findByCityAndCountry(city, country); // Return results for city and country
        } else if (postalCode != null) {
            return addressService.findByPostalCode(postalCode); // Return results for postal code
        }
        return List.of(); // Return an empty list if no parameters are provided
    }

    @GET
    @Path("/count")
    public Response getCount() {
        long count = addressService.countAddresses();
        return Response.ok(count).build(); // Return the count of addresses
    }
}