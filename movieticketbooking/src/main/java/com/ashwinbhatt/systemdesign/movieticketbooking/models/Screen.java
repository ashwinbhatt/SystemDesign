package com.ashwinbhatt.systemdesign.movieticketbooking.models;

import lombok.Getter;

@Getter
public class Screen {

    private final int screenNumber;
    private final Cinema cinema;
    private final int capacity;

    public Screen(int screenNumber, Cinema cinema, int capacity) {
        this.screenNumber = screenNumber;
        this.cinema = cinema;
        this.capacity = capacity;
    }
}
