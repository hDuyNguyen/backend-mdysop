package com.project.mdyshop.config;

import com.project.mdyshop.model.Role;
import com.project.mdyshop.model.User;
import com.project.mdyshop.repository.UserRepository;
import com.project.mdyshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class SeedDataConfig implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {
            Set<Role> adminRole = new HashSet<>();
            adminRole.add(Role.ADMIN);

            User admin = User.builder()
                    .firstName("admin")
                    .lastName("admin")
                    .email("admin@admin.com")
                    .password(passwordEncoder.encode("1234"))
                    .roles(adminRole)
                    .build();

            userRepository.save(admin);
        }
    }
}
