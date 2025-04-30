package com.maveric.submersible.probe.dto;

import com.maveric.submersible.probe.model.Command;
import com.maveric.submersible.probe.model.Direction;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class ProbeRequest {
    private int gridWidth;
    private int gridHeight;
    private Set<PositionDto> obstacles;
    private PositionDto initialPosition;
    private Direction initialDirection;
    private List<Command> commands;
}