package com.example.demo.api.user.service;

import com.example.demo.api.user.model.User;
import com.example.demo.api.user.dao.UserDao;
import com.example.demo.api.user.dto.UserInfo;
import com.example.demo.api.user.response.UserGetResponse;
import com.example.demo.api.user.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Pattern;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    private final Pattern emailPattern= Pattern.compile("^[a-z]+@[a-z]+\\.com$");

    UserService(){}

    public UserGetResponse getResponse(String email) {
        UserResponse rsp = new UserResponse();
        UserInfo responsedUser=new UserInfo();

        boolean emailIsValid = emailPattern.matcher(email).find();
        if(email.isEmpty()){
            rsp.MISSING_PARAMETER();
        }
        if(email.contains(" ") || !emailIsValid){
            rsp.PROBLEM_WITH_THE_PARAMETERS();
        }
        User queryedUser = userDao.getUser(email);
        if(queryedUser!=null) {
            responsedUser.setEmail(queryedUser.getEmail());
            responsedUser.setName(queryedUser.getName());
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
            return rsp;
        }
        User queryedUser = userDao.getUser(newUser.getEmail());
        if (queryedUser!=null) {
            rsp.EMAIL_ALREADY_EXISTS();
            return rsp;
        }
        else{
            rsp.OPERATION_SUCCESSFUL();
            userDao.addUser(newUser);
            return rsp;
        }
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
            return rsp;
        }
        User queryedUser = userDao.getUser(newUser.getEmail());
        if (queryedUser!=null) {
            if(!Objects.equals(queryedUser.getPassword(),newUser.getPassword())) {
                rsp.PASSWORD_IS_INCORRECT();
                return rsp;
            }
            User existedUser = userDao.getUser(email);
            if(existedUser!=null){
                rsp.EMAIL_ALREADY_EXISTS();
            }
            else {
                rsp.OPERATION_SUCCESSFUL();
                userDao.fixUser(email,newUser);
            }
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
        if (email.contains(" ") || password.contains(" ") || !emailIsValid) {
            rsp.PROBLEM_WITH_THE_PARAMETERS();
            return rsp;
        }
        User queryedUser = userDao.getUser(email);
        if (queryedUser!=null) {
            if (!Objects.equals(queryedUser.getPassword(), password)) {
                rsp.PASSWORD_IS_INCORRECT();
            }
            else {
                userDao.deleteUser(email);
                rsp.OPERATION_SUCCESSFUL();
            }
        } else {
            rsp.EMAIL_DOES_NOT_EXIST();
        }
        return rsp;
    }
}
