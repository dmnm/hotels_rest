package org.example.rest.api;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.example.rest.entity.Reservation;
import org.example.rest.entity.Room;
import org.example.rest.service.ReservationService;
import org.example.rest.service.RoomService;

@Named
@Path("hotels/{hotelId}/rooms")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RoomResource {
    @Inject
    private RoomService delegate;

    @Inject
    private ReservationService reservationService;

    @GET
    public List<Room> getAll(@PathParam("hotelId") final Long hotelId) {
        return delegate.findByHotelId(hotelId);
    }

    @GET
    @Path("{roomId}")
    public Response findById(@PathParam("hotelId") final Long hotelId, @PathParam("roomId") final Long roomId) {
        return Response.ok().entity(delegate.findById(roomId)).build();
    }

    @POST
    @Path("{roomId}/reserve")
    public Response reserve(@Valid final Reservation data) {
        reservationService.reserve(data);
        return Response.status(Status.CREATED).entity(data).build();
    }

    @POST
    @RolesAllowed("admin")
    public Response create(@PathParam("hotelId") final Long hotelId, final Room room) {
        room.setId(null);
        return Response.status(Status.CREATED).entity(delegate.add(hotelId, room)).build();
    }

    @PUT
    @Path("{roomId}")
    @RolesAllowed("admin")
    public Response update(@PathParam("roomId") final Long roomId, final Room room) {
        room.setId(roomId);
        if (delegate.update(room) != null) {
            return Response.ok().entity(room).build();
        } else {
            return Response.status(Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("{roomId}")
    @RolesAllowed("admin")
    public Response delete(@PathParam("roomId") final Long roomId) {
        if (delegate.delete(roomId)) {
            return Response.ok().build();
        } else {
            return Response.status(Status.NOT_FOUND).build();
        }
    }

}
