package com.maveric.submersible.probe.dto;

import com.maveric.submersible.probe.model.Direction;
import com.maveric.submersible.probe.model.Position;
import lombok.Data;

import java.util.List;

@Data
public class ProbeResponse {
    private Position finalPosition;
    private Direction finalDirection;
    private List<Position> visitedPositions;
}