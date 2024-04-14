package com.ashwinbhatt.systemdesign.movieticketbooking.service;

import com.ashwinbhatt.systemdesign.movieticketbooking.exceptions.AdminServiceException;
import com.ashwinbhatt.systemdesign.movieticketbooking.exceptions.ShowAllocationStrategyException;
import com.ashwinbhatt.systemdesign.movieticketbooking.models.Cinema;
import com.ashwinbhatt.systemdesign.movieticketbooking.models.Screen;
import com.ashwinbhatt.systemdesign.movieticketbooking.models.Show;
import com.ashwinbhatt.systemdesign.movieticketbooking.repositories.CinemaRepo;
import com.ashwinbhatt.systemdesign.movieticketbooking.repositories.ScreenRepo;
import com.ashwinbhatt.systemdesign.movieticketbooking.repositories.ShowRepo;
import com.ashwinbhatt.systemdesign.movieticketbooking.strategies.IShowBookingStrategy;
import com.ashwinbhatt.systemdesign.movieticketbooking.strategies.IShowStrategy;
import lombok.NonNull;

import java.util.HashMap;
import java.util.Map;

public class AdminService {

    private final CinemaRepo cinemaRepo;
    private final ScreenRepo screenRepo;
    private final ShowRepo showRepo;
    private final IShowStrategy showStrategy;
    private final Map<String, IShowBookingStrategy> showBookingStrategyMap;

    public AdminService(CinemaRepo cinemaRepo, ScreenRepo screenRepo, ShowRepo showRepo, IShowStrategy showStrategy) {
        this.cinemaRepo = cinemaRepo;
        this.screenRepo = screenRepo;
        this.showRepo = showRepo;
        this.showStrategy = showStrategy;
        showBookingStrategyMap = new HashMap<String, IShowBookingStrategy>();
    }

    // Cinema level APIs
    public Cinema getCinema(@NonNull String cinemaId) throws AdminServiceException {
        Cinema cinema = cinemaRepo.getCinema(cinemaId);
        if(cinema == null) {
            throw new AdminServiceException(String.format("Cinema with id: <%s>, don't exists", cinemaId));
        }
        return cinema;
    }

    public Cinema createCinema(@NonNull Cinema cinema) throws AdminServiceException {
        Cinema dbCinema = cinemaRepo.getCinema(cinema.getId());
        if(dbCinema != null) {
            throw new AdminServiceException(String.format("Cinema with id: <%s>, already exists", cinema.getId()));
        }
        return cinemaRepo.createCinema(cinema);
    }

    // Screen level APIs
    public Screen getScreen(@NonNull String cinemaId, int cinemaNo) throws AdminServiceException {
        Screen screen = screenRepo.getScreen(cinemaId, cinemaNo);
        if(screen == null) {
            throw new AdminServiceException(String.format("Cinema with id: <%s>, don't have screen with number: <%s>", cinemaId, cinemaNo));
        }
        return screen;
    }

    public Screen createScreen(@NonNull Screen screen) throws AdminServiceException {
        Screen dbScreen = screenRepo.getScreen(screen.getCinema().getId(), screen.getScreenNumber());
        if(dbScreen != null) {
            throw new AdminServiceException(String.format("Cinema with id: <%s>, already have screen with number: <%s>", screen.getCinema().getId(), screen.getScreenNumber()));
        }
        return screenRepo.createScreen(screen);
    }

    // Show level APIs
    public Show getShow(@NonNull String showId) throws AdminServiceException {
        Show show = showRepo.getShow(showId);
        if(show == null) {
            throw new AdminServiceException(String.format("Show with id: <%s>, don't exits", showId));
        }
        return show;
    }

    public Show createShow(@NonNull Show show) throws ShowAllocationStrategyException {
        boolean isViable = showStrategy.checkShowIsViable(show);
        showStrategy.allocateShow(show);
        // TODO: Initialize implementation of show's booking strategy map here.
        showBookingStrategyMap.put(show.getShowId(), null);
        return showRepo.createShow(show);
    }

    public IShowBookingStrategy getShowBookingStrategy(@NonNull Show show) throws AdminServiceException {
        getShow(show.getShowId());
        return showBookingStrategyMap.get(show.getShowId());
    }

}
