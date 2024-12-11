package com.example.demo.api.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

public class User {
    private String name;

    private String email;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String password;

    User(){}

    User(String name, String email,String password){
        this.name=name;
        this.email=email;
        this.password=password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
