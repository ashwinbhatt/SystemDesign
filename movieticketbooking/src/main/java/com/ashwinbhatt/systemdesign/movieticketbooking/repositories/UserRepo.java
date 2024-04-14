package com.ashwinbhatt.systemdesign.movieticketbooking.repositories;

import com.ashwinbhatt.systemdesign.movieticketbooking.models.User;

import java.util.HashMap;
import java.util.Map;

public class UserRepo {

    private final Map<String, User> userMap;

    public UserRepo() {
        userMap = new HashMap<>();
    }

    public User getUser(String userId) {
        return userMap.get(userId);
    }

    public User createUser(User user) {
        userMap.put(user.getUserId(), user);
        return user;
    }

}
