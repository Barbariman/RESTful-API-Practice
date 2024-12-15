package com.example.demo.api.user.controller;

import com.example.demo.api.user.model.User;
import com.example.demo.api.user.response.UserGetResponse;
import com.example.demo.api.user.response.UserResponse;
import com.example.demo.api.user.service.UserService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private static Gson GSON;

    @Autowired
    private UserService userService;

    @GetMapping("/get/get_user")
    public ResponseEntity<UserGetResponse> getUser(@RequestHeader String email){
        HttpHeaders headers = new HttpHeaders();
        UserGetResponse rsp = userService.getResponse(email);
        return new ResponseEntity(GSON.toJson(rsp),headers, HttpStatus.OK);
    }
    @PostMapping("/post/add_user")
    public ResponseEntity addUser(@RequestBody User newUser){
        HttpHeaders headers = new HttpHeaders();
        UserResponse rsp = userService.postResponse(newUser);
        return new ResponseEntity(GSON.toJson(rsp),headers, HttpStatus.OK);
    }
    @PutMapping("/put/fix_user")
    public ResponseEntity<UserResponse> fixUser(@RequestHeader String email,@RequestBody User newUser){
        HttpHeaders headers = new HttpHeaders();
        UserResponse rsp = userService.putResponse(email, newUser);
        return new ResponseEntity(GSON.toJson(rsp),headers, HttpStatus.OK);
    }
    @DeleteMapping("/delete/delete_user")
    public ResponseEntity<UserResponse> deleteUser(@RequestHeader String email, @RequestHeader(required = false) String password){
        HttpHeaders headers = new HttpHeaders();
        UserResponse rsp = userService.deleteResponse(email, password);
        return new ResponseEntity(GSON.toJson(rsp),headers, HttpStatus.OK);
    }
}
