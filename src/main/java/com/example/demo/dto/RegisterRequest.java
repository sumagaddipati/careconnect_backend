package com.example.demo.dto;

public class RegisterRequest {

    private String name;
    private String email;
    private String password;
    private String role;

    private String seniorName;
    private String seniorCode;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getSeniorName() {
        return seniorName;
    }

    public void setSeniorName(String seniorName) {
        this.seniorName = seniorName;
    }

    public String getSeniorCode() {
        return seniorCode;
    }

    public void setSeniorCode(String seniorCode) {
        this.seniorCode = seniorCode;
    }
}