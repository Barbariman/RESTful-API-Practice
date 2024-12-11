package com.example.demo.api.user;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Pattern;

@Service
public class UserService {

    private final Map<String,User> userMap;
    private final Pattern emailPattern= Pattern.compile("^[a-z]+@[a-z]+\\.com$");

    UserService(){
        userMap = new HashMap<>();

        User user1 = new User("aaa","aaa@mail.com","123");
        User user2 = new User("bbb","bbb@mail.com","123");
        User user3 = new User("ccc","ccc@mail.com","123");
        User user4 = new User("ddd","ddd@mail.com","123");

        userMap.putAll(Map.of(user1.getEmail(), user1,user2.getEmail(),user2, user3.getEmail(), user3,user4.getEmail(),user4));
    }

    public UserGetResponse getResponse(String email) {
        UserResponse rsp = new UserResponse();
        User responsedUser=new User();
        boolean emailIsValid = emailPattern.matcher(email).find();
        if(email.isEmpty()){
            rsp.MISSING_PARAMETER();
        }
        if(email.contains(" ") || !emailIsValid){
            rsp.PROBLEM_WITH_THE_PARAMETERS();
        }
        if(userMap.containsKey(email)) {
            responsedUser.setEmail(userMap.get(email).getEmail());
            responsedUser.setName(userMap.get(email).getName());
            rsp.OPERATION_SUCCESSFUL();
        }
        else{
            rsp.EMAIL_DOES_NOT_EXIST();
        }
        return new UserGetResponse(rsp,responsedUser);
    }
    public UserResponse postResponse(User newUser){
        UserResponse rsp = new UserResponse();
        if(newUser.getEmail()==null || newUser.getName()==null ||newUser.getPassword()==null) {
            rsp.MISSING_PARAMETER();
            return rsp;
        }
        boolean emailIsValid = emailPattern.matcher(newUser.getEmail()).find();
        if(newUser.getEmail().contains(" ") || newUser.getName().isBlank() || newUser.getPassword().contains(" ") || !emailIsValid){
            rsp.PROBLEM_WITH_THE_PARAMETERS();
        }
        else if (userMap.containsKey(newUser.getEmail())) {
            rsp.EMAIL_ALREADY_EXISTS();
        }
        else rsp.OPERATION_SUCCESSFUL();
        return rsp;
    }
    public UserResponse putResponse(String email,User newUser){
        UserResponse rsp = new UserResponse();
        boolean emailIsValid = emailPattern.matcher(email).find();
        boolean newEmailIsValid = emailPattern.matcher(newUser.getEmail()).find();
        if(newUser.getName()==null || newUser.getEmail()==null || newUser.getPassword()==null){
            rsp.MISSING_PARAMETER();
            return rsp;
        }
        if(email.contains(" ")|| newUser.getName().isBlank()||newUser.getEmail().contains(" ")||newUser.getPassword().contains(" ")||!emailIsValid||!newEmailIsValid){
            rsp.PROBLEM_WITH_THE_PARAMETERS();
        }
        else if (userMap.containsKey(email)) {
            if(!Objects.equals(userMap.get(email).getPassword(),newUser.getPassword())) {
                rsp.PASSWORD_IS_INCORRECT();
            }
            else if(userMap.containsKey(newUser.getEmail())){
                rsp.EMAIL_ALREADY_EXISTS();
            }
            else rsp.OPERATION_SUCCESSFUL();
        }
        else{
            rsp.EMAIL_DOES_NOT_EXIST();
        }
        return rsp;
    }
    public UserResponse deleteResponse(String email,String password) {
        UserResponse rsp = new UserResponse();
        boolean emailIsValid = emailPattern.matcher(email).find();
        if (email == null || password == null) {
            rsp.MISSING_PARAMETER();
            return rsp;
        }
        else if (email.contains(" ") || password.contains(" ") || !emailIsValid) {
            rsp.PROBLEM_WITH_THE_PARAMETERS();
        }
        else if (userMap.containsKey(email)) {
            if (!Objects.equals(userMap.get(email).getPassword(), password)) {
                rsp.PASSWORD_IS_INCORRECT();
            }
            else rsp.OPERATION_SUCCESSFUL();
        } else {
            rsp.EMAIL_DOES_NOT_EXIST();
        }
        return rsp;
    }
}
