package com.example.rest.repository;

import com.example.rest.model.Post;
import com.example.rest.model.enums.CategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findAllByCategory(CategoryEnum category);

}
