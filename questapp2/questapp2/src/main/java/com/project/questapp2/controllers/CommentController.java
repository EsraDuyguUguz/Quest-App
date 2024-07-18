package com.project.questapp2.controllers;

import com.project.questapp2.entities.Comment;
import com.project.questapp2.requests.CommentCreateRequest;
import com.project.questapp2.requests.CommentUpdateRequest;
import com.project.questapp2.responses.CommentResponse;
import com.project.questapp2.services.CommentService;

import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/comments")
public class CommentController {
    private CommentService commentService;

    public CommentController(CommentService commentService){
        super();
        this.commentService = commentService;
    }

    @GetMapping
    private List<CommentResponse> getAllComments(@RequestParam Optional<Long> userId, @RequestParam Optional<Long> postId){
        return commentService.getAllCommentsWithParam(userId,postId);
    }

    @PostMapping
    public Comment createOneComment(@RequestBody CommentCreateRequest request){
        return commentService.createOneComment(request);
    }

    @GetMapping("/{commentId}")
    public Comment getOneComment(@PathVariable Long commentId){
        return commentService.getOneCommentById(commentId);
    }

    @PutMapping("/{commentId}")
    public Comment updateOneComment(@PathVariable Long commentId,
                                    @RequestBody CommentUpdateRequest request){
        return commentService.updateOneCommentById(commentId, request);
    }

    @DeleteMapping("/{commentId}")
    public void deleteOneComment(@PathVariable Long commentId){
        commentService.deleteOneCommentById(commentId);
    }
}