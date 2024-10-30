package com.ziad.appsocial.service;

import com.ziad.appsocial.models.Post;
import com.ziad.appsocial.models.User;
import com.ziad.appsocial.repository.PostRepository;
import com.ziad.appsocial.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PostServiceImplementation implements PostService {
    @Autowired
    PostRepository postRepository;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public List<Post> findPostByUserId(long userId) {
        return postRepository.findPostByUserId(userId);
    }

    @Override
    public Post findPostByID(long postId) throws Exception {
        Optional<Post> post = postRepository.findById(postId);
        if (post.isPresent())
            return post.get();
        throw new Exception("Post not found with id " + postId);
    }

    @Override
    public Post createNewPost(Post post, long userId) {
        return postRepository.save(post);
    }

    @Override
    public String deletePost(long postId, long userId) throws Exception {
        Optional<Post> post = postRepository.findById(postId);
        if (post.isEmpty())
            throw new Exception("No Post to delete wit id " + postId);
        User user = userService.findUserById(userId);
        if (!Objects.equals(post.get().getUser().getId(), user.getId()))
            throw new Exception("You can't delete another Users post");
        postRepository.deleteById(postId);
        return "Post Deleted Successfully";
    }

    @Override
    public Post savedPost(long userId, long postId) throws Exception{
        Post post = findPostByID(postId);
        User user = userService.findUserById(userId);
        if(user.getSavedPost().contains(post))
            user.getSavedPost().remove(post);
        else
            user.getSavedPost().add(post);
        return post;
    }

    @Override
    public Post likePost(long userId, long postId) throws Exception {
        Post post = findPostByID(postId);
        User user = userService.findUserById(userId);
        if (post.getLiked().contains(user)) {
            post.getLiked().remove(user);
        } else {
            post.getLiked().add(user);
        }
        return postRepository.save(post);
    }
}
