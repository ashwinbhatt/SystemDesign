package com.ashwinbhatt.services;

import com.ashwinbhatt.exceptions.*;
import com.ashwinbhatt.models.*;
import com.ashwinbhatt.strategies.BasicShowSeatStrategy;
import com.ashwinbhatt.strategies.PaymentStrategy;
import com.ashwinbhatt.strategies.ShowSeatStrategy;
import com.ashwinbhatt.strategies.SimplePaymentStrategy;
import com.ashwinbhatt.utils.DateUtils;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TheaterService {
    private final Theater theater;
    private final HashMap<String, Show> shows;
    private final HashMap<String, Movie> movies;
    private final Map<String, Ticket> ticketMap;
    @Value("${MAX_LOCK_TIME_PERIOD}")
    private Long maxLockTimePeriod;

    public TheaterService(){
        List<Screen> screens= new ArrayList<>();
        theater= new Theater(screens);
        shows= new HashMap<>();
        movies= new HashMap<>();
        ticketMap= new HashMap<>();
    }

    public Integer addScreen(@NonNull Integer screenCapacity){
        return theater.addScreen(screenCapacity);
    }

    public boolean addMovie(@NonNull String movieName, @NonNull Date duration){
        movies.put(movieName, new Movie(movieName, duration));
        return true;
    }

    public boolean checkMovie(@NonNull String movieName){
        return movies.containsKey(movieName);
    }

    public boolean checkScreen(@NonNull Integer screenId){
        return theater.getScreens().stream().anyMatch(screen -> screen.getScreenNumber().equals(screenId));
    }

    public Show getShow(@NonNull String showId) throws ShowNotFoundException {
        Show show= shows.get(showId);
        if(show == null){
            throw new ShowNotFoundException(String.format("Show with showId %s, not found", showId));
        }
        return show;
    }

    public Integer addShow(@NonNull String movieName, @NonNull Integer screenId, @NonNull Date startTime, @NonNull Date endTime) throws MovieNotFoundException, ScreenNotFoundException, ScreenAlreadyBookedException {
        if (!checkMovie(movieName)) {
            throw new MovieNotFoundException("No movie found in repository with name: " + movieName);
        }
        if (!checkScreen(screenId)) {
            throw new ScreenNotFoundException("No screen found in repository with id: " + screenId);
        }
        Movie movie = movies.get(movieName);
        Screen screen = theater.getScreens().stream().filter(s -> s.getScreenNumber().equals(screenId)).findFirst().get();
        boolean screenBooked = shows.values().stream()
                .filter(show -> {
                    return show.getScreen().equals(screenId) &&
                            DateUtils.rangeOverlaps(show.getStartTime(), show.getEndTime(), startTime, endTime);
                })
                .anyMatch(show -> true);

        if (screenBooked) {
            throw new ScreenAlreadyBookedException(String.format("Screen: %s, busy for time [%s, %s]", screenId, startTime, endTime));
        }
        ShowSeatStrategy showSeatStrategy = new BasicShowSeatStrategy(screen.getCapacity(), maxLockTimePeriod);
        PaymentStrategy paymentStrategy= new SimplePaymentStrategy();
        Show show = new Show(movie, screen, showSeatStrategy, paymentStrategy, startTime, endTime);
        shows.put(show.getShowId(), show);
        return shows.size() - 1;
    }

    public List<Integer> getAvailableSeats(@NonNull String showId) throws ShowNotFoundException {
        Show show = getShow(showId);
        return show.getShowSeatStrategy().getAvailableSeats();
    }

    public Ticket getTicket(@NonNull String ticketId) throws TicketNotFoundException {
        Ticket ticket= ticketMap.get(ticketId);
        if(ticket == null){
            throw new TicketNotFoundException(String.format("Ticket ID: %s, not found",ticketId));
        }
        return ticket;
    }

    public Integer saveTicketToCart(@NonNull String showId, @NonNull List<Integer> seatNumbers, @NonNull String userId) throws ShowNotFoundException,  SeatBookingException {
        Show show= getShow(showId);
        ShowSeatStrategy showSeatStrategy= show.getShowSeatStrategy();
        boolean reserved= showSeatStrategy.checkInSeats(seatNumbers, userId);
        if(!reserved){
            throw new SeatBookingException(String.format("Requested seats for show already booked"));
        }
        Integer cost= show.getPaymentStrategy().getCost(seatNumbers);
        return cost;
    }

    public Ticket bookTicket(@NonNull String showId, @NonNull List<Integer> seatNumber, @NonNull String userId) throws ShowNotFoundException, SeatBookingException {
        Show show= getShow(showId);
        ShowSeatStrategy showSeatStrategy= show.getShowSeatStrategy();

        boolean lockedByUser= showSeatStrategy.checkIfTempLockByUser(seatNumber, userId);

        if(!lockedByUser){
            throw new SeatBookingException("Seat not checked out by user");
        }
        Integer cost= show.getPaymentStrategy().getCost(seatNumber);
        boolean paymentSuccess= show.getPaymentStrategy().doPayment(cost);
        if(!paymentSuccess){
            throw new SeatBookingException(String.format("Payment failed"));
        }
        boolean book= showSeatStrategy.checkOutSeats(seatNumber, userId);
        if(!book){
            throw new SeatBookingException(String.format("Cannot book seats as seats not checked out by user"));
        }
        Ticket ticket= new Ticket(showId, cost, seatNumber);
        return ticket;
    }
}
