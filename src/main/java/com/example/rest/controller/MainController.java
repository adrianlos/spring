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
    @PostMapping("/addUser")
    public User addUser(String login, String password){
        return new User(login ,password, LocalDateTime.now(),true);
    }


}
