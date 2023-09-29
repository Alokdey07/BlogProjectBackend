package com.myblog8.service.impl;

import com.myblog8.entity.Post;
import com.myblog8.exception.ResourceNotFoundException;
import com.myblog8.payload.PostDto;
import com.myblog8.repository.PostRepository;
import com.myblog8.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class PostServiceImpl implements PostService {


    private PostRepository postRepository;

    // Here initialiasation through constructor is done it is constructor injection
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    // Create a new post based on the provided PostDto
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
    // Delete a post by its ID
    @Override
    public void deletePostById(long userId) {

        //first find a post exist or not
        postRepository.findById(userId).orElseThrow(
                ()->new ResourceNotFoundException("Post not found with userid:"+userId)

        );
        postRepository.deleteById(userId);
    }
    // Update a post with a new PostDto by its ID
    @Override
    public PostDto updatePost(long userId,PostDto postDto) {
        // Find the post by its ID
        Post post = postRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("Post not found with ID: " + userId)
        );
      // Update the post entity with the data from the PostDto
        Post post1=new Post();
        post1.setId(post.getId());
        post1.setTitle(postDto.getTitle());
        post1.setDescription(postDto.getDescription());
        post1.setContent(postDto.getContent());
        // Save the updated post in the repository
        postRepository.save(post1);

        //PostDto dto=new PostDto();
        // Convert the updated entity back to a DTO and return it
//        dto.setId(post1.getId());
//        dto.setTitle(post1.getTitle());
//        dto.setDescription(post1.getDescription());
//        dto.setContent(post1.getContent());

        //this can be also done in single line

        return mapToDto(post1);
    }

    // Find a post by its ID and return it as a PostDto
    @Override
    public PostDto findPostById(long postId) {
        // Find the post by its ID and throw an exception if not found
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post not exixt with id no: " + postId));
        // Convert the found entity to a DTO and return it
        return mapToDto(post);

    }
    // Get a list of all posts and return them as a list of PostDto objects
    @Override
    public List<PostDto> getAllPost(int pageNo, int pageSize) {

        PageRequest pageable = PageRequest.of(pageNo, pageSize);
        Page<Post> all = postRepository.findAll(pageable);
        List<Post> posts = all.getContent();

        // Find all posts in the repository
       // List<Post> findAllPost = postRepository.findAll();
        // Convert the list of entities to a list of DTOs and return it
        List<PostDto> postDto = posts.stream().map(post -> mapToDto(post)).collect(Collectors.toList());
        return postDto;
    }

    // Helper method to map a Post entity to a PostDto
    public PostDto mapToDto(Post post){

        PostDto dto=new PostDto();
        dto.setId(post.getId());
        dto.setContent(post.getContent());
        dto.setDescription(post.getDescription());
        dto.setTitle(post.getTitle());

        return dto;

    }


}
