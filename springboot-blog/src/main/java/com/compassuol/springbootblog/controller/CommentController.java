package com.compassuol.springbootblog.controller;

import com.compassuol.springbootblog.payload.CommentDto;
import com.compassuol.springbootblog.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment (
            @PathVariable int postId ,
            @RequestBody CommentDto commentDto){
        return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);
    }

    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<List<CommentDto>> getAllCommentsByPostId(@PathVariable long postId){
        return new ResponseEntity<>(commentService.getAllCommentsByPostId(postId),HttpStatus.OK);
    }

    @GetMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable long postId,@PathVariable long id){
        return new ResponseEntity<>(commentService.getCommentById(postId,id),HttpStatus.OK);
    }

    @PutMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable long postId,@PathVariable long id,@RequestBody CommentDto commentDto){
        return new ResponseEntity<>(commentService.updateComment(postId,id,commentDto),HttpStatus.OK);
    }

    @DeleteMapping ("/posts/{postId}/comments/{id}")
    public ResponseEntity<String> deleteCommentById(@PathVariable long postId,@PathVariable long id){
        commentService.deleteComment(postId,id);
        return new ResponseEntity<String>("Comment deleted successfully!",HttpStatus.OK);
    }
}
