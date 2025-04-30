# Submersible Probe REST API

# Overview
This project is a Spring Boot REST API for controlling a remotely operated submersible probe on an ocean floor grid. The probe navigates a 2D grid using x/y coordinates, starting from an initial position and direction, executing commands (FORWARD, BACKWARD, LEFT, RIGHT), avoiding obstacles, and tracking visited positions. The API is built with a Test-Driven Development (TDD) approach, clean code principles, and includes Swagger documentation for easy testing.
Features

# REST API Endpoints:
POST /api/probe/navigate: Execute navigation commands and return final position, direction, and visited positions.
GET /api/probe/current-position: Retrieve the probe's current position and direction.

# Prerequisites
Java: 17 or later
Maven: 3.6.0 or later
Git: For version control
IDE: IntelliJ IDEA, Eclipse, or VS Code (optional, for development)

The application will start on http://localhost:8080.

# API Usage
1. Navigate Probe
   Endpoint: POST /api/probe/navigateDescription: Executes a series of commands to navigate the probe on the grid.Request Body:
   {
   "gridWidth": 5,
   "gridHeight": 5,
   "obstacles": [{"x": 2, "y": 2}],
   "initialPosition": {"x": 0, "y": 0},
   "initialDirection": "NORTH",
   "commands": ["FORWARD", "RIGHT", "FORWARD"]
   }

Response:
{
"finalPosition": {"x": 1, "y": 1},
"finalDirection": "EAST",
"visitedPositions": [{"x": 0, "y": 0}, {"x": 0, "y": 1}, {"x": 0, "y": 1}, {"x": 1, "y": 1}]
}

Curl Example:
curl -X POST http://localhost:8080/api/probe/navigate \
-H "Content-Type: application/json" \
-d '{"gridWidth": 5, "gridHeight": 5, "obstacles": [{"x": 2, "y": 2}], "initialPosition": {"x": 0, "y": 0}, "initialDirection": "NORTH", "commands": ["FORWARD", "RIGHT", "FORWARD"]}'

2. Get Current Position
   Endpoint: GET /api/probe/current-positionDescription: Retrieves the probe's current position and direction after navigation. Must call /navigate first.Response:
   {
   "currentPosition": {"x": 1, "y": 1},
   "currentDirection": "EAST"
   }

Curl Example:
curl -X GET http://localhost:8080/api/probe/current-position

# Swagger URL
http://localhost:8080/swagger-ui/index.html
