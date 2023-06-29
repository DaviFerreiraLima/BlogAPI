package com.compassuol.springbootblog.service;

import com.compassuol.springbootblog.entity.Comment;
import com.compassuol.springbootblog.exception.BlogAPIException;
import com.compassuol.springbootblog.exception.ResourceNotFoundException;
import com.compassuol.springbootblog.payload.CommentDto;
import com.compassuol.springbootblog.repository.CommentRepository;
import com.compassuol.springbootblog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private CommentRepository commentRepository;
    private PostRepository postRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository,PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    public CommentDto createComment(long postId,CommentDto commentDto){

        Comment comment =mapToEntity(commentDto);

        var post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post","id",postId));
        comment.setPost(post);
        comment = commentRepository.save(comment);

        return mapToDto(comment);
    }

    public List<CommentDto> getAllCommentsByPostId(long postId) {
        return commentRepository.findByPostId(postId)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public CommentDto getCommentById(long postId,long commentId) {
        var comment = commentRepository.findByPostIdAndId(postId,commentId);
        if (comment == null){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"The comment does not belong to post");
        }
        return mapToDto(comment);
    }

    public CommentDto updateComment(long postId, long commentId, CommentDto commentDto){
        var comment = commentRepository.findByPostIdAndId(postId,commentId);

        if ( comment== null){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"The comment does not belong to post");
        }

        comment.setEmail(commentDto.getEmail());
        comment.setName(commentDto.getName());
        comment.setBody(commentDto.getBody());

       comment = commentRepository.save(comment);
        return mapToDto(comment);
    }

    public void deleteComment(long postId, long commentId){
        if ( commentRepository.findByPostIdAndId(postId,commentId)== null){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"The comment does not belong to post");
        }
        commentRepository.deleteById(commentId);
    }
    private CommentDto mapToDto(Comment comment){
        var commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setBody(comment.getBody());
        commentDto.setName(comment.getName());
        commentDto.setEmail(comment.getEmail());
        return commentDto;
    }

    private Comment mapToEntity(CommentDto commentDto){
        Comment comment = new Comment();
        comment.setId(commentDto.getId());
        comment.setEmail(commentDto.getEmail());
        comment.setName(commentDto.getName());
        comment.setBody(commentDto.getBody());
        return comment;
    }



}
