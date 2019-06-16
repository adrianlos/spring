package com.example.rest.controller.dto;

import com.example.rest.model.enums.CategoryEnum;
import lombok.Data;

import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class PostDto {

    @NotBlank(message = "Title is required")
    @Size(min = 5, message = "Title is too short")
    private String title;

    @NotBlank(message = "Content is required")
    @Size(min = 10, message = "Content is too short")
    private String content;

    private CategoryEnum category;
}
