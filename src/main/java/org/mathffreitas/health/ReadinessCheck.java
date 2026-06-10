package org.mathffreitas.health;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.mathffreitas.constants.Constants;
import org.mathffreitas.service.StarWarService;

@Readiness
public class ReadinessCheck implements HealthCheck {
    private final String NAME = "StarWars ReadinessCheck";
    @RestClient
    private StarWarService service;

    @Override
    public HealthCheckResponse call() {
        if (service.getStarShips().contains(Constants.FALLBACK_MESSAGE)) {
            return HealthCheckResponse.down(NAME);
        }
        return HealthCheckResponse.up(NAME);
    }
}
