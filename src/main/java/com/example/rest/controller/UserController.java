package com.example.rest.controller;

import com.example.rest.model.User;
import com.example.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    // pole do wstrzykniecia
    UserService userService;

    // wstrzyknięcie zależności przez konstruktor
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // rejestracja
    @PostMapping("/registration")
    public void registration(String login, String password){
        userService.saveUser(login, password);
    }
    // logowanie
    @GetMapping("/login")
    public boolean login(String login, String password){
        if(userService.loginUser(login,password) != null){
            return true;
        }
        return false;
    }
}
