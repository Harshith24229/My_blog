package com.example.Myblog.service.impl;

import com.example.Myblog.entity.Post;
import com.example.Myblog.exception.ResourceNotFoundException;
import com.example.Myblog.payload.PostDto;
import com.example.Myblog.repository.PostRepository;
import com.example.Myblog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;
    @Override
    public PostDto createPost(PostDto postDto) {

        Post post = mapToEntity(postDto);

        Post savedPost = postRepository.save(post);
        PostDto postDto1 = maptoDto(savedPost);

        return postDto1;
    }

    @Override
    public List getAllPost() {

        return postRepository.findAll();
    }

    @Override
    public PostDto updatePostById(long id, PostDto postDto) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        post.setTitle(postDto.getTitle());
        post.setDescription((postDto.getDescription()));
        post.setContent(postDto.getContent());
        Post saved = postRepository.save(post);

        return maptoDto(saved);
    }

    @Override
    public PostDto getById(long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));

        return maptoDto(post);
    }

    @Override
    public void deletePostById(long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
         postRepository.delete(post);
    }

    //dtoto entity
    private Post mapToEntity(PostDto postDto) {
        Post post= new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        return post;
    }
    //entityto dto

    private PostDto maptoDto(Post post){
        PostDto postDto=new PostDto();

        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setDescription(post.getDescription());
        postDto.setContent(post.getContent());
        return postDto;
    }
}
