package com.project.mdyshop.repository;

import com.project.mdyshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    @Query("select u from User u where u.id <> 1")
    List<User> getAllUser();
}
