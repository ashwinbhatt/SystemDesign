package com.ashwinbhatt.systemdesign.movieticketbooking.service;

import com.ashwinbhatt.systemdesign.movieticketbooking.exceptions.MovieServiceException;
import com.ashwinbhatt.systemdesign.movieticketbooking.models.Movie;
import com.ashwinbhatt.systemdesign.movieticketbooking.repositories.MovieRepo;
import lombok.NonNull;

public class MovieService {

    private final MovieRepo movieRepo;

    public MovieService(MovieRepo movieRepo) {
        this.movieRepo = movieRepo;
    }

    public Movie getMovie(@NonNull String movieName) throws MovieServiceException {
        Movie movie = movieRepo.getMovie(movieName);
        if(movie == null) {
            throw new MovieServiceException(String.format("Movie with name: <%s>, don't exists", movieName));
        }
        return movie;
    }

    public Movie createMovie(@NonNull Movie movie) throws MovieServiceException {
        Movie dbMovie = movieRepo.getMovie(movie.getMovieName());
        if(dbMovie != null) {
            throw new MovieServiceException(String.format("Movie with name: <%s>, already exists", movie.getMovieName()));
        }
        return movieRepo.addMovie(movie);
    }
}
