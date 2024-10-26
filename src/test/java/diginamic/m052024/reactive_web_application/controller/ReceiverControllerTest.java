package diginamic.m052024.reactive_web_application.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

@WebFluxTest(ReceiverController.class)
public class ReceiverControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    // Set up the WebTestClient before each test
    @BeforeEach
    void setUp() {
        webTestClient = WebTestClient.bindToController(new ReceiverController()).build();
    }

    // Test the /start-task endpoint
    @Test
    void testStartTask() {
        webTestClient.get().uri("/start-task")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Flux.class);
    }

    // Test the /stop-task endpoint
    @Test
    void testStopTask() {
        webTestClient.get().uri("/stop-task")
                .exchange()
                .expectStatus().isOk();
    }
}