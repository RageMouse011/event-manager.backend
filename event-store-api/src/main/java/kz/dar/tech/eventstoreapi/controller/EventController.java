package kz.dar.tech.eventstoreapi.controller;

import kz.dar.tech.eventstoreapi.document.Event;
import kz.dar.tech.eventstoreapi.service.EventService;
import kz.dar.tech.eventstoreapi.util.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/event")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @GetMapping("/all")
    public Page<Event> getAllEvents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return eventService.getAllEvents(
                page,
                size
        );
    }

    @GetMapping("/{eventId}")
    public Event getEventById(
            @PathVariable String eventId
    ) {
        return eventService.getEventById(eventId);
    }
    @GetMapping("/filter")
    public Page<Event> getEventsWithFiltersAndSoring(
            @RequestParam(value = "category", required = false) Category category,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "sortDirection", required = false) Sort.Direction sortDirection,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        return eventService.getEventsWithFiltersAndSoring(
                category,
                sortBy,
                sortDirection,
                page,
                size
        );
    }


    @PostMapping
    public Event createEvent(
            @RequestBody Event event
    ) {
        return eventService.createEvent(event);
    }

    @PutMapping("/{eventId}")
    public Event updateEvent(
            @PathVariable String eventId,
            @RequestBody Event event
    ) {
        return eventService.updateEvent(
                eventId,
                event
        );
    }

    @DeleteMapping("/{eventId}")
    public void deleteEvent(
            @PathVariable String eventId
    ) {
        eventService.deleteEvent(eventId);
    }

    @PostMapping("/{eventId}/like")
    public Event likeEvent(
            @PathVariable String eventId
    ) {
        return eventService.likeEvent(eventId);
    }
}
