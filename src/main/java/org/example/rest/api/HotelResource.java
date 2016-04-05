package org.example.rest.api;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.example.rest.entity.Hotel;
import org.example.rest.service.HotelService;

@Named
@Path("/hotels")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class HotelResource {

    @Inject
    private HotelService delegate;

    @GET
    public List<Hotel> getAll() {
        return delegate.getAll();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") final Long id) {
        return Response.ok().entity(delegate.findById(id)).build();
    }

}
