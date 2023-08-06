package com.kumojin.eventsmanager.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kumojin.eventsmanager.dto.EventDto;
import com.kumojin.eventsmanager.models.Event;
import com.kumojin.eventsmanager.services.EventService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.ZonedDateTime;
import java.util.Arrays;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@WebMvcTest (controllers = EventController.class)
@AutoConfigureMockMvc (addFilters = false)
@ExtendWith(MockitoExtension.class)
public class EventControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EventService eventService;

    @Autowired
    private ObjectMapper objectMapper;
    private Event event;
    private EventDto eventDto;

    @BeforeEach
    public void init() {
        eventDto = EventDto.builder()
                .name("Yoga")
                .description("Event for Yoga lovers")
                .startDate(ZonedDateTime.parse("2023-10-05T08:20:10+05:30[Canada/Central]"))
                .endDate(ZonedDateTime.parse("2023-10-06T08:20:10+05:30[Canada/Central]"))
                .build();

        event = Event.builder()
                .name("Yoga")
                .description("Event for Yoga lovers")
                .startDate(ZonedDateTime.parse("2023-10-05T08:20:10+05:30[Canada/Central]"))
                .endDate(ZonedDateTime.parse("2023-10-06T08:20:10+05:30[Canada/Central]"))
                .build();


    }

    @Test
    public void EventController_CreateEvent_ReturnCreated() throws Exception {
        given(eventService.create(ArgumentMatchers.any())).willAnswer((invocation -> invocation.getArgument(0)));

        ResultActions response = mockMvc.perform(post("/api/v1/events/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(eventDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(eventDto.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description", CoreMatchers.is(eventDto.getDescription())));
    }

    @Test
    public void EventController_GetAllEvents_ReturnResponseDto() throws Exception {
        when(eventService.findAll()).thenReturn(Arrays.asList(eventDto));

        ResultActions response = mockMvc.perform(get("/api/v1/events")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(eventDto)));
        ;

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(Arrays.asList(eventDto).size())));
    }
}
