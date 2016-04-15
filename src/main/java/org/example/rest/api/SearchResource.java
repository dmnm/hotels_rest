package org.example.rest.api;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.example.rest.dto.SearchRequest;
import org.example.rest.entity.Room;
import org.example.rest.service.SearchService;

@Named
@Path("search")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SearchResource {

    @Inject
    private SearchService delegate;

    @GET
    public Response search(@BeanParam final SearchRequest data) {
        final List<Room> list = delegate.search(data);
        if (list == null || list.isEmpty()) {
            return Response.status(Status.NOT_FOUND).build();
        }
        return Response.ok().entity(list).build();
    }
}
