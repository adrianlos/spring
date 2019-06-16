package com.example.rest.controller;

import com.example.rest.model.Comment;
import com.example.rest.model.Post;
import com.example.rest.model.enums.CategoryEnum;
import com.example.rest.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class PostController {
    PostService postService;
    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/")
    public String getAllPosts(Model model, Authentication auth) {

        model.addAttribute("auth", auth);

        List<Post> posts = postService.getAllPosts();
        model.addAttribute("posts", posts);

        return "index";
    }

    @PostMapping("/addPost/{user_id}")
    public void addPost(
            String title,
            String content,
            CategoryEnum category,
            @PathVariable Long user_id){
        postService.addPost(title, content, category, user_id);
    }

    @GetMapping("/addpost")
    public String addPost(){
        return "registerForm";
    }

    @PostMapping("/addcomment/{id}")
    public void addComment(@PathVariable("id") Long id_post,
                             String author,
                             String message){

        // zapis komentarza przez serwis
        postService.addCommentToPost(id_post, author, message);
    }

    @GetMapping("/allPosts")
    public String getAllPosts(){
        return String.valueOf(postService.getAllPosts());
    }

    @GetMapping("/deletecomment/{id}")
    public String deleteComment(@PathVariable("id") Long id){
        postService.deleteCommentById(id);

        return "redirect:/";
    }

    @GetMapping("/deletepost/{id}")
    public String deletePost(@PathVariable("id") Long id){
        postService.deletePost(id);

        return "redirect:/";
    }
}
