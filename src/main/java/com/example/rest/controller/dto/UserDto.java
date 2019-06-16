package com.example.rest.controller.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class UserDto {

    @NotBlank(message = "Insert your name")
    private String name;

    @NotBlank(message = "Insert your last name")
    private String lastName;

    @NotBlank(message = "Insert your email")
    @Email(message = "Your email is not valid")
    private String email;

    @NotBlank(message = "Password cant be empty")
    @Size(min = 5, message = "Password too short")
    @Pattern(regexp = "([A-Z]+.*[0-9]+)|([0-9]+.*[A-Z])", message = "Your password must have one capital letter and one digit")
    private String password;
}
