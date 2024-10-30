package com.ziad.appsocial.service;

import com.ziad.appsocial.models.User;
import com.ziad.appsocial.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService{
    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User registerUser(User user) {
        User newUser = new User();
        newUser.setUser(user);
        return userRepository.save(newUser);
    }

    @Override
    public User findUserById(long userId) throws Exception{
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent())
            return user.get();
        throw new Exception("User doesn't exist with userId " + userId);
    }

    @Override
    public User findUserByEmail(String email) throws Exception{
        User user = userRepository.findByEmail(email);
        if (user != null)
            return user;
        throw new Exception("User doesn't exist with Email " + email);
    }

    @Override
    public User followUser(long userId1, long userId2) throws Exception {
        User user1 = findUserById(userId1) , user2 = findUserById(userId2);

        user1.getFollowings().add(user2.getId());
        user2.getFollowers().add(user1.getId());

        userRepository.save(user1);
        userRepository.save(user2);
        return user1;
    }

    @Override
    public User updateUser(User user,Long userId) throws Exception {
        Optional<User> currentUser = userRepository.findById(userId);
        if (currentUser.isEmpty())
            throw new Exception("User doesn't exist with id " + userId);
        return userRepository.save(user);
    }

    @Override
    public List<User> searchUser(String query) {
        return userRepository.searchUser(query);
    }
}
