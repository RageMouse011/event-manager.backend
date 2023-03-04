package kz.dar.tech.eventstoreapi.repository;

import kz.dar.tech.eventstoreapi.document.Event;
import kz.dar.tech.eventstoreapi.util.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


public interface EventRepository
        extends ElasticsearchRepository<Event, String> {
    Page<Event> findAll(
            Pageable pageable
    );

    Page<Event> findByCategory(
            Category category,
            Pageable pageable
    );
}
