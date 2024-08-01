package com.example.Myblog.service;

import com.example.Myblog.payload.CommentDto;

import java.util.List;

public interface CommentService {
     CommentDto createComment(long postId, CommentDto commentDto) ;

     List<CommentDto> getAllCommentsByPostId(long postId);

     CommentDto getCommentById(long postId, long commentId);
}
