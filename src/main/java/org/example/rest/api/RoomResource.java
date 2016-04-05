package org.example.rest.api;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.example.rest.entity.Room;
import org.example.rest.service.RoomService;

@Named
@Path("/rooms")
@Produces(MediaType.APPLICATION_JSON)
public class RoomResource {

    @Inject
    private RoomService delegate;

    @GET
    public List<Room> getAll() {
        return delegate.getAll();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") final Long id) {
        return Response.ok().entity(delegate.findById(id)).build();
    }

}
