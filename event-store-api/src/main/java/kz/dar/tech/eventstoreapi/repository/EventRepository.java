package kz.dar.tech.eventstoreapi.repository;

import kz.dar.tech.eventstoreapi.document.Event;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface EventRepository extends ElasticsearchRepository<Event, String> {

    List<Event> findByCategoryAndSectionsIn(String category, List<String> sections);
}
