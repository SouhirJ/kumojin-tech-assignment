package com.kumojin.eventsmanager.repositories;


import com.kumojin.eventsmanager.dto.EventDto;
import com.kumojin.eventsmanager.models.Event;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataMongoTest
public class EventRepositoryTests {
    @Autowired
    private EventRepository eventRepository;

    @Test
    public void testSaveEvent() {
        // Arrange
        Event event = Event.builder()
                .name("Yoga")
                .description("Event for Yoga lovers")
                //.startDate(ZonedDateTime.parse("2023-10-05T08:20:10+05:30[Canada/Central]"))
                //.endDate(ZonedDateTime.parse("2023-10-06T08:20:10+05:30[Canada/Central]"))
                .build();
        // Act
        Event savedEvent = eventRepository.save(event);

        // Assert
        assertEquals(event.getName(), savedEvent.getName());
    }
    @Test
    public void testFindByName() {
        // Arrange
        String eventName = "DevSecOps";
        Event event = Event.builder()
                .name(eventName)
                .description("Meetup for DevSecOps lovers")
                //.startDate(ZonedDateTime.parse("2023-10-05T08:20:10+05:30[Canada/Central]"))
                //.endDate(ZonedDateTime.parse("2023-10-06T08:20:10+05:30[Canada/Central]"))
                .build();
        // Save the Event to the in-memory database
        eventRepository.save(event);

        // Act
        Optional<EventDto> result = eventRepository.findByName(eventName);

        // Assert
        assertEquals(event.getName(), result.orElse(null).getName());
    }

    @AfterEach
    void cleanUpDatabase() {
        this.eventRepository.deleteAll();
    }

}
