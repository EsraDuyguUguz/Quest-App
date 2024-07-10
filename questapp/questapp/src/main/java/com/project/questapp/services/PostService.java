package com.project.questapp.services;

import com.project.questapp.entities.Post;
import com.project.questapp.entities.User;
import com.project.questapp.requests.PostCreateRequest;
import org.springframework.stereotype.Service;
import com.project.questapp.repository.PostRepository;
import com.project.questapp.requests.PostUpdateRequest;
import com.project.questapp.responses.LikeResponse;
import com.project.questapp.responses.PostResponse;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {
    private  PostRepository postRepository;
    private  UserService userService;
    private  LikeService likeService;

    public PostService(PostRepository postRepository,UserService userService){
        this.postRepository = postRepository;
        this.userService  = userService;
    }

    public List<PostResponse> getAllPosts(Optional<Long> userId) {
        List<Post> list;
        if(userId.isPresent()){
            list = postRepository.findByUserId(userId.get());
        } else
            list = postRepository.findAll();
        return list.stream().map(p -> {
            List<LikeResponse> likes = likeService.getAllLikesWithParam(Optional.ofNullable(null),Optional.of(p.getId()));
            return new PostResponse(p,likes);
        }).collect(Collectors.toList());
    }

    public Post getOnePostById(Long postId) {
        return postRepository.findById(postId).orElse(null);
    }

    public Post createOnePost(PostCreateRequest newPostCreateRequest) {
        User user = userService.getOneUserById(newPostCreateRequest.getUserId());
        if(user==null){
           return null;
        }
        Post toSave = new Post();
        toSave.setId(newPostCreateRequest.getId());
        toSave.setText(newPostCreateRequest.getText());
        toSave.setTitle(newPostCreateRequest.getTitle());
        toSave.setUser(user);
        return postRepository.save(toSave);
    }

    public Post updateOnePostById(Long postId, PostUpdateRequest postUpdateRequest){
        Optional<Post> post = postRepository.findById(postId);
        if(post.isPresent()){
            Post toUpdate = post.get();
            toUpdate.setText(postUpdateRequest.getText());
            toUpdate.setTitle(postUpdateRequest.getTitle());
            postRepository.save(toUpdate);
            return toUpdate;
        }
        return null;
    }

    public void deleteOnePostById(Long postId){
        postRepository.deleteById(postId);
    }
}