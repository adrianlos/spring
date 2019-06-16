package com.example.rest.controller;

import com.example.rest.model.User;
import com.example.rest.repository.UserRepository;
import com.example.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/rest")
public class MainController {

    @Autowired
    private UserRepository userRepository;

    private List<String> logins = new ArrayList<>(Arrays.asList("mk","kk","ee","rr"));
    private User user;

    @GetMapping("/")
    public String hello(){
        return "HELLO WORLD";
    }

    @GetMapping("/users")
    public List<User> users(){
        return userRepository.findAll();
    }
}
