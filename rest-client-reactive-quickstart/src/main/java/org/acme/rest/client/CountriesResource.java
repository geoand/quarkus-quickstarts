package org.acme.rest.client;

import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.time.Duration;
import java.util.Set;

@Path("/country")
public class CountriesResource {

    private static final Logger log = Logger.getLogger(CountriesResource.class.getName());

    @RestClient
    CountriesService countriesService;

    @GET
    @Path("/name-uni/{countryName}")
    public Uni<Country> nameUni(String countryName) {
        log.info("Received request from browser");
        return countriesService.getByNameAsUni(countryName).onFailure().retry()
                .atMost(3);
    }
}
