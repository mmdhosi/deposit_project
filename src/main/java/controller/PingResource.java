package controller;


import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("ping")
public class PingResource {

    @GET
    public String ping() {
        return "Hello world! Enjoy Java EE 8!";
    }

}
