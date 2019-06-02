package com.example.rest.service;

import com.example.rest.model.Post;
import com.example.rest.model.User;
import com.example.rest.model.enums.CategoryEnum;
import com.example.rest.repository.PostRepository;
import com.example.rest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    PostRepository postRepository;
    UserRepository userRepository;
    @Autowired
    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public void addPost(
            String title, String content, CategoryEnum category, Long user_id){
        Post post = new Post(title,content,category);
        User user = userRepository.getOne(user_id);
        post.setUser(user);
        user.addPost(post);
        postRepository.save(post);
    }
    public List<Post> getAllPosts(){
        return postRepository.findAll();
    }
    public List<Post> getAllPostsByCategory(CategoryEnum category){
        return postRepository.findAllByCategory(category);
    }
}
