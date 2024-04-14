package com.ashwinbhatt.systemdesign.movieticketbooking.models;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Show {

    private final String showId;
    private final Movie movie;
    private final Screen screen;
    private final Long startTime;
    private final Long endTime;
    @Setter
    private final boolean bookingAvailable;

    public Show(String showId, Movie movie, Screen screen, Long startTime, boolean bookingAvailable) {
        this.showId = showId;
        this.movie = movie;
        this.screen = screen;
        this.startTime = startTime;
        this.bookingAvailable = bookingAvailable;
        this.endTime = startTime + movie.getDuration();
    }
}
