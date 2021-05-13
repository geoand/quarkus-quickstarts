package org.acme.rest.client;

import io.smallrye.mutiny.Uni;
import org.jboss.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/dummy/v2")
public class DummyCountryResource {

    private static final Logger log = Logger.getLogger(DummyCountryResource.class.getName());

    @GET
    @Path("/name/{countryName}")
    public Uni<Country> byName(String countryName) {
        log.info("Received request from client");

        Country result = new Country();
        result.name = countryName;
        result.capital = countryName + "Capital";
//        return Uni.createFrom().item(result);

        return Uni.createFrom().failure(new RuntimeException("Failed DummyCountryResource for " + countryName));
    }
}
