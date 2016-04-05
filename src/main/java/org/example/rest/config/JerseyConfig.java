package org.example.rest.config;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.ws.rs.ApplicationPath;

import org.example.rest.api.RoomResource;
import org.glassfish.jersey.server.ResourceConfig;

@Named
@ApplicationPath("/api")
public class JerseyConfig extends ResourceConfig {
    @PostConstruct
    public void registerResources() {
        register(RoomResource.class);
    }
}
