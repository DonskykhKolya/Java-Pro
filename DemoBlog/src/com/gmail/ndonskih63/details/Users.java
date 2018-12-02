package com.gmail.ndonskih63.details;

public class Users {
    private String username;
    private String password;
    private String role;

    public Users(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public Users() {
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

    public boolean verify(String password) {
        return this.password.equals(password);
    }
}

