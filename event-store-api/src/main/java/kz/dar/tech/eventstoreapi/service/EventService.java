package kz.dar.tech.eventstoreapi.service;

import kz.dar.tech.eventstoreapi.document.Event;
import kz.dar.tech.eventstoreapi.repository.EventRepository;
import kz.dar.tech.eventstoreapi.util.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.Files.write;


@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final EventProducer eventProducer;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
    private static final String DATA_STORE_URL = "/home/ragemouse11/event-manager-datastore/";

    public Event createEvent(
            Event event,
            MultipartFile photo1,
            MultipartFile photo2,
            MultipartFile photo3,
            MultipartFile photo4,
            MultipartFile photo5
    ) throws IOException {
        event.setDateOfCreation(LocalDateTime.now().format(FORMATTER));
        List<MultipartFile> photos = new ArrayList<>();
        if (photo1 != null) photos.add(photo1);
        if (photo2 != null) photos.add(photo2);
        if (photo3 != null) photos.add(photo3);
        if (photo4 != null) photos.add(photo4);
        if (photo5 != null) photos.add(photo5);

        List<String> photosUrls = uploadPhotos(photos);
        event.setImageUrls(photosUrls);


        Event createdEvent = eventRepository.save(event);
        eventProducer.sendEvent(createdEvent.toString(), "event-key");
        return createdEvent;
    }

    public List<String> uploadPhotos(
            List<MultipartFile> photos
    ) throws IOException {
        List<String> photosUrls = new ArrayList<>();
        for (MultipartFile photo : photos) {
            byte[] bytes = photo.getBytes();
            Path path = Paths.get(DATA_STORE_URL + photo.getOriginalFilename());
            photosUrls.add(Files.write(path, bytes).toString());
        }
        return photosUrls;
    }

    public Event getEventById(String eventId) {
        return eventRepository.findById(eventId).get();
    }

    public Page<Event> getAllEvents(
            int page,
            int size,
            String sortedBy,
            Sort.Direction sortDirection
    ) {
        return findAllOrderBy(sortedBy, sortDirection, page, size);
    }

    public Page<Event> getEventsWithFiltersAndSoring(
            Category category,
            String sortBy,
            Sort.Direction sortDirection,
            int page,
            int size
    ) {
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
            int size
    ) {
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
