package com.myblog8.service;

import com.myblog8.payload.CommentDto;

import java.util.List;

public interface CommentService {
    // This method is responsible for saving a comment associated with a specific blog post.
    // It takes a CommentDto object representing the comment content and a postId to associate the comment with the post.
    // The method returns a CommentDto object representing the saved comment.
    CommentDto saveComment(CommentDto dto,long postId);


    void deleteByCommentId(long commentId);

    CommentDto updateCommentById(CommentDto commentDto, long id);

    List<CommentDto> getAllComment();
}

