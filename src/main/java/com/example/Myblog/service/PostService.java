package com.example.Myblog.service;

import com.example.Myblog.payload.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);

    List getAllPost();

    PostDto updatePostById(long id, PostDto postDto);

    PostDto getById(long id);

    void deletePostById(long id);
}
