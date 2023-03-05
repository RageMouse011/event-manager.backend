package kz.dar.tech.commentapi.repository;

import kz.dar.tech.commentapi.document.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface CommentRepository
                extends ElasticsearchRepository<Comment, String> {
    Page<Comment> findByEventId(
            String eventId,
            Pageable pageable
    );
}
