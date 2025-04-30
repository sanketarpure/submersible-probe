package com.maveric.submersible.probe.model;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class Grid {
    private final int width;
    private final int height;
    private final Set<Position> obstacles;

    public Grid(int width, int height, Set<Position> obstacles) {
        this.width = width;
        this.height = height;
        this.obstacles = new HashSet<>(obstacles);
    }

    public boolean isValidPosition(Position position) {
        return position.getX() >= 0 && position.getX() < width &&
               position.getY() >= 0 && position.getY() < height &&
               !obstacles.contains(position);
    }

}