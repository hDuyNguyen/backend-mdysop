package com.project.mdyshop.repository;

import com.project.mdyshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findByEmail(String email);
    User findUserById(Long id);
}
