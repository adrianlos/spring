package com.example.rest.controller.dto;

import lombok.Data;

@Data
public class UserDto {

    private String name;
    private String lastName;
    private String email;
    private String password;
}
