package com.myblog8.service;

import com.myblog8.payload.PostDto;
import com.myblog8.payload.PostResponse;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto);

    void deletePostById(long postId);

    PostDto getPostByPostId(long postId);

    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

    PostDto updatePostById(long postId, PostDto postDto);
}
