package mindswap.academy.moviereview_api.controller.actuator;

import io.swagger.v3.core.util.Json;
import lombok.*;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.boot.actuate.health.Status;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class ActuatorController {
    RestTemplate restTemplate = new RestTemplate();
    @Scheduled(initialDelay = 10000,fixedRate = 300000)
    public void healthCheck(){
        Response response = restTemplate.getForObject("https://movie-review-api.onrender.com/actuator/health", Response.class);
        assert response != null;
        System.out.println(response.status);
    }
    @ToString
    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    static class Response{
        String status;
    }
}
