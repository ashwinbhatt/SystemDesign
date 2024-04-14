package com.ashwinbhatt.systemdesign.movieticketbooking.repositories;

import com.ashwinbhatt.systemdesign.movieticketbooking.models.Movie;

import java.util.HashMap;
import java.util.Map;

public class MovieRepo {

    private final Map<String, Movie> movieMap;


    public MovieRepo() {
        this.movieMap = new HashMap<>();
    }

    public Movie getMovie(String movieName) {
        return movieMap.get(movieName);
    }

    public Movie addMovie(Movie movie) {
        movieMap.put(movie.getMovieName(), movie);
        return movie;
    }
}
