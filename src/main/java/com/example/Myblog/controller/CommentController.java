package com.example.Myblog.controller;

import com.example.Myblog.payload.CommentDto;
import com.example.Myblog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }
    @PostMapping("/posts/{postId}/comments")

    public ResponseEntity<CommentDto> createComment
            (@PathVariable(name = "postId") long postId, @RequestBody CommentDto commentDto) {

        CommentDto comment = commentService.createComment(postId, commentDto);
        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }

    @GetMapping("/posts/{postId}/comments")

    public List<CommentDto> getAllCommentByPostId(@PathVariable(name = "postId")long postId){
       return  commentService.getAllCommentsByPostId(postId);
    }
    @GetMapping("/posts/{postId}/comments/{id}")

    public ResponseEntity<CommentDto>getCommentById(@PathVariable(name = "postId")long postId ,@PathVariable(name="id")long commentId){
        CommentDto commentById = commentService.getCommentById(postId, commentId);
return new ResponseEntity<>(commentById,HttpStatus.OK);
    }

}
