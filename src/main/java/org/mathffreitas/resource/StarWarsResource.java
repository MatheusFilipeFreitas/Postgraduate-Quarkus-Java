package org.mathffreitas.resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.mathffreitas.service.StarWarService;

@Path("starwars")
@Produces(MediaType.APPLICATION_JSON)
public class StarWarsResource {
    @RestClient
    private StarWarService service;

    @GET
    @Path("starships")
    public String getStarShips() {
        return service.getStarShips();
    }
}
