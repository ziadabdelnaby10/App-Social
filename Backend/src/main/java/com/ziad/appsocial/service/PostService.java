package com.ziad.appsocial.service;

import com.ziad.appsocial.models.Post;

import java.util.List;

public interface PostService {
    List<Post> getAllPosts();
    List<Post> findPostByUserId(long userId);
    Post findPostByID(long postId) throws Exception;
    Post createNewPost(Post post , long userId) throws Exception;
    String deletePost(long postId , long userId) throws Exception;
    Post savedPost(long userId , long postId) throws Exception;
    Post likePost(long userId , long postId) throws Exception;
    //Post commentPost(long userId , long postId);
}
