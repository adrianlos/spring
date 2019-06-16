package com.example.rest.controller.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserDto {

    @NotBlank(message = "Insert your name")
    private String name;

    private String lastName;
    private String email;
    private String password;
}
