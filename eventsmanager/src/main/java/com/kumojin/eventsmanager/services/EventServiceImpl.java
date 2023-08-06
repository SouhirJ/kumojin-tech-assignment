package com.kumojin.eventsmanager.services;

import com.kumojin.eventsmanager.dto.EventDto;
import com.kumojin.eventsmanager.exceptions.EventAlreadyExistsException;
import com.kumojin.eventsmanager.exceptions.ObjectValidationException;
import com.kumojin.eventsmanager.models.Event;
import com.kumojin.eventsmanager.repositories.EventRepository;
import com.kumojin.eventsmanager.validators.ObjectsValidator;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Log4j2
public class EventServiceImpl implements EventService{
    private final EventRepository eventRepository;
    private final ObjectsValidator<EventDto> validator;

    @Override
    public EventDto create(EventDto eventDto) throws ObjectValidationException, EventAlreadyExistsException {
        validator.validate(eventDto);
        Event eventToSave = EventDto.toEvent(eventDto);
        Optional<EventDto> optionalEventDto = eventRepository.findByName(eventDto.getName());
        if (optionalEventDto.isPresent()) {
            throw new EventAlreadyExistsException("Event with name " + eventDto.getName() + " already exists!");
        } else {
            eventToSave.setCreatedAt(LocalDateTime.now());
            return EventDto.toEventDto(eventRepository.save(eventToSave));
        }
    }

    @Override
    public EventDto update(UUID id, EventDto object) {
        return null;
    }

    @Override
    public EventDto findById(UUID id) {
        return null;
    }

    @Override
    public List<EventDto> findAll() {
        return eventRepository.findAll()
                .stream()
                .map(EventDto::toEventDto)
                .collect(Collectors.toList());
    }
}
