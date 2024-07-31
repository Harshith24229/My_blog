package com.example.Myblog.service;

import com.example.Myblog.payload.CommentDto;

public interface CommentService {
     CommentDto createComment(long postId, CommentDto commentDto) ;

}
