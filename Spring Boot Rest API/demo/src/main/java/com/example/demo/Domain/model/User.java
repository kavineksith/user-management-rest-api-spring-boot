package com.example.demo.Domain.model;

import jakarta.persistence.*;

@Entity // Indicates that this class is a JPA entity
@Table(name = "users") // Specifies the table name in the database
public class User {

    @Id // Marks this field as the primary key
    @GeneratedValue(strategy = GenerationType.UUID) // Specifies the strategy for primary key generation
    @Column(name = "id", nullable = false, unique = true, updatable = false, insertable = true)
    private String id;

    @Column(name = "username", nullable = false, unique = true, updatable = true, insertable = true)
    private String username;

    @Column(name = "name", nullable = false, unique = false, updatable = true, insertable = true)
    private String name;

    @Column(name ="password", nullable = false, updatable = true, insertable = true, unique = false)
    private String password;

    public User() {
    }

    public User(String username, String name, String password) {
        this.username = username;
        this.name = name;
        this.password = password;
    }

    public User(String id, String username, String name, String password) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
