package com.ashwinbhatt.systemdesign.movieticketbooking.strategies;

import com.ashwinbhatt.systemdesign.movieticketbooking.exceptions.BookingServiceException;
import com.ashwinbhatt.systemdesign.movieticketbooking.models.Booking;
import com.ashwinbhatt.systemdesign.movieticketbooking.models.Show;
import com.ashwinbhatt.systemdesign.movieticketbooking.models.User;

import java.util.List;

public abstract class IShowBookingStrategy {

    protected final Show show;

    protected IShowBookingStrategy(Show show) {
        this.show = show;
    }

    public abstract boolean checkSeatAvailability(List<Integer> seatNumbers) throws BookingServiceException;

    public abstract Booking allocateBooking(User bookingUser, List<Integer> seatNumber) throws BookingServiceException;

    public abstract Booking book(Booking booking) throws BookingServiceException;
}
