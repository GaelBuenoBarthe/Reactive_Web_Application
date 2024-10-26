package diginamic.m052024.reactive_web_application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
public class SenderController {

    private final WebClient webClient;

    // Inject a WebClient.Builder instance
    @Autowired
    public SenderController(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8080").build();
    }

    // Send a request to the /start-task endpoint
    @GetMapping("/send-request")
    public Mono<String> sendRequest() {
        // Send a GET request to the /start-task endpoint
        return webClient.get()
                .uri("/start-task")
                .retrieve()
                .bodyToMono(String.class)
                // Log the response
                .map(response -> "Receiver response: " + response);
    }
}