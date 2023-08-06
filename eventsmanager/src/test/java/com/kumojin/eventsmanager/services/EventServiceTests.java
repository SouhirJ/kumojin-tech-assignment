package com.kumojin.eventsmanager.services;

import com.kumojin.eventsmanager.dto.EventDto;
import com.kumojin.eventsmanager.models.Event;
import com.kumojin.eventsmanager.repositories.EventRepository;
import com.kumojin.eventsmanager.validators.ObjectsValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EventServiceTests {
    @InjectMocks
    private EventServiceImpl eventService;
    @Mock
    private EventRepository eventRepository;
    @Mock
    private ObjectsValidator<EventDto>  validator;

    private EventDto eventDto1;
    private Event event1;
    private EventDto eventDto2;
    private Event event2;

    @BeforeEach
    public void init() {
        // Initialize test data before each test method
         eventDto1 = EventDto.builder()
                .name("Yoga")
                .description("Event for Yoga lovers")
                .startDate(ZonedDateTime.parse("2023-10-05T08:20:10+05:30[Canada/Central]"))
                .endDate(ZonedDateTime.parse("2023-10-06T08:20:10+05:30[Canada/Central]"))
                .build();
        eventDto2 = EventDto.builder()
                .name("Cloud")
                .description("Meetup for cloud community")
                .startDate(ZonedDateTime.parse("2023-10-05T08:20:10+05:30[Canada/Central]"))
                .endDate(ZonedDateTime.parse("2023-10-06T08:20:10+05:30[Canada/Central]"))
                .build();
        event1 = Event.builder()
                .name("Yoga")
                .description("Event for Yoga lovers")
                .startDate(ZonedDateTime.parse("2023-10-05T08:20:10+05:30[Canada/Central]"))
                .endDate(ZonedDateTime.parse("2023-10-06T08:20:10+05:30[Canada/Central]"))
                .build();
        event2 = Event.builder()
                .name("Cloud")
                .description("Meetup for cloud community")
                .startDate(ZonedDateTime.parse("2023-10-05T08:20:10+05:30[Canada/Central]"))
                .endDate(ZonedDateTime.parse("2023-10-06T08:20:10+05:30[Canada/Central]"))
                .build();
    }
    @Test
    public void testCreateEvent() {
        // Arrange
        when(eventRepository.save(Mockito.any(Event.class))).thenReturn(event1);

        // Act
        EventDto createdEvent = eventService.create(eventDto1);

        // Assert
        assertEquals(eventDto1.getName(), createdEvent.getName());
        Assertions.assertNotNull(createdEvent);

        verify(eventRepository).save(Mockito.any(Event.class));
    }
    @Test
    public void testGetAllEvents() {
        // Arrange
        List<Event> events = Arrays.asList(event1, event2);
        when(eventRepository.findAll()).thenReturn(events);

        // Act
        List<EventDto> allEvents = eventService.findAll();

        // Assert
        assertEquals(2, allEvents.size());

        verify(eventRepository).findAll();

    }
    @BeforeEach
    void setup() {
    }
}
