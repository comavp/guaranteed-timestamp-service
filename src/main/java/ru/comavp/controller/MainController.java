package ru.comavp.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.comavp.model.dto.TimestampDto;
import ru.comavp.service.TimestampService;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/timestamps")
public class MainController {

    private final TimestampService timestampService;

    @GetMapping
    public ResponseEntity<List<TimestampDto>> findAll() {
        log.info("Received request to find all entities");
        return ResponseEntity.ok(timestampService.findAll());
    }

    @DeleteMapping
    public ResponseEntity<Void> clearTimestampList() {
        log.info("Received request to clear DB");
        timestampService.clearTimestampList();
        return ResponseEntity.noContent().build();
    }
}
