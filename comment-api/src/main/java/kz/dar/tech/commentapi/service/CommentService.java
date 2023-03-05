package kz.dar.tech.commentapi.service;

import kz.dar.tech.commentapi.document.Comment;
import kz.dar.tech.commentapi.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@Service
public class CommentService {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
    private final CommentRepository commentRepository;
    private final CommentProducer commentProducer;

    public Comment createComment(
            String eventId,
            Comment comment
    ) {
        comment.setDateOfCreation(LocalDateTime.now().format(FORMATTER));
        comment.setEventId(eventId);
        Comment createdComment = commentRepository.save(comment);
        commentProducer.sendComment(createdComment.toString(), "comment-key");
        return createdComment;
    }

    public Page<Comment> getCommentsByEventId(
            String eventId,
            String sortBy,
            Sort.Direction sortDirection,
            int page,
            int size
    ) {
        return findByEventIdAndOrderBy(
                sortBy,
                sortDirection,
                eventId,
                page,
                size
        );
    }

    public Page<Comment> findByEventIdAndOrderBy(
            String sortBy,
            Sort.Direction sortDirection,
            String eventId,
            int page,
            int size
    ) {
        Sort sort = sortBy != null ? Sort.by(sortDirection, sortBy) : Sort.by(sortDirection, "dateOfCreation");
        return commentRepository.findByEventId(eventId, PageRequest.of(page, size, sort));
    }

    public void deleteComment(
            String eventId,
            String commentId
    ) {
        commentRepository.deleteById(commentId);
    }

    public Comment updateComment(
            String commentId,
            Comment comment
    ) {
        Comment updatedComment = commentRepository.findById(commentId).get();
        updatedComment.setMessage(comment.getMessage());
        return commentRepository.save(updatedComment);
    }

    public Comment getComment(
            String eventId,
            String commentId) {
        return commentRepository.findById(commentId).get();
    }
}
