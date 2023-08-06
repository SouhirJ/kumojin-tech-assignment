package com.kumojin.eventsmanager.controllers;

import com.kumojin.eventsmanager.dto.EventDto;
import com.kumojin.eventsmanager.services.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/events")
@RequiredArgsConstructor
@Tag(name = "Events")
@CrossOrigin(origins ="*")
public class EventController {
    private final EventService eventService;

    @PostMapping("create")
    public ResponseEntity<EventDto> createEvent(@RequestBody EventDto eventDto) {
        return ResponseEntity.ok(eventService.create(eventDto));
    }
    @Operation(
            description = "Get all events",
            summary = "Get all events",
            responses = {
                    @ApiResponse(
                            description = "OK",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Events not found",
                            responseCode = "404")
            }
    )
    @GetMapping()
    public ResponseEntity<?> getAllEvents() {
        List<EventDto> events = eventService.findAll();
        if (events.size() > 0) {
            return new ResponseEntity<>(events, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No events available", HttpStatus.NOT_FOUND);
        }
    }
}
