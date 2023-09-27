package com.myblog8.service.impl;

import com.myblog8.entity.Comment;
import com.myblog8.entity.Post;
import com.myblog8.exception.ResourceNotFoundException;
import com.myblog8.payload.CommentDto;
import com.myblog8.repository.CommentRepository;
import com.myblog8.repository.PostRepository;
import com.myblog8.service.CommentService;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {


    private CommentRepository commentRepo;

    private PostRepository postRepo;

    public CommentServiceImpl(CommentRepository commentRepo,PostRepository postRepo) {
        this.commentRepo = commentRepo;
        this.postRepo=postRepo;
    }

    @Override
    public CommentDto saveComment(CommentDto dto, long postId) {
        Post post= postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post Not found with Postid: "+postId));
        Comment comment=new Comment();
        comment.setId(dto.getId());
        comment.setName(dto.getName());
        comment.setEmail(dto.getEmail());
        comment.setBody(dto.getBody());
        comment.setPost(post);
        commentRepo.save(comment);
        return mapToDto(comment);
    }

    public CommentDto mapToDto(Comment comment){
        CommentDto dto=new CommentDto();
        dto.setId(comment.getId());
        dto.setName(comment.getName());
        dto.setEmail(comment.getEmail());
        dto.setBody(comment.getBody());
        return dto;

    }

}
