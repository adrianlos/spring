package com.example.rest.controller;

import com.example.rest.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.*;

@RestController
public class MainController {

    private List<String> logins = new ArrayList<>(Arrays.asList("mk","kk","ee","rr"));

    @GetMapping("/")
    public String hello(){
        return "HELLO WORLD";
    }
    @GetMapping("/logins")
    public List<String> getLogins(){
        return logins;
    }
    @GetMapping("/addUser")
    public User addUser(){
        return new User("mk","mk", LocalDateTime.now(),true);
    }

}
