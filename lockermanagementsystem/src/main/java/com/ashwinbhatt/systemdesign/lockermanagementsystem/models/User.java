package com.ashwinbhatt.systemdesign.lockermanagementsystem.models;


import lombok.Getter;

@Getter
public abstract class User {

    private final String userId;
    private final String userName;

    public User(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }
}
