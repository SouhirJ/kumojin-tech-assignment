package com.kumojin.eventsmanager.dto;


import com.kumojin.eventsmanager.models.Event;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;

import java.time.ZonedDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventDto {
    private String id;
    @NotNull(message = "name can not be null")
    @Size(max = 32, message = "name must not be more than 32 letters")
    private String name;
    @NotNull(message = "description can not be null")
    private String description;
    private ZonedDateTime startDate;
    private ZonedDateTime endDate;

    public static Event toEvent(EventDto eventDto) {
        if (eventDto == null) {
            return null;
        }
        return Event.builder()
                .id(eventDto.getId())
                .name(eventDto.getName())
                .description(eventDto.getDescription())
                .startDate(eventDto.getStartDate())
                .endDate(eventDto.getEndDate())
                .build();
    }
    public static EventDto toEventDto(Event event) {
        if (event == null) {
            return null;
        }
        return EventDto.builder()
                .id(event.getId())
                .name(event.getName())
                .description(event.getDescription())
                .startDate(event.getStartDate())
                .endDate(event.getEndDate())
                .build();
    }

}
