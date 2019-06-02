package com.example.rest.service;

import com.example.rest.model.User;
import com.example.rest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UserService {
    UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(String login, String password){
        User user = new User(login, password);
        // zapis do bazy danych
        userRepository.save(user);
    }
    public User loginUser(String login, String password){
        return userRepository.findFirstByLoginAndPassword(login,password);
    }
}
