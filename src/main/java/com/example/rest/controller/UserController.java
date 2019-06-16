package com.example.rest.controller;

import com.example.rest.controller.dto.UserDto;
import com.example.rest.model.User;
import com.example.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class UserController {

    // pole do wstrzykniecia
    UserService userService;

    // wstrzyknięcie zależności przez konstruktor
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // wejście na stronę rejestracji
    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("user", new UserDto());
        return "registerForm";
    }

    // obsługa wysłanego formularza
    @PostMapping("/register")
    public String register(@ModelAttribute("user") UserDto userDto){
        userService.saveUser(userDto);
        return "redirect:/";
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
