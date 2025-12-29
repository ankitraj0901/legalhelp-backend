package com.legalhelp.backend.dto;

public class LoginResponse {
    private String message;
    private Object user;


    public LoginResponse(String message, Object user) {
        this.message = message;
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getUser() {
        return user;
    }

    public void setUser(Object user) {
        this.user = user;
    }
}
