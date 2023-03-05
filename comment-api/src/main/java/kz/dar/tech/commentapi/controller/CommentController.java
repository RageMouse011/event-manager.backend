package kz.dar.tech.commentapi.controller;

import kz.dar.tech.commentapi.document.Comment;
import kz.dar.tech.commentapi.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/event/{eventId}/comment")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public Comment createComment(
                @PathVariable String eventId,
                @RequestBody Comment comment
    ) {
        return commentService.createComment(
                eventId,
                comment
        );
    }

    @GetMapping("/{commentId}")
    public Comment getComment(
            @PathVariable String eventId,
            @PathVariable String commentId
    ) {
        return commentService.getComment(
                eventId,
                commentId
        );
    }

      @GetMapping("/all")
      public Page<Comment> getCommentsByEventId(
              @PathVariable String eventId,
              @RequestParam(value = "sortBy", required = false) String sortBy,
              @RequestParam(value = "sortDirection", defaultValue = "DESC") Sort.Direction sortDirection,
              @RequestParam(defaultValue = "0") int page,
              @RequestParam(defaultValue = "10") int size
      ) {
          return commentService.getCommentsByEventId(
                  eventId,
                  sortBy,
                  sortDirection,
                  page,
                  size);
      }

      @DeleteMapping("/{commentId}")
      public void deleteComment(
              @PathVariable String eventId,
              @PathVariable String commentId
      ) {
         commentService.deleteComment(
                 eventId,
                 commentId
         );
      }

      @PutMapping("/{commentId}")
      public Comment updateComment(
              @PathVariable String commentId,
              @PathVariable String eventId,
              @RequestBody Comment comment
      ) {
         return commentService.updateComment(
                 commentId,
                 comment
         );
      }
}
