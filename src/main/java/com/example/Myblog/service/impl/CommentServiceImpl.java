package com.example.Myblog.service.impl;

import com.example.Myblog.entity.Comment;
import com.example.Myblog.entity.Post;
import com.example.Myblog.exception.BlogAPIException;
import com.example.Myblog.exception.ResourceNotFoundException;
import com.example.Myblog.payload.CommentDto;
import com.example.Myblog.repository.CommentRepository;
import com.example.Myblog.repository.PostRepository;
import com.example.Myblog.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    public List<CommentDto> getAllCommentsByPostId(long postId) {
        List<Comment> comments = commentRepository.findBypostId(postId);

        return comments.stream().map(comment -> mapToDto(comment)).collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentById(long postId, long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", postId));
        Comment comment = commentRepository.findById(commentId).orElseThrow(() ->
                new ResourceNotFoundException("Comment", "id", commentId));

        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");

        }


        return mapToDto(comment);
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
