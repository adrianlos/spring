package com.example.rest.controller;

import com.example.rest.model.Post;
import com.example.rest.model.enums.CategoryEnum;
import com.example.rest.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class PostController {
    PostService postService;
    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/addPost/{user_id}")
    public void addPost(
            String title,
            String content,
            CategoryEnum category,
            @PathVariable Long user_id){
        postService.addPost(title, content, category, user_id);
    }
    @GetMapping("/allPosts")
    public List<Post> getAllPosts(){
        return postService.getAllPosts();
    }
}
