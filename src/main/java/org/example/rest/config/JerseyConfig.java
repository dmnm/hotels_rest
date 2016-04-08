package org.example.rest.config;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import org.example.rest.api.HotelResource;
import org.example.rest.api.RoomResource;
import org.example.rest.web.CorsFilter;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Named
@ApplicationPath("api")
public class JerseyConfig extends ResourceConfig {
    @PostConstruct
    public void registerResources() {
        register(HotelResource.class);
        register(RoomResource.class);
        register(JacksonContextResolver.class);
        register(RolesAllowedDynamicFeature.class);
        register(CorsFilter.class);
    }

    @Provider
    @Produces(MediaType.APPLICATION_JSON)
    static class JacksonContextResolver implements ContextResolver<ObjectMapper> {
        private final ObjectMapper mapper;

        public JacksonContextResolver() {
            mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                    .configure(SerializationFeature.INDENT_OUTPUT, true);
        }

        @Override
        public ObjectMapper getContext(final Class<?> objectType) {
            return mapper;
        }
    }
}
