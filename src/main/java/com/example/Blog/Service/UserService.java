package com.example.Blog.Service;

import com.example.Blog.DTOS.RegisterRequest;
import com.example.Blog.Models.User;
import com.example.Blog.Repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

@Service
public class UserService {

    private PasswordEncoder encoder;

    private UserRepository userRepository;

    public UserService(PasswordEncoder encoder, UserRepository userRepository) {
        this.encoder = encoder;
        this.userRepository = userRepository;
    }


    public User signup(RegisterRequest registerRequest){

        User created = new User();
        created.setUsername(registerRequest.getUsername());
        created.setEmail(registerRequest.getEmail());
        created.setPassword(encoder.encode(registerRequest.getPassword()));
        created.setRoles(registerRequest.getRoles());
        return userRepository.save(created);

    }

    public Optional<org.springframework.security.core.userdetails.User>getCurrentUser(){
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        return Optional.of(principal);
    }
}
