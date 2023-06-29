package com.compassuol.springbootblog.service;

import com.compassuol.springbootblog.entity.Post;
import com.compassuol.springbootblog.exception.ResourceNotFoundException;
import com.compassuol.springbootblog.payload.PostDto;
import com.compassuol.springbootblog.payload.PostResponse;
import com.compassuol.springbootblog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

        return convertToDto(newPost);
    }

    public PostResponse getAllPosts(int pageNo,int pageSize){

        var pageable = PageRequest.of(pageNo,pageSize);
        Page<Post> postPage = postRepository.findAll(pageable);
        List<PostDto> content = postPage.stream().map(this::convertToDto).collect(Collectors.toList());

        return new PostResponse(content,postPage.getNumber(),postPage.getSize(),
                postPage.getTotalElements(),postPage.getTotalPages(),postPage.isLast());
    }
    public PostDto getPostById(long id){
        return postRepository.findById(id)
                .map(this::convertToDto)
                .orElseThrow( ()-> new ResourceNotFoundException("Post","id",id));
    }



    public PostDto updatePost(long id,PostDto postDto) {

        var post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post","id",id));

        post.setTitle(postDto.getTitle());;
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        var newPost = postRepository.save(post);
        return convertToDto(newPost);
    }

    public void deletePost(long id){
        postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post","id",id));
        postRepository.deleteById(id);
    }
    private PostDto convertToDto(Post post){
        var postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setDescription(post.getDescription());
        postDto.setContent(post.getContent());
        return postDto;
    }
}
