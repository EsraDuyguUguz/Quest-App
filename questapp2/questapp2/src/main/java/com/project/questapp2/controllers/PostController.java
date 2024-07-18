package com.project.questapp2.controllers;

import com.project.questapp2.entities.Post;
import com.project.questapp2.requests.PostCreateRequest;
import com.project.questapp2.requests.PostUpdateRequest;
import com.project.questapp2.responses.PostResponse;
import com.project.questapp2.services.PostService;


import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/posts")
public class PostController {

    private  PostService postService;

    public PostController(PostService postService){
        this.postService = postService;
    }

    @GetMapping
    private List<PostResponse> getAllPosts(@RequestParam Optional<Long> userId){
        return postService.getAllPosts(userId);
    }

    @PostMapping
    public Post createOnePost(@RequestBody PostCreateRequest newPostRequest){
        return postService.createOnePost(newPostRequest);
    }

    @GetMapping("/{postId}")
    public PostResponse getOnePost(@PathVariable Long postId){
        return postService.getOnePostByIdWithLikes(postId);
    }

    @PutMapping("/{postId}")
    public Post updateOnePost(@PathVariable Long postId, @RequestBody PostUpdateRequest updatePost){
        return postService.updateOnePostById(postId, updatePost);
    }

    @DeleteMapping("/{postId}")
    public void deleteOnePost(@PathVariable Long postId){
        postService.deleteOnePostById(postId);
    }
}
