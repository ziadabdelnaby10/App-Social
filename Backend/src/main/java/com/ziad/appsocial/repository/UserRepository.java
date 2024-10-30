package com.ziad.appsocial.repository;

import com.ziad.appsocial.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String Email);
    @Query("select u from User u where u.firstName LIKE %:query% OR u.lastName like %:query% OR u.email like %:query%")
    List<User> searchUser(@Param("query") String query);
}
