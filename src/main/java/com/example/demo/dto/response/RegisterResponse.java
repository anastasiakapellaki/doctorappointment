package com.example.demo.dto.response;

public class RegisterResponse {

    private String amka;
    private String fullName;
    private String message;

    public RegisterResponse(String amka, String fullName, String message) {
        this.amka = amka;
        this.fullName = fullName;
        this.message = message;
    }

    public String getAmka() { return amka; }
    public String getFullName() { return fullName; }
    public String getMessage() { return message; }
}