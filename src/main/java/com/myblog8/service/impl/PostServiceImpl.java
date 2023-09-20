package com.myblog8.service.impl;

import com.myblog8.entity.Post;
import com.myblog8.exception.ResourceNotFoundException;
import com.myblog8.payload.PostDto;
import com.myblog8.repository.PostRepository;
import com.myblog8.service.PostService;
import org.springframework.stereotype.Service;



@Service
public class PostServiceImpl implements PostService {


    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {

        // convert DTO to entity
        Post post=new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post updatedPost = postRepository.save(post);
        // convert entity to DTO
        PostDto dto=new PostDto();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setDescription(post.getDescription());
        dto.setContent(post.getContent());

        return dto;


    }

    @Override
    public void deletePostById(long userId) {

        postRepository.findById(userId).orElseThrow(
                ()->new ResourceNotFoundException("Post not found:"+userId)

        );
        postRepository.deleteById(userId);
    }

    @Override
    public PostDto updatePost(long userId,PostDto postDto) {

        Post post = postRepository.findById(userId).get();

        Post post1=new Post();
        post1.setId(post.getId());
        post1.setTitle(postDto.getTitle());
        post1.setDescription(postDto.getDescription());
        post1.setContent(postDto.getContent());

        postRepository.save(post1);

        PostDto dto=new PostDto();

        dto.setId(post1.getId());
        dto.setTitle(post1.getTitle());
        dto.setDescription(post1.getDescription());
        dto.setContent(post1.getContent());


        return dto;
    }


}
