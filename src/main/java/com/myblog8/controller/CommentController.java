package com.myblog8.controller;

import com.myblog8.payload.CommentDto;
import com.myblog8.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private CommentService commentService;

    // http://localhost:8080/api/comments/{postId}
    @PostMapping("/{postId}")
    public ResponseEntity<CommentDto> saveComment(@RequestBody CommentDto commentDto, @PathVariable long postId){

        CommentDto commentDto1 = commentService.saveComment(commentDto, postId);

        return new ResponseEntity<>(commentDto1, HttpStatus.OK);
    }
}
