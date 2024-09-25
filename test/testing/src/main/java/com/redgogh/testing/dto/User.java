package com.redgogh.testing.dto;

import lombok.Data;

@Data
public class User {
    private String username;
    private String email;
    private int age;

    public User() {

    }

    public User(String username, String email, int age) {
        this.username = username;
        this.email = email;
        this.age = age;
    }

}