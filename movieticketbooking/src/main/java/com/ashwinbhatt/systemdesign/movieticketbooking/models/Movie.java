package com.ashwinbhatt.systemdesign.movieticketbooking.models;

import lombok.Getter;

@Getter
public class Movie {

    private final String movieName;
    private final Long duration;

    public Movie(String movieName, Long duration) {
        this.movieName = movieName;
        this.duration = duration;
    }
}
