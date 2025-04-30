package com.maveric.submersible.probe.model;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Getter
@Slf4j
public class Probe {
    private Position position;
    private Direction direction;
    private final Grid grid;
    private final List<Position> visitedPositions;

    public Probe(Position initialPosition, Direction initialDirection, Grid grid) {
        this.position = initialPosition;
        this.direction = initialDirection;
        this.grid = grid;
        this.visitedPositions = new ArrayList<>();
        this.visitedPositions.add(initialPosition);
    }

    public void executeCommands(List<Command> commands) {
        for (Command command : commands) {
            if (grid.isValidPosition(position)) {
                switch (command) {
                    case FORWARD -> moveForward();
                    case BACKWARD -> moveBackward();
                    case LEFT -> direction = direction.turnLeft();
                    case RIGHT -> direction = direction.turnRight();
                    case STAY -> dontMove();
                }

                visitedPositions.add(position);
                log.info("Visited position " + position + " to " + direction);
            }
        }
    }

    private void moveForward() {
        Position newPosition = calculateNewPosition(1);
        if (grid.isValidPosition(newPosition)) {
            position = newPosition;
        } else {
            log.error("Blocked moving forward by obstacle or boundary at {}" , newPosition);
        }
    }

    private void moveBackward() {
        Position newPosition = calculateNewPosition(-1);
        if (grid.isValidPosition(newPosition)) {
            position = newPosition;
        }else {
            log.error("Blocked moving backward by obstacle or boundary at {}", newPosition);
        }
    }

    private void dontMove() {
        Position newPosition = calculateNewPosition(0);
        if (grid.isValidPosition(newPosition)) {
            position = newPosition;
        }else {
            log.error("Stay at {}", newPosition);
        }
    }

    private Position calculateNewPosition(int step) {
        return switch (direction) {
            case NORTH -> new Position(position.getX(), position.getY() + step);
            case SOUTH -> new Position(position.getX(), position.getY() - step);
            case EAST -> new Position(position.getX() + step, position.getY());
            case WEST -> new Position(position.getX() - step, position.getY());
        };
    }

}