package com.project.mdyshop.service.imp;

import com.project.mdyshop.config.JwtTokenProvider;
import com.project.mdyshop.exception.UserException;
import com.project.mdyshop.model.User;
import com.project.mdyshop.repository.UserRepository;
import com.project.mdyshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                User user = userRepository.findByEmail(username);

                if (user != null) {
                   return user;
                }
                throw new UsernameNotFoundException("User not found: " + username);

            }
        };
    }

    @Override
    public User findUserByToken(String token) throws UserException {
        String email = jwtTokenProvider.getUserFromToken(token);

        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new UserException("User not found with email: " + email);
        }
        else {
            return user;
        }
    }

    @Override
    public List<User> getAllUser() {
        return null;
    }

    @Override
    public User findUserById(Long userId) throws UserException{
        Optional<User> opt = userRepository.findById(userId);

        if (opt.isPresent()) {
            return opt.get();
        }
        else {
            throw new UserException("User not found with ID:" + userId);
        }
    }
}
