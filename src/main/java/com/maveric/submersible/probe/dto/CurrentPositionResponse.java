package com.maveric.submersible.probe.dto;

import com.maveric.submersible.probe.model.Direction;
import com.maveric.submersible.probe.model.Position;
import lombok.Data;

@Data
public class CurrentPositionResponse {
    private Position currentPosition;
    private Direction currentDirection;
}