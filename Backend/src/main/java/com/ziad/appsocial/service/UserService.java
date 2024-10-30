package com.ziad.appsocial.service;

import com.ziad.appsocial.models.User;

import java.util.List;

public interface UserService {

    public List<User> getAllUsers();
    public User registerUser(User user);

    public User findUserById(long userId) throws Exception;

    public User findUserByEmail(String email) throws Exception;

    public User followUser(long userId1 , long userId2) throws Exception;

    public User updateUser(User user , Long userId) throws Exception;

    public List<User> searchUser(String query);
}
