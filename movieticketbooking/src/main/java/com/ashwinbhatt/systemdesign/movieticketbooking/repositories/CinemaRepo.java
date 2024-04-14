package com.ashwinbhatt.systemdesign.movieticketbooking.repositories;

import com.ashwinbhatt.systemdesign.movieticketbooking.models.Cinema;

import java.util.HashMap;
import java.util.Map;

public class CinemaRepo {

    private final Map<String, Cinema> cinemaMap;

    public CinemaRepo() {
        this.cinemaMap = new HashMap<>();
    }

    public Cinema getCinema(String cinemaId) {
        return cinemaMap.get(cinemaId);
    }

    public Cinema createCinema(Cinema cinema) {
        cinemaMap.put(cinema.getId(), cinema);
        return cinema;
    }
}
