package com.example.rest.service;

import com.example.rest.model.User;
import com.example.rest.repository.RoleRepository;
import com.example.rest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UserService {
    UserRepository userRepository;
    RoleRepository roleRepository;
    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }
    public void saveUser(String login, String password){
        User user = new User(login, password);
        // dodajemy rolę użytkownika
        user.addRole(roleRepository.getOne(2L));
        // zapis do bazy danych
        System.out.println(user);
        userRepository.save(user);
    }
    public  void confirmUser(String login){
        User confirmedUser = userRepository.findFirstByLogin(login);
        confirmedUser.setActive(true);
        userRepository.save(confirmedUser);
    }
    public User loginUser(String login, String password){
        return userRepository.findFirstByLoginAndPassword(login,password);
    }
    public void addAdmin(Long id){
        User user = userRepository.getOne(id);
        user.addRole(roleRepository.getOne(1L));
        userRepository.save(user);
    }
}
