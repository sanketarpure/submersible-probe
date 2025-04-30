package com.maveric.submerible.probe;

import com.maveric.submersible.probe.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ProbeTest {
    private Grid grid;
    private Probe probe;

    @BeforeEach
    void setUp() {
        grid = new Grid(5, 5, Set.of(new Position(2, 2)));
        probe = new Probe(new Position(0, 0), Direction.NORTH, grid);
    }

    @Test
    void shouldMoveForwardWhenValid() {
        probe.executeCommands(List.of(Command.FORWARD));
        assertEquals(new Position(0, 1), probe.getPosition());
    }

    @Test
    void shouldNotMoveForwardWhenObstacle() {
        Grid testGrid = new Grid(5, 5, Set.of(new Position(2, 2))); // Fresh grid
        Probe testProbe = new Probe(new Position(2, 1), Direction.NORTH, testGrid);
        testProbe.executeCommands(List.of(Command.FORWARD));
        assertEquals(new Position(2, 1), testProbe.getPosition());
    }

    @Test
    void shouldTurnLeft() {
        probe.executeCommands(List.of(Command.LEFT));
        assertEquals(Direction.WEST, probe.getDirection());
    }

    @Test
    void shouldTrackVisitedPositions() {
        probe.executeCommands(List.of(Command.FORWARD, Command.RIGHT, Command.FORWARD));
        List<Position> expected = List.of(
                new Position(0, 0),
                new Position(0, 1),
                new Position(0, 1),
                new Position(1, 1)
        );
        assertEquals(expected, probe.getVisitedPositions());
    }

    @Test
    void shouldNotMoveOutsideGrid() {
        probe = new Probe(new Position(0, 4), Direction.NORTH, grid);
        probe.executeCommands(List.of(Command.FORWARD));
        assertEquals(new Position(0, 4), probe.getPosition());
    }

    @Test
    void shouldStayOnGrid() {
        probe.executeCommands(List.of(Command.STAY));
        assertEquals(new Position(0, 0), probe.getPosition());
    }
}