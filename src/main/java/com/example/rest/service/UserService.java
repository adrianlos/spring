package com.example.rest.service;

import com.example.rest.controller.dto.UserDto;
import com.example.rest.model.User;
import com.example.rest.repository.RoleRepository;
import com.example.rest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    UserRepository userRepository;
    RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public void saveUser(UserDto userDto) {

        String encodedPassword = new BCryptPasswordEncoder().encode(userDto.getPassword());

        User user = new User(userDto.getName(),
                userDto.getLastName(),
                userDto.getEmail(),
                encodedPassword);

        // dodajemy rolę użytkownika
        user.addRole(roleRepository.getOne(1L));

        // zapis do bazy danych
        System.out.println(user);
        userRepository.save(user);
    }

    public void confirmUser(String login) {
        User confirmedUser = userRepository.findFirstByEmail(login);
        confirmedUser.setActive(true);
        userRepository.save(confirmedUser);
    }

    public User loginUser(String login, String password) {
        return userRepository.findFirstByEmailAndPassword(login, password);
    }

    public void addAdmin(Long id) {
        User user = userRepository.getOne(id);
        user.addRole(roleRepository.getOne(1L));
        userRepository.save(user);
    }
}
