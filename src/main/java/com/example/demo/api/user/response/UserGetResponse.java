package com.example.demo.api.user.response;

import com.example.demo.api.user.dto.UserInfo;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

public class UserGetResponse {
    @JsonUnwrapped
    private UserResponse rsp;

    private UserInfo data;

    public UserGetResponse(UserResponse rsp, UserInfo data) {
        this.rsp = rsp;
        this.data = data;
    }

    public UserResponse getRsp() {
        return rsp;
    }

    public void setRsp(UserResponse rsp) {
        this.rsp = rsp;
    }

    public UserInfo getData() {
        return data;
    }

    public void setData(UserInfo data) {
        this.data = data;
    }

}
