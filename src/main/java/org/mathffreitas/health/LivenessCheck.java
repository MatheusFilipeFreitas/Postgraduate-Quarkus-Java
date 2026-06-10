package org.mathffreitas.health;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

@Liveness
public class LivenessCheck implements HealthCheck {
    private final String NAME = "App LivenessCheck";
    @Override
    public HealthCheckResponse call() {
        return HealthCheckResponse.named(NAME).up().build();
    }
}
