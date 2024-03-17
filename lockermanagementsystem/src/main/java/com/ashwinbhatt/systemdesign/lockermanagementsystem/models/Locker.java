package com.ashwinbhatt.systemdesign.lockermanagementsystem.models;

import lombok.Getter;

@Getter
public class Locker {

    private final String lockerId;

    public Locker(String lockerId) {
        this.lockerId = lockerId;
    }
}
