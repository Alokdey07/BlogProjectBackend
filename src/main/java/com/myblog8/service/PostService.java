package com.myblog8.service;

import com.myblog8.entity.Post;
import com.myblog8.payload.PostDto;

public interface PostService {

    PostDto createPost(PostDto postDto);

    void deletePostById(long userId);

    PostDto updatePost(long userId,PostDto postDto);
}
