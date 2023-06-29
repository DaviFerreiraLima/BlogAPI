package com.compassuol.springbootblog.controller;

import com.compassuol.springbootblog.payload.PostDto;
import com.compassuol.springbootblog.payload.PostResponse;
import com.compassuol.springbootblog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<PostResponse> getAllPosts(
            @RequestParam(value = "pageNo",defaultValue = "0",required = false) int pageNo,
            @RequestParam(value = "pageSize",defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id",required = false) String sortBy
    ){
        return new ResponseEntity<>(postService.getAllPosts( pageNo,pageSize,sortBy),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable long id){
        return new ResponseEntity<>(postService.getPostById(id),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable long id){
        return new ResponseEntity<PostDto>(postService.updatePost(id,postDto),HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> updatePost(@PathVariable long id){
        postService.deletePost(id);
        return new ResponseEntity<>("Post deleted Successfully",HttpStatus.OK);
    }
}
