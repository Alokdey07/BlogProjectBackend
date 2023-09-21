package com.myblog8.controller;

import com.myblog8.entity.Post;
import com.myblog8.exception.ResourceNotFoundException;
import com.myblog8.payload.PostDto;
import com.myblog8.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {



    private PostService postService;

    // Constructor injection for PostService
    public PostController(PostService postService) {
        this.postService = postService;
    }

    // Create a new blog post via a POST request
    // Example URL: http://localhost:8080/api/posts
    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){
        PostDto dto = postService.createPost(postDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);


    }

    // Delete a blog post by its user ID via a DELETE request
    // Example URL: http://localhost:8080/api/posts/userId

    //http://localhost:8080/api/posts/userId
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteById(@PathVariable long userId) throws ResourceNotFoundException {
        // Call the service to delete the post by user ID
        postService.deletePostById(userId);
        // Return a success response with a message and HTTP status code 200 (OK)
        return new ResponseEntity<>("Post Deleted Success With Id:  "+ userId,HttpStatus.OK);
    }


    // Retrieve a list of all blog posts via a GET request
    @PutMapping("/{userId}")
    public ResponseEntity<PostDto> updateById(@PathVariable long userId,@RequestBody PostDto postDto){
        // Call the service to get all blog posts
        PostDto dto = postService.updatePost(userId,postDto);
        // Return the list of PostDto objects and HTTP status code 200 (OK)
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    @GetMapping("{postId}")
    public ResponseEntity<PostDto> findPostById(@PathVariable long postId){
        PostDto postByIdDto = postService.findPostById(postId);


        return new ResponseEntity<PostDto>(postByIdDto,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPost(){

        List<PostDto> allPost = postService.getAllPost();

        return new ResponseEntity<>(allPost,HttpStatus.OK);
    }


}
