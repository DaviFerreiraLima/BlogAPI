package com.compassuol.springbootblog.service;

import com.compassuol.springbootblog.entity.Post;
import com.compassuol.springbootblog.payload.PostDto;
import com.compassuol.springbootblog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {


    private PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostDto createPost(PostDto postDto){

        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post newPost = postRepository.save(post);

        PostDto response = new PostDto();
        response.setContent(newPost.getContent());
        response.setDescription(newPost.getDescription());
        response.setTitle(newPost.getTitle());

        return response;
    }
}
