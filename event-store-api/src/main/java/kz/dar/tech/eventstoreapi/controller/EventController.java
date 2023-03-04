package kz.dar.tech.eventstoreapi.controller;

import kz.dar.tech.eventstoreapi.document.Event;
import kz.dar.tech.eventstoreapi.service.EventService;
import kz.dar.tech.eventstoreapi.util.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequestMapping("/event")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @GetMapping("/all")
    public Page<Event> getAllEvents(
            @RequestParam(defaultValue = "9") int page,
            @RequestParam(defaultValue = "9") int size
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
            @RequestParam(value = "page", defaultValue = "9") int page,
            @RequestParam(value = "size", defaultValue = "9") int size
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
            @RequestPart("event") Event event,
            @RequestPart(value = "photo1", required = false) MultipartFile photo1,
            @RequestPart(value = "photo2", required = false) MultipartFile photo2,
            @RequestPart(value = "photo3", required = false) MultipartFile photo3,
            @RequestPart(value = "photo4", required = false) MultipartFile photo4,
            @RequestPart(value = "photo5", required = false) MultipartFile photo5
    ) throws IOException {
        return eventService.createEvent(
                event,
                photo1,
                photo2,
                photo3,
                photo4,
                photo5
        );
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
