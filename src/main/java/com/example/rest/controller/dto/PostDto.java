package com.example.rest.controller.dto;

import com.example.rest.model.enums.CategoryEnum;
import lombok.Data;

import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;

@Data
public class PostDto {

    private String title;

    private String content;

    private CategoryEnum category = CategoryEnum.JAVA;
}
