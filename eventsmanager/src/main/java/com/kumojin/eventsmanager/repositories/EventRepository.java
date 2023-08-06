package com.kumojin.eventsmanager.repositories;

import com.kumojin.eventsmanager.dto.EventDto;
import com.kumojin.eventsmanager.models.Event;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface EventRepository extends MongoRepository<Event, String> {
    @Query("{ 'name' : ?0 }")
    Optional<EventDto> findByName(String name);
}
