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
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }
    //http://localhost:8080/api/comments/{postId}
    @PostMapping("{postId}")
    public ResponseEntity<CommentDto> saveComment
            (@RequestBody CommentDto commentDto, @PathVariable long postId){
        CommentDto dto = commentService.saveComment(commentDto, postId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteComment(@PathVariable long id){
        commentService.deleteByCommentId(id);
        return new ResponseEntity<>("Comment is deleted",HttpStatus.OK);
    }
    @PutMapping("{id}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable long id,@RequestBody CommentDto commentDto){
        CommentDto dto = commentService.updateCommentById(id, commentDto);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
}
