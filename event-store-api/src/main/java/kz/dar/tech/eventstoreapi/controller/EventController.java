package kz.dar.tech.eventstoreapi.controller;

import kz.dar.tech.eventstoreapi.document.Event;
import kz.dar.tech.eventstoreapi.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/event")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @GetMapping("/all")
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping
    public Event getEventById(
            @RequestParam String eventId
    ) {
        return eventService.getEventById(
                eventId
        );
    }

    @GetMapping("/filter")
    public List<Event> filterEventsByCategoriesAndSections(
            @RequestParam(required = false) String categories,
            @RequestParam(required = false) List<String> sections
    ) {
        return eventService.searchEvents(
                categories,
                sections);
    }

    @PostMapping
    public Event createEvent(
            @RequestBody Event event
    ) {
        return eventService.createEvent(
                event
        );
    }

    @PutMapping
    public Event updateEvent(
            @RequestParam String eventId,
            @RequestBody Event event
    ) {
        return eventService.updateEvent(
                eventId,
                event
        );
    }

    @DeleteMapping
    public void deleteEvent(
            @RequestParam String eventId
    ) {
        eventService.deleteEvent(
                eventId
        );
    }
}
