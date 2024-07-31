package com.example.Myblog.service.impl;

import com.example.Myblog.entity.Comment;
import com.example.Myblog.entity.Post;
import com.example.Myblog.exception.ResourceNotFoundException;
import com.example.Myblog.payload.CommentDto;
import com.example.Myblog.repository.CommentRepository;
import com.example.Myblog.repository.PostRepository;
import com.example.Myblog.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private PostRepository postRepository;
    private ModelMapper modelMapper;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository, ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
        Comment comment = mapToEntity(commentDto);

        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", postId));

        comment.setPost(post);
        Comment save = commentRepository.save(comment);

        return mapToDto(save);
    }
    //dto to entity
    private Comment mapToEntity(CommentDto commentDto) {
        Comment map = modelMapper.map(commentDto, Comment.class);
        return map;
    }
    //entity to dto

    private CommentDto mapToDto(Comment comment){
        CommentDto map1 = modelMapper.map(comment, CommentDto.class);
        return map1;
    }


}
