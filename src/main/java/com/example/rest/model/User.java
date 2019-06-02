package com.example.rest.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class User {
    String login;
    String password;
    LocalDateTime registration_date;
    boolean active;

}
