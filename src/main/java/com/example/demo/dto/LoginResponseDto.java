package com.example.demo.dto;

public class LoginResponseDto {

    private String message;
    private Long id;
    private String email;
    private String token;

    public LoginResponseDto() {
    }

    public LoginResponseDto(String message, Long id, String email, String token) {
        this.message = message;
        this.id = id;
        this.email = email;
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getToken() {
        return token;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setToken(String token) {
        this.token = token;
    }
}