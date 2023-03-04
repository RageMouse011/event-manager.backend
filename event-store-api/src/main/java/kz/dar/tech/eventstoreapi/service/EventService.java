package kz.dar.tech.eventstoreapi.service;

import kz.dar.tech.eventstoreapi.document.Event;
import kz.dar.tech.eventstoreapi.repository.EventRepository;
import kz.dar.tech.eventstoreapi.util.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final EventProducer eventProducer;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");

    public Event createEvent(Event event) {
        event.setDateOfCreation(LocalDateTime.now().format(FORMATTER));
        Event createdEvent = eventRepository.save(event);

        eventProducer.sendEvent(createdEvent.toString(), "event-key");
        return createdEvent;
    }

    public Event getEventById(String eventId) {
        return eventRepository.findById(eventId).get();
    }

    public Page<Event> getAllEvents(
            int page,
            int size
    ) {
        return findAllOrderBy("dateOfCreation", Sort.Direction.valueOf("DESC"), page, size);
    }

    public Page<Event> getEventsWithFiltersAndSoring(
            Category category,
            String sortBy,
            Sort.Direction sortDirection,
            int page,
            int size) {
        if (sortDirection == null) {
            sortDirection = Sort.Direction.DESC;
        }
        Page<Event> events;
        if (category != null) {
            events = findByCategoryAndOrderBy(category, sortBy, sortDirection, page, size);
        } else {
            events = findAllOrderBy(sortBy, sortDirection, page, size);
        }
        return events;
    }

    public Page<Event> findByCategoryAndOrderBy(
            Category category,
            String sortBy,
            Sort.Direction sortDirection,
            int page,
            int size
    ) {
        Sort sort = sortBy != null ? Sort.by(sortDirection, sortBy) : Sort.by(sortDirection, "dateOfCreation");
        return eventRepository.findByCategory(category, PageRequest.of(page, size, sort));
    }

    public Page<Event> findAllOrderBy(
            String sortBy,
            Sort.Direction sortDirection,
            int page,
            int size) {
        Sort sort = sortBy != null ? Sort.by(sortDirection, sortBy) : Sort.by(sortDirection, "dateOfCreation");
        return eventRepository.findAll(PageRequest.of(page, size, sort));
    }

    public Event updateEvent(
            String eventId,
            Event event
    ) {
        Event updatedEvent = eventRepository.findById(eventId).get();

        updatedEvent.setName(event.getName());
        updatedEvent.setDescription(event.getDescription());
        updatedEvent.setCategory(event.getCategory());
        updatedEvent.setDate(event.getDate());
        updatedEvent.setTime(event.getTime());
        updatedEvent.setLocation(event.getLocation());

        return eventRepository.save(
                updatedEvent
        );
    }

    public void deleteEvent(String eventId) {
        eventRepository.deleteById(eventId);
    }

    public Event likeEvent(
            String eventId
    ) {
        Event event = eventRepository.findById(eventId).get();
        event.setLikes(event.getLikes() + 1);
        return eventRepository.save(event);
    }
}
