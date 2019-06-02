package com.example.rest.controller;

import com.example.rest.model.User;
import com.example.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    // potwierdzenie            //zmienna ścieżki URL
    @PutMapping("/confirmation/{login}")    //zmienna pobrana z URL
    public void confirmation(@PathVariable String login){
        userService.confirmUser(login);
    }

    // logowanie
    @GetMapping("/login")
    public String login(String login, String password){
        User loggedUser = userService.loginUser(login,password);
        if(loggedUser != null){
            if(loggedUser.isActive()) {
                return "zalogowano";
            }
            return "konto jest nieaktywne";
        }
        return "błąd logowania";
    }
    // dodawanie administratora
    @PutMapping("/addAdmin/{id}")
    public void addAdmin(@PathVariable Long id){
        userService.addAdmin(id);
    }





}
