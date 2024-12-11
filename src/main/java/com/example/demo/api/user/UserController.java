package com.example.demo.api.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    UserController(UserService userService){
        this.userService=userService;
    }
    @GetMapping("/get/get_user")
    public ResponseEntity<UserGetResponse> getUser(@RequestHeader String email){
        HttpHeaders headers = new HttpHeaders();
        UserGetResponse rsp = userService.getResponse(email);
        return ResponseEntity.ok().headers(headers).body(rsp);
    }
    @PostMapping("/post/add_user")
    public ResponseEntity<UserResponse> addUser(@RequestBody User newUser){
        HttpHeaders headers = new HttpHeaders();
        UserResponse rsp = userService.postResponse(newUser);
        return ResponseEntity.ok().headers(headers).body(rsp);
    }
    @PutMapping("/put/fix_user")
    public ResponseEntity<UserResponse> fixUser(@RequestHeader String email,@RequestBody User newUser){
        HttpHeaders headers = new HttpHeaders();
        UserResponse rsp = userService.putResponse(email, newUser);
        return ResponseEntity.ok().headers(headers).body(rsp);
    }
    @DeleteMapping("/delete/delete_user")
    public ResponseEntity<UserResponse> deleteUser(@RequestHeader String email, @RequestHeader(required = false) String password){
        HttpHeaders headers = new HttpHeaders();
        UserResponse rsp = userService.deleteResponse(email, password);
        return ResponseEntity.ok().headers(headers).body(rsp);
    }
}
