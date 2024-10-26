package diginamic.m052024.reactive_web_application.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.time.Duration;

@RestController
public class ReceiverController {

    private static final Logger logger = LoggerFactory.getLogger(ReceiverController.class);
    private final Sinks.Many<String> sink;
    private volatile boolean stopRequested = false;

    public ReceiverController() {
        this.sink = Sinks.many().multicast().onBackpressureBuffer();
    }

    // Endpoint to start the task
    @GetMapping("/start-task")
    public Flux<String> startTask() {
        stopRequested = false;
        performTask();
        return sink.asFlux();
    }

    // Endpoint to stop the task
    @GetMapping("/stop-task")
    public Flux<String> stopTask() {
        stopRequested = true;
        sink.tryEmitNext("Process stopped by user");
        sink.tryEmitComplete();
        return sink.asFlux();
    }

    // Method to perform the task
    public void performTask() {
        new Thread(() -> {
            long startTime = System.currentTimeMillis();
            try {
                for (int i = 0; i < 1000; i++) {
                    if (stopRequested) {
                        return;
                    }
                    sink.tryEmitNext("Iteration: " + (i + 1));
                    Thread.sleep(1000); // Simulate delay
                }
                long elapsedTime = (System.currentTimeMillis() - startTime) / 1000;
                String message = "Task completed in " + elapsedTime + " seconds";
                logger.info(message);
                sink.tryEmitNext(message);
                sink.tryEmitComplete();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                sink.tryEmitNext("Error performing the task");
                sink.tryEmitComplete();
            } catch (Exception e) {
                sink.tryEmitNext("Error performing the task");
                sink.tryEmitComplete();
            }
        }).start();
    }
}