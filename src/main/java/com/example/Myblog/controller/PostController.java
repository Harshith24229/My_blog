package com.example.Myblog.controller;

import com.example.Myblog.entity.Post;
import com.example.Myblog.payload.PostDto;
import com.example.Myblog.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }
    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
        PostDto post = postService.createPost(postDto);
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }

    @GetMapping
    public List<PostDto> getAllPost() {
        return postService.getAllPost();
    }
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getById(@PathVariable(name = "id")long id ){
        PostDto byId = postService.getById(id);
        return new ResponseEntity<>(byId,HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<PostDto>updatePostById(@PathVariable(name = "id")long id,@RequestBody PostDto postDto){
        PostDto postDto1 = postService.updatePostById(id, postDto);
        return new ResponseEntity<>(postDto1,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public String  deletePostById(@PathVariable(name = "id") long id){
        postService.deletePostById(id);
        return "post is deleted "+id;
    }

}
