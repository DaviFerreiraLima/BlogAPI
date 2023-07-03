package com.compassuol.springbootblog.service;

import com.compassuol.springbootblog.entity.Post;
import com.compassuol.springbootblog.exception.ResourceNotFoundException;
import com.compassuol.springbootblog.payload.PostDto;
import com.compassuol.springbootblog.payload.PostResponse;
import com.compassuol.springbootblog.repository.CategoryRepository;
import com.compassuol.springbootblog.repository.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper mapper;

    @Autowired
    public PostService(PostRepository postRepository, CategoryRepository categoryRepository, ModelMapper mapper) {
        this.postRepository = postRepository;
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
    }




    public PostDto createPost(PostDto postDto){
        var category = categoryRepository.findById(postDto.getCategoryId())
                .orElseThrow( ()-> new ResourceNotFoundException("Category","id",postDto.getCategoryId()));
       var post = mapper.map(postDto,Post.class);
       post.setCategory(category);
       var newPost = postRepository.save(post);

       return mapper.map(newPost,PostDto.class);
    }

    public PostResponse getAllPosts(int pageNo,int pageSize,String sortBy, String sortDir){

        var pageable = PageRequest.of(pageNo,pageSize,
                Sort.by(Sort.Direction.fromString(sortDir.toLowerCase()),
                        sortBy));

        Page<Post> postPage = postRepository.findAll(pageable);
        List<PostDto> content = postPage.stream().map(post -> mapper.map(post,PostDto.class))
                .collect(Collectors.toList());
        return new PostResponse(content,postPage.getNumber(),postPage.getSize(),
                postPage.getTotalElements(),postPage.getTotalPages(),postPage.isLast());
    }
    public PostDto getPostById(long id){
        return postRepository.findById(id)
                .map(post -> mapper.map(post,PostDto.class))
                .orElseThrow( ()-> new ResourceNotFoundException("Post","id",id));
    }



    public PostDto updatePost(long id,PostDto postDto) {
        var post = postRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Post","id",id));
        var category = categoryRepository.findById(postDto.getCategoryId())
                .orElseThrow( ()-> new ResourceNotFoundException("Category","id",postDto.getCategoryId()));

        post.setTitle(postDto.getTitle());;
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        post.setCategory(category);

        var newPost = postRepository.save(post);

        return mapper.map(newPost,PostDto.class);
    }

    public void deletePost(long id){
        postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post","id",id));
        postRepository.deleteById(id);
    }

    public List<PostDto> getPostsByCategoryId(long categoryId){
        var category = categoryRepository.findById(categoryId)
                .orElseThrow( ()-> new ResourceNotFoundException("Category","id",categoryId));

        return postRepository.getPostByCategoryId(categoryId).stream()
                .map(post -> mapper.map(post,PostDto.class))
                .collect(Collectors.toList());
    }

}
