package org.mathffreitas.resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/unipds")
// default config
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UnipdsResource {
    private int i = 0;

    @GET
    // specific config
    @Produces(MediaType.TEXT_PLAIN)
    public Integer get() {
        return i;
    }

    @POST
    public void set() {
        this.i++;
    }

    @DELETE
    public void delete() {
        this.i--;
    }

    @PUT
    public void put(Integer i) {
        this.i = i;
    }

    @GET
    @Path("/even")
    public Integer getIfEven() {
        return this.i % 2 == 0 ? this.i : 0;
    }
}
