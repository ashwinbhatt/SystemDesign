package com.ashwinbhatt.controllers;

import com.ashwinbhatt.exceptions.*;
import com.ashwinbhatt.models.Movie;
import com.ashwinbhatt.models.Screen;
import com.ashwinbhatt.models.Show;
import com.ashwinbhatt.pojos.requestbody.MovieDetails;
import com.ashwinbhatt.pojos.requestbody.ScreenDetails;
import com.ashwinbhatt.services.TheaterService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/")
public class MovieTheaterController {

    public final TheaterService theaterService;

    public MovieTheaterController(TheaterService theaterService){
        this.theaterService= theaterService;
    }

    @RequestMapping(path = "/theater/screen", method = RequestMethod.POST)
    public Integer addScreen(@RequestBody ScreenDetails screenDetails){
        return theaterService.addScreen(screenDetails.getScreenCapacity());
    }

    @RequestMapping(path = "/theater/movie", method = RequestMethod.POST)
    public boolean addMovie(@RequestBody MovieDetails movieDetails){
        return theaterService.addMovie(movieDetails.getName(), movieDetails.getDuration());
    }

    @RequestMapping(path = "/theater/show", method = RequestMethod.POST)
    public String addShow(@RequestBody String movieName, @RequestBody Integer screenId, @RequestBody Date startTime, @RequestBody Date endTime){
        try {
            return theaterService.addShow(movieName, screenId, startTime, endTime).toString();
        } catch (MovieNotFoundException | ScreenNotFoundException | ScreenAlreadyBookedException e) {
            return e.getMessage();
        }
    }

    @RequestMapping(path = "/theater/show", method = RequestMethod.GET)
    public String getAllAvailableSeats(@RequestParam String showId){
        try {
            return theaterService.getAvailableSeats(showId).toString();
        } catch (ShowNotFoundException e) {
            return e.getMessage();
        }
    }

    @RequestMapping(path = "/theater/cart", method = RequestMethod.POST)
    public String saveToCart(@RequestBody String showId, List<Integer> seatNumbers, String userId){
        try {
            return theaterService.saveTicketToCart(showId, seatNumbers, userId).toString();
        } catch (ShowNotFoundException | SeatBookingException e) {
            return e.getMessage();
        }
    }

    @RequestMapping(path = "/theater/book", method = RequestMethod.POST)
    public String bookTicket(@RequestBody String showId, List<Integer> seatNumber, String userId){
        try {
            return theaterService.bookTicket(showId, seatNumber, userId).toString();
        } catch (ShowNotFoundException | SeatBookingException e) {
            return e.getMessage();
        }
    }

    @RequestMapping(path = "/theater/ticket", method = RequestMethod.GET)
    public String getTicket(@RequestBody String ticketId){
        try {
            return theaterService.getTicket(ticketId).toString();
        } catch (TicketNotFoundException e) {
            return e.getMessage();
        }
    }

}
