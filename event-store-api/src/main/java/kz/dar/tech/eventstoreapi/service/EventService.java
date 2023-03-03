package kz.dar.tech.eventstoreapi.service;

import kz.dar.tech.eventstoreapi.document.Event;
import kz.dar.tech.eventstoreapi.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    public Event getEventById(String eventId) {
        return eventRepository.findById(eventId).get();
    }

    public List<Event> getAllEvents() {
        return (List<Event>) eventRepository.findAll();
    }


    public List<Event> searchEvents(String category, List<String> sections) {
        return eventRepository.findByCategoryAndSectionsIn(category, sections);
    }

    public Event updateEvent(
            String eventId,
            Event event
    ) {
        Event updatedEvent = eventRepository.findById(eventId).get();

        updatedEvent.setName(event.getName());
        updatedEvent.setDescription(event.getDescription());
        updatedEvent.setCategory(event.getCategory());
        updatedEvent.setSections(event.getSections());

        return eventRepository.save(
                updatedEvent
        );
    }

    public void deleteEvent(
            String eventId
    ) {
        eventRepository.deleteById(
                eventId
        );
    }
}
