package org.example.rest.api;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.example.rest.entity.Hotel;
import org.example.rest.service.HotelService;

@Named
@Path("hotels")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class HotelResource {

    @Inject
    private HotelService delegate;

    @GET
    public Response getAll(@QueryParam("offset") final Integer offset, @QueryParam("limit") final Integer limit) {
        final List<Hotel> list = delegate.getAll(offset, limit);
        if (list == null || list.isEmpty()) {
            return Response.status(Status.NOT_FOUND).build();
        }
        return Response.ok().entity(list).build();
    }

    @GET
    @Path("{id}")
    public Response findById(@PathParam("id") final Long id) {
        return Response.ok().entity(delegate.findById(id)).build();
    }

    @DELETE
    @Path("{id}")
    @RolesAllowed("admin")
    public Response delete(@PathParam("id") final Long id) {
        if (delegate.delete(id)) {
            return Response.ok().build();
        } else {
            return Response.status(Status.NOT_FOUND).build();
        }
    }

    @POST
    // @RolesAllowed("admin")
    public Response create(final Hotel hotel) {
        final Long id = delegate.add(hotel);
        return Response.status(Status.CREATED).entity(id).build();
    }

    @PUT
    @Path("{id}")
    // @RolesAllowed("admin")
    public Response update(@PathParam("id") final Long id, final Hotel hotel) {
        hotel.setId(id);
        if (delegate.update(hotel) != null) {
            return Response.ok().build();
        } else {
            return Response.status(Status.NOT_FOUND).build();
        }
    }

}
