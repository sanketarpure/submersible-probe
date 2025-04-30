package com.maveric.submersible.probe.controller;

import com.maveric.submersible.probe.dto.CurrentPositionResponse;
import com.maveric.submersible.probe.dto.ProbeRequest;
import com.maveric.submersible.probe.dto.ProbeResponse;
import com.maveric.submersible.probe.service.ProbeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Slf4j
public class ProbeController {
    private final ProbeService probeService;

    @PostMapping("/navigate")
    public ResponseEntity<ProbeResponse> navigate(@RequestBody ProbeRequest request) {
        log.info("Executing probe navigation commands");
        return ResponseEntity.ok(probeService.navigate(request));
    }

    @GetMapping("/current-position")
    public ResponseEntity<CurrentPositionResponse> getCurrentPosition() {
        log.info("Executing probe current position");
        CurrentPositionResponse response = probeService.getCurrentPosition();
        HttpHeaders headers = new HttpHeaders();
        if (response.getCurrentPosition() == null) {
            headers.add("X-Exception", "Initial Position is not initialized");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(headers).body(response);
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}