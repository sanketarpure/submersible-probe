package com.maveric.submersible.probe.service;

import com.maveric.submersible.probe.dto.CurrentPositionResponse;
import com.maveric.submersible.probe.dto.ProbeRequest;
import com.maveric.submersible.probe.dto.ProbeResponse;
import com.maveric.submersible.probe.model.Grid;
import com.maveric.submersible.probe.model.Position;
import com.maveric.submersible.probe.model.Probe;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProbeService {
    private Probe currentProbe; // Store probe state (in-memory for simplicity)

    public ProbeResponse navigate(ProbeRequest request) {
        Set<Position> obstacles = request.getObstacles().stream()
                .map(dto -> new Position(dto.getX(), dto.getY()))
                .collect(Collectors.toSet());

        Grid grid = new Grid(request.getGridWidth(), request.getGridHeight(), obstacles);
        Position initialPosition = new Position(
                request.getInitialPosition().getX(),
                request.getInitialPosition().getY()
        );

        currentProbe = new Probe(initialPosition, request.getInitialDirection(), grid);
        currentProbe.executeCommands(request.getCommands());

        ProbeResponse response = new ProbeResponse();
        response.setFinalPosition(currentProbe.getPosition());
        response.setFinalDirection(currentProbe.getDirection());
        response.setVisitedPositions(currentProbe.getVisitedPositions());

        return response;
    }

    public CurrentPositionResponse getCurrentPosition() {
        if (currentProbe == null) {
            throw new IllegalStateException("Probe has not been initialized. Please navigate first.");
        }
        CurrentPositionResponse response = new CurrentPositionResponse();
        response.setCurrentPosition(currentProbe.getPosition());
        response.setCurrentDirection(currentProbe.getDirection());
        return response;
    }
}