package com.ziad.appsocial.controller;

import com.ziad.appsocial.models.ApiResponse;
import com.ziad.appsocial.models.Post;
import com.ziad.appsocial.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {
    @Autowired
    PostService postService;

    @PostMapping("/posts/user/{userId}")
    public ResponseEntity<Post> createPost(@RequestBody Post post, @PathVariable("userId") long userId) throws Exception {
        return new ResponseEntity<>(postService.createNewPost(post, userId), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/posts/{postId}/user/{userId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable long postId, @PathVariable() long userId) throws Exception {
        return new ResponseEntity<>(new ApiResponse(postService.deletePost(postId, userId), true), HttpStatus.OK);
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<Post> findPostByIdHandler(@PathVariable("postId") long postId) throws Exception {
        return new ResponseEntity<>(postService.findPostByID(postId) , HttpStatus.ACCEPTED);
    }
    @GetMapping("/posts/user/{userId}")
    public ResponseEntity<List<Post>> findUserPost(@PathVariable("userId") long userId){
        return new ResponseEntity<>(postService.findPostByUserId(userId) , HttpStatus.OK);
    }

    @GetMapping("/posts")
    public ResponseEntity<List<Post>> findAllPost(){
        return new ResponseEntity<>(postService.getAllPosts() , HttpStatus.OK);
    }

    @PutMapping("/posts/{postId}/user/{userId}")
    public ResponseEntity<Post> savedPostHandler(@PathVariable("postId") long postId , @PathVariable("userId") long userId) throws Exception {
        return new ResponseEntity<>(postService.savedPost(userId , postId) , HttpStatus.ACCEPTED);
    }

    @PutMapping("/posts/{postId}/user/{userId}")
    public ResponseEntity<Post> likePostHandler(@PathVariable("postId") long postId , @PathVariable("userId") long userId) throws Exception {
        return new ResponseEntity<>(postService.likePost(userId , postId) , HttpStatus.ACCEPTED);
    }
}
