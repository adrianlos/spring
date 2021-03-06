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
    public String register(@ModelAttribute("user") @Valid UserDto userDto,
                           BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "registerForm";
        }

        userService.saveUser(userDto);
        return "redirect:/";
    }

    // wejście na stronę logowania
    @GetMapping("/login")
    public String login(Model model){
        return "loginForm";
    }

    // dodawanie administratora
    @PutMapping("/addAdmin/{id}")
    public void addAdmin(@PathVariable Long id){
        userService.addAdmin(id);
    }

}
