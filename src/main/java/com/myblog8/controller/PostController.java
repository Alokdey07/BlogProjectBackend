package com.myblog8.controller;

import com.myblog8.entity.Post;
import com.myblog8.exception.ResourceNotFoundException;
import com.myblog8.payload.PostDto;
import com.myblog8.payload.PostResponse;
import com.myblog8.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
        PostDto dto = postService.createPost(postDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);


    }

    // Delete a blog post by its user ID via a DELETE request
    // Example URL: http://localhost:8080/api/posts/userId

    //http://localhost:8080/api/posts/userId
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteById(@PathVariable long userId) throws ResourceNotFoundException {
        // Call the service to delete the post by user ID
        postService.deletePostById(userId);
        // Return a success response with a message and HTTP status code 200 (OK)
        return new ResponseEntity<>("Post Deleted Success With Id:  " + userId, HttpStatus.OK);
    }


    // Update a blog post by its user ID via a PUT request
    // Example URL: http://localhost:8080/api/posts/userId
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{userId}")
    public ResponseEntity<PostDto> updateById(@PathVariable long userId, @RequestBody PostDto postDto) {
        // Call the service to update a blog post by user ID
        PostDto dto = postService.updatePost(userId, postDto);
        // Return the updated PostDto and HTTP status code 200 (OK)
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    // Retrieve a blog post by its post ID via a GET request
    // Example URL: http://localhost:8080/api/posts/postId
    @GetMapping("{postId}")
    public ResponseEntity<PostDto> findPostById(@PathVariable long postId) {
        // Call the service to find a blog post by post ID
        PostDto postByIdDto = postService.findPostById(postId);
        // Return the found PostDto and HTTP status code 200 (OK)

        return new ResponseEntity<PostDto>(postByIdDto, HttpStatus.OK);
    }

    // Retrieve a list of all blog posts via a GET request
    //http://localhost:8080/api/posts?pageNo=0&pageSize=10&sortBy=content&sortDir=asc
    @GetMapping
    public ResponseEntity<PostResponse> getAllPost(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String
                    sortDir) {
        // Call the service to get all blog posts
        PostResponse postResponse = postService.getAllPost(pageNo, pageSize, sortBy, sortDir);


        // Return the list of PostDto objects and HTTP status code 200 (OK)
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }


}
