package com.example.demo.api.user.response;

public class UserResponse {

    private Integer rsp_code;
    private String rsp_msg;

    public UserResponse(){}

    public Integer getRsp_code() {
        return rsp_code;
    }

    public void setRsp_code(Integer rsp_code) {
        this.rsp_code = rsp_code;
    }

    public String getRsp_msg() {
        return rsp_msg;
    }

    public void setRsp_msg(String rsp_msg) {
        this.rsp_msg = rsp_msg;
    }

    public void OPERATION_SUCCESSFUL(){
        this.setRsp_code(20);
        this.setRsp_msg("Operation successful.");
    }
    public void EMAIL_ALREADY_EXISTS(){
        this.setRsp_code(21);
        this.setRsp_msg("Email already exists.");
    }
    public void PROBLEM_WITH_THE_PARAMETERS(){
        this.setRsp_code(30);
        this.setRsp_msg("There is a problem with the parameters.");
    }
    public void MISSING_PARAMETER(){
        this.setRsp_code(31);
        this.setRsp_msg("Missing parameter.");
    }
    public void EMAIL_DOES_NOT_EXIST(){
        this.setRsp_code(40);
        this.setRsp_msg("Email does not exist.");
    }
    public void PASSWORD_IS_INCORRECT(){
        this.setRsp_code(50);
        this.setRsp_msg("The password is incorrect.");
    }
}
