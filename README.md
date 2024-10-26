# Reactive Web Application

## Overview

This project is a reactive web application built using Java, Spring Boot, and Maven. It demonstrates the use of reactive programming principles to handle asynchronous tasks and real-time updates.

## Features

- **Reactive Task Execution**: Start and stop long-running tasks with real-time updates.
- **Event Streaming**: Use Server-Sent Events (SSE) to stream task progress to the client.
- **Responsive UI**: A simple and responsive user interface built with Tailwind CSS.

## Prerequisites

- Java 11 or higher
- Maven 3.6.3 or higher

## Getting Started

### Clone the Repository

```bash
git clone https://github.com/GaelBuenoBarthe/Reactive_Web_Application.git
cd Reactive_Web_Application
```
## Build the Project

```bash
mvn clean install
```
## Run the Application

```bash
mvn spring-boot:run
```
The application will be accessible at `http://localhost:8080`.

## Endpoints

### Start Task

- **URL**: /start-task
- **Method**: GET
- **Description**: Starts a long-running task and streams progress updates to the client.

### Stop Task

- **URL**: /stop-task
- **Method**: GET
- **Description**: Stops the currently running task.

### Send Request

- **URL**: /send-request
- **Method**: GET
- **Description**: Sends a request to start the task and returns the receiver's response.

## Project Structure

```bash
src
├── main
│   ├── java
│   │   └── diginamic
│   │       └── m052024
│   │           └── reactive_web_application
│   │               └── controller
│   │                   ├── ReceiverController.java
│   │                   └── SenderController.java
│   ├── resources
│   │   ├── static
│   │   │   └── index.html
│   │   └── application.properties
└── test
    └── java
        └── diginamic
            └── m052024
                └── reactive_web_application
                    └── controller
                        └── ReceiverControllerTest.java
```
## How it works

### Receiver Controller

- **startTask**: Initiates the task and streams updates using Sinks.Many.
- **stopTask**: Stops the task and completes the stream.
- **performTask**: Executes the task in a separate thread, ensuring each iteration takes 1 second.

### Sender Controller

- **sendRequest**: Sends a request to start the task and returns the receiver's response.

### Frontend

- **index.html**: Contains the UI and JavaScript to handle real-time updates and user interactions.

### Tests

- **ReceiverControllerTest**: Tests the receiver controller using WebTestClient.

## License

This project is licensed under the MIT License. See the LICENSE file for details.

## Contact

For any inquiries or issues, please contact gaelbueno@aol.com.
