package com.myblog8.controller;

import com.myblog8.entity.Post;
import com.myblog8.exception.ResourceNotFoundException;
import com.myblog8.payload.PostDto;
import com.myblog8.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
public class PostController {



    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // create blog post rest api
    //http://localhost:8080/api/posts
    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){
        PostDto dto = postService.createPost(postDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);


    }

    //http://localhost:8080/api/posts/userId
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteById(@PathVariable long userId) throws ResourceNotFoundException {
        postService.deletePostById(userId);

        return new ResponseEntity<>("Post Deleted Success With Id:  "+ userId,HttpStatus.OK);
    }



    @PutMapping("/{userId}")
    public ResponseEntity<PostDto> updateById(@PathVariable long userId,@RequestBody PostDto postDto){

        PostDto dto = postService.updatePost(userId,postDto);

        return new ResponseEntity<>(dto,HttpStatus.OK);
    }


}
