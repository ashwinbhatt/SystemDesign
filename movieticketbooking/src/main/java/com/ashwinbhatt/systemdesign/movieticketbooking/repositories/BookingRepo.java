package com.ashwinbhatt.systemdesign.movieticketbooking.repositories;

import com.ashwinbhatt.systemdesign.movieticketbooking.models.Booking;

import java.util.HashMap;
import java.util.Map;

public class BookingRepo {

    private final Map<String, Booking> bookingMap;

    public BookingRepo() {
        this.bookingMap = new HashMap<String, Booking>();
    }

    public Booking getBooking(String bookingId) {
        return bookingMap.get(bookingId);
    }

    public Booking createBooking(Booking booking) {
        bookingMap.put(booking.getBookingId(), booking);
        return booking;
    }
}
