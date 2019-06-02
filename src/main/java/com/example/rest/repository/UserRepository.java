package com.example.rest.repository;

import com.example.rest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository                                   //<tabelka,typ klucza>
public interface UserRepository extends JpaRepository<User,Long> {
    // SEELCT * FROM user WHERE login = ? AND password = ?;
    User findFirstByLoginAndPassword(String login, String password);

}
