package com.ashwinbhatt.systemdesign.lockermanagementsystem.repositories;

import com.ashwinbhatt.systemdesign.lockermanagementsystem.exceptions.BookingUserRepoException;
import com.ashwinbhatt.systemdesign.lockermanagementsystem.models.Booking;
import com.ashwinbhatt.systemdesign.lockermanagementsystem.models.BookingUser;

import java.util.HashMap;
import java.util.Map;

public class BookingRepository {

    private final Map<String, Booking> bookingMap;

    public BookingRepository() {
        bookingMap = new HashMap();
    }

    public void addBooking(Booking booking) throws BookingUserRepoException {
        if(bookingMap.containsKey(booking.getBookingId())) {
            throw new BookingUserRepoException(String.format("Booking with id: <%s>, already exist", booking.getBookingId()));
        }
        bookingMap.put(booking.getBookingId(), booking);
    }

    public Booking getBooking(String bookingId) throws BookingUserRepoException {
        Booking bookingUser = bookingMap.get(bookingId);
        if(bookingUser == null) {
            throw new BookingUserRepoException(String.format("Booking with id: <%s>, don't exist", bookingUser.getBookingId()));
        }
        return bookingUser;
    }

}
