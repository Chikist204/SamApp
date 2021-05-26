package com.example.samsapp;

import java.io.Serializable;

public class Students implements Serializable {
    private long id;
    private String username;
    private String name;
    private String role;
    private String groups;
    private String email;
    private String hashed_password;

    public Students(long id, String username, String name, String role, String groups, String email, String hashed_password){
        this.id = id;
        this.username = username;
        this.name = name;
        this.role = role;
        this.groups = groups;
        this.email = email;
        this.hashed_password= hashed_password;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public String getGroups() {
        return groups;
    }

    public String getEmail() {
        return email;
    }

    public String getHashed_password() {
        return hashed_password;
    }
}
