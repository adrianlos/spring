package com.example.rest.controller;

import com.example.rest.controller.dto.PostDto;
import com.example.rest.model.Comment;
import com.example.rest.model.Post;
import com.example.rest.model.enums.CategoryEnum;
import com.example.rest.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
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

    @GetMapping("/addpost")
    public String addPost(Model model, Authentication auth){

        model.addAttribute("auth", auth);
        model.addAttribute("post", new PostDto());
        model.addAttribute("categories", Arrays.asList(CategoryEnum.values()));

        return "addPost";
    }

    @PostMapping("/addpost")
    public String addPost(@ModelAttribute("post") PostDto postDto,
                          Authentication auth){

        String email = ((UserDetails)auth.getPrincipal()).getUsername();

        postService.addPost(postDto, email);
        return "redirect:/";
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
