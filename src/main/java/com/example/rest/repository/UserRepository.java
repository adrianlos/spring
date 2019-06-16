package com.example.rest.repository;

import com.example.rest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository                                   //<tabelka,typ klucza>
public interface UserRepository extends JpaRepository<User,Long> {
    // SELECT * FROM user WHERE email = ? AND password = ?;
    User findFirstByEmailAndPassword(String email, String password);
    // SELECT * FROM user WHERE email = ?;
    User findFirstByEmail(String login);

}
