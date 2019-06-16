package com.example.rest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id                                             // PRIMARY KEY
    @GeneratedValue(strategy = GenerationType.AUTO) // AUTO_INCREMENT
    private Long id;

    private String name;
    private String lastName;
    private String email;
    private String password;

    private LocalDateTime registration_date = LocalDateTime.now();
    private boolean active = true;

    // RELACJA N:M
    @ManyToMany(
            cascade = CascadeType.ALL,                          // pełna rekursywność
            fetch = FetchType.EAGER                             // zachłanne pobieranie rekordów
    )
    @JoinTable(
            name = "user_role",                                 // nazwa tabelki
            joinColumns = @JoinColumn(name = "user_id"),        // klucz uzytkownika
            inverseJoinColumns = @JoinColumn(name = "role_id")  // klucz roli
    )
    private Set<Role> roles = new HashSet<>();

    public User(String name, String lastName, String email, String password) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    // RELACJA 1:N
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    private List<Post> posts = new ArrayList<>();

    public void addPost(Post post){
        this.posts.add(post);
    }
    public void addRole(Role role){
        this.roles.add(role);
    }
}
