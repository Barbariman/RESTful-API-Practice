package com.example.demo.api.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

public class UserGetResponse {
    @JsonUnwrapped
    private UserResponse rsp;

    private User data;

    public UserGetResponse(UserResponse rsp, User data) {
        this.rsp = rsp;
        this.data = data;
    }

    public UserResponse getRsp() {
        return rsp;
    }

    public void setRsp(UserResponse rsp) {
        this.rsp = rsp;
    }

    public User getData() {
        return data;
    }

    public void setData(User data) {
        this.data = data;
    }

}
