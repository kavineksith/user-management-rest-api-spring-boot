package com.example.demo.Application.dto.request;

import jakarta.validation.constraints.NotBlank;

public class UpdateUser {
    @NotBlank(message = "Username can't be blank!")
    private String username;
    @NotBlank(message = "Name can't be blank!")
    private String name;
    @NotBlank(message = "Password can't be blank!")
    private String password;

    public UpdateUser() {
    }

    public UpdateUser(String username, String name, String password) {
        this.username = username;
        this.name = name;
        this.password = password;
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
