package com.myblog8.service;

import com.myblog8.entity.Post;
import com.myblog8.payload.PostDto;

import java.util.List;

public interface PostService {
    // Create a new blog post
    PostDto createPost(PostDto postDto);

    // Delete a blog post by its user ID
    void deletePostById(long userId);

    // Update a blog post by its user ID with new data
    PostDto updatePost(long userId,PostDto postDto);

    // Find a blog post by its post ID
    PostDto findPostById(long postId);

    // Retrieve a list of all blog posts
    List<PostDto> getAllPost();
}
