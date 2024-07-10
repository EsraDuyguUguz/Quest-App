package com.project.questapp.services;

import com.project.questapp.entities.Like;
import com.project.questapp.repository.LikeRepository;
import com.project.questapp.responses.LikeResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LikeService {
    private LikeRepository likeRepository;
    private UserService userService;
    private PostService postService;


    public LikeService(LikeRepository likeRepository){
        this.likeRepository = likeRepository;

    }

    public List<Like> getAllLikes(Optional<Long> userId,Optional<Long> postId){
        if(userId.isPresent()){
            return likeRepository.findByUserId(userId.get());
        }
        if(postId.isPresent()){
            return likeRepository.findByPostId(postId.get());
        }
        return likeRepository.findAll();
    }

    public Like getOneLikeById(Long likeId){
        return likeRepository.findById(likeId).orElse(null);
    }

    public List<LikeResponse> getAllLikesWithParam(Optional<Long> userId, Optional<Long> postId){
        List<Like> list;
        if(userId.isPresent() && postId.isPresent()){
            list = likeRepository.findByUserIdAndPostId(userId.get(),postId.get());
        } else if (userId.isPresent()) {
            list = likeRepository.findByUserId(userId.get());
        } else if (postId.isPresent()) {
            list = likeRepository.findByPostId(postId.get());
        }else
            list = likeRepository.findAll();
        return list.stream().map(like-> new LikeResponse(like)).collect(Collectors.toList());
    }
}
