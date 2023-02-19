package com.example.demo.userRolePermission.domain.rsp;

import java.io.Serializable;

public class Response<T> implements Serializable {
    private int code = 1;

    private String message = "Success";

    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Response(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Response() {
    }

    public Response(T data) {
        this.data = data;
    }

    public Response(String errorMessage) {
        this.code = 0;
        if (errorMessage != null && errorMessage.length() > 99) {
            this.message = "Something went wrong, please try again later";
        } else {
            this.message = errorMessage;
        }
    }
}
