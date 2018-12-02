package com.gmail.ndonskih63.details;

import com.gmail.ndonskih63.details.Users;

import java.util.HashMap;
import java.util.Map;

public class AddUsers {

    private final Map<String, Users> users = new HashMap<>();

    public AddUsers() {
        users.put("admin", new Users("admin", "000", "admin"));
        users.put("user", new Users("user", "666", "user"));
    }

    public Users getUser(String username) {
        return users.get(username);
    }

    public boolean verify(String username, String password) {
        return users.get(username).verify(password);
    }
}
