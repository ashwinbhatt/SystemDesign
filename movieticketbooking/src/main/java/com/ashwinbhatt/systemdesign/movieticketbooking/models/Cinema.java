package com.ashwinbhatt.systemdesign.movieticketbooking.models;

import lombok.Getter;

@Getter
public class Cinema {

    private final String id;
    private final String name;

    public Cinema(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
