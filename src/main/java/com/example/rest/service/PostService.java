package com.example.rest.service;

import com.example.rest.controller.dto.PostDto;
import com.example.rest.model.Comment;
import com.example.rest.model.Post;
import com.example.rest.model.User;
import com.example.rest.model.enums.CategoryEnum;
import com.example.rest.repository.CommentRepository;
import com.example.rest.repository.PostRepository;
import com.example.rest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.beans.Transient;
import java.util.List;

@Service
public class PostService {
    PostRepository postRepository;
    UserRepository userRepository;
    CommentRepository commentRepository;

    @Autowired
    public PostService(PostRepository postRepository,
                       UserRepository userRepository,
                       CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }

    public void addPost(PostDto postDto, String email) {

        Post post = new Post(postDto.getTitle(),
                postDto.getContent(), postDto.getCategory());

        User user = userRepository.findFirstByEmail(email);
        post.setUser(user);
        user.addPost(post);

        postRepository.save(post);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public List<Post> getAllPostsByCategory(CategoryEnum category) {
        return postRepository.findAllByCategory(category);
    }


    public Comment addCommentToPost(Long id_post, String author, String message) {
        // wyszukaj post po id
        Post post = postRepository.getOne(id_post);
        // dodaj komentarz do posta
        Comment comment = new Comment(
                message,
                author,
                post
        );
        post.addComment(comment);
        // zapisz komentarz w DB
        return commentRepository.save(comment);
    }

    public void deleteCommentById(Long id) {
        commentRepository.deleteById(id);
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}
