package com.ziad.appsocial.controller;

import com.ziad.appsocial.repository.UserRepository;
import com.ziad.appsocial.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ziad.appsocial.models.User;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.getAllUsers();

    }

    @GetMapping("/users/{userid}")
    public User getUserById(@PathVariable("userid") long id) throws Exception {
        return userService.findUserById(id);
    }

    @GetMapping("/users/{email}")
    public User getUserByEmail(@PathVariable("email")String email) throws Exception {
        return userService.findUserByEmail(email);
    }

    @PutMapping("/users/{userid}")
    public User updateUser(@RequestBody User user, @PathVariable("userid") long id) throws Exception {
        return userService.updateUser(user , id);
    }

    @GetMapping("/users/follow/{userid1}/{userid2}")
    public User followUserHandler(@PathVariable("userid1") Long userId1 , @PathVariable("userid2") Long userId2) throws Exception {
        return userService.followUser(userId1 , userId2);
    }

    @GetMapping("/users/search")
    public List<User> searchUser(@RequestParam("query") String query){
        return userService.searchUser(query);
    }

    @DeleteMapping("/users/{userid}")
    public String deleteUSer(@PathVariable("userid") long userId) throws Exception {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new Exception("User doesn't exist with id " + userId);
        }
        userRepository.delete(user.get());
        return "Deleted Successfully";
    }
}
