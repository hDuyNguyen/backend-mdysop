package com.project.mdyshop.service;

import com.project.mdyshop.exception.UserException;
import com.project.mdyshop.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {

    UserDetailsService userDetailsService();

    User findUserByToken(String token) throws UserException;

    List<User> getAllUser();

    User findUserById(Long userId) throws UserException;
}
