package mindswap.academy.moviereview_api.controller.actuator;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.boot.actuate.health.Status;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class ActuatorController {
    private final HealthEndpoint healthEndpoint;
    @Scheduled(fixedRate = 300000)
    public void healthCheck(){
        Status status = healthEndpoint.health().getStatus();
        System.out.println(status);
    }
}
