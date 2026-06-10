package org.mathffreitas.service;

// swapi api

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.mathffreitas.constants.Constants;

import java.time.temporal.ChronoUnit;

@RegisterRestClient (baseUri = "https://swapi.info/api/")
@Produces(MediaType.APPLICATION_JSON)
public interface StarWarService {

    @GET
    @Path("starships")
    @Timeout(
            value = 3,
            unit = ChronoUnit.SECONDS
    )
    @CircuitBreaker(
            requestVolumeThreshold = 2, //analyze the requests (default: 20)
            failureRatio = .5, //percentage of failure to break (default: 0.50 - 50% but analyze your case)
            delay = 3000L, //time to check again (in this case 3 seconds)
            successThreshold =  2 //how many requests works for turn on the circuit
    )
    @Fallback(
            fallbackMethod = "getStarShipsFallback"
    )
    String getStarShips();

    // need to be the same return and the same number of params
    // in this fallback method
    default String getStarShipsFallback() {
        return Constants.FALLBACK_MESSAGE;
    }
}
