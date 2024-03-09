package com.project.mdyshop.service.imp;

import com.project.mdyshop.config.JwtTokenProvider;
import com.project.mdyshop.dto.request.SigninRequest;
import com.project.mdyshop.dto.request.SignupRequest;
import com.project.mdyshop.dto.response.AuthResponse;
import com.project.mdyshop.exception.UserException;
import com.project.mdyshop.model.Role;
import com.project.mdyshop.model.User;
import com.project.mdyshop.repository.UserRepository;
import com.project.mdyshop.service.AuthService;
import com.project.mdyshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImp implements AuthService {

    @Autowired
    UserService userService;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;

    @Override
    public AuthResponse signin(SigninRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        User user = userRepository.findByEmail(request.getEmail());
        if (user == null) {
            throw new IllegalArgumentException("Invalid email or password");
        }
        String jwt = jwtTokenProvider.generateToken(user);

        return new AuthResponse(jwt, "Signin Successfully");
    }

    @Override
    public AuthResponse signup(SignupRequest request) throws UserException{
        User userEmail = userRepository.findByEmail(request.getEmail());

        if (userEmail != null) {
            throw new UserException("Email is already used with another account");
        }

        Set<Role> roles = new HashSet<>();
        roles.add(Role.USER);
        var user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .roles(roles)
                .build();

        user = userRepository.save(user);
        var jwt = jwtTokenProvider.generateToken(user);

        return new AuthResponse(jwt, "Signup Successfully");
    }
}
