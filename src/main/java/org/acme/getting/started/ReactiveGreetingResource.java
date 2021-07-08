package org.acme.getting.started;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.smallrye.common.annotation.Blocking;

import org.eclipse.microprofile.faulttolerance.Retry;

@Path("/hello")
@Blocking
public class ReactiveGreetingResource {

    @Inject
    ReactiveGreetingService service;

    //TODO Comment this out to get rid of FaultToleranceDefinitionException
    @Retry(maxRetries = 5, delay = 5)
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/greeting/{name}")
    public String greeting(@PathParam("name") String name) {
        return service.greeting(name);
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "hello";
    }
}
