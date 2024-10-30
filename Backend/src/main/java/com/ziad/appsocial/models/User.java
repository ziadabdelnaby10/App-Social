package com.ziad.appsocial.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String gender;
    private List<Long> followers = new ArrayList<>();
    private List<Long> followings = new ArrayList<>();
    private List<Post> savedPost = new ArrayList<>();

    public User(){

    }

    public User(Long id, String firstName, String lastName, String email, String password, String gender, List<Long> followers, List<Long> followings , List<Post> savedPost) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.followers = followers;
        this.followings = followings;
        this.savedPost = savedPost;
    }

    public List<Post> getSavedPost() {
        return savedPost;
    }

    public void setSavedPost(List<Post> savedPost) {
        this.savedPost = savedPost;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<Long> getFollowers() {
        return followers;
    }

    public void setFollowers(List<Long> followers) {
        this.followers = followers;
    }

    public List<Long> getFollowings() {
        return followings;
    }

    public void setFollowings(List<Long> followings) {
        this.followings = followings;
    }


    public String getName() {
        return firstName + " " + lastName;
    }

    public String getEmail() {
        return email;
    }

    public boolean setEmail(String email) {
        if (checkEmailFormat(email)) {
            this.email = email;
            return true;
        }
        return false;
    }

    public boolean checkEmailFormat(String email) {
        return email.contains("@gmail.com") || email.contains("@rocketmail.com") || email.contains("@yahoo.com");
    }

    public String getPassword() {
        return password;
    }

    public boolean setPassword(String password) {
        if (checkPassword(password)) {
            this.password = password;
            return true;
        }
        return false;
    }

    public boolean checkPassword(String password) {
        return password.length() >= 6;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setUser(User newUser){
        this.id = newUser.id;
        this.firstName = newUser.firstName;
        this.lastName = newUser.lastName;
        this.email = newUser.email;
        this.password = newUser.password;
        this.gender = newUser.gender;
        this.followers = newUser.followers;
        this.followings = newUser.followings;
    }
}
