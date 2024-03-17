package com.ashwinbhatt.systemdesign.lockermanagementsystem.repositories;

import com.ashwinbhatt.systemdesign.lockermanagementsystem.exceptions.BookingUserRepoException;
import com.ashwinbhatt.systemdesign.lockermanagementsystem.models.BookingUser;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BookingUserRepo {

    private final Map<String, BookingUser> bookingUserMap;

    public BookingUserRepo() {
        bookingUserMap = new HashMap<String, BookingUser>();
    }

    public void addBookingUser(BookingUser bookingUser) throws BookingUserRepoException {
        if(bookingUserMap.containsKey(bookingUser.getUserName())) {
            throw new BookingUserRepoException(String.format("Booking user with id: <%s>, already exist", bookingUser.getUserId()));
        }
        bookingUserMap.put(bookingUser.getUserId(), bookingUser);
    }

    public BookingUser getBookingUser(String bookingUserId) throws BookingUserRepoException {
        BookingUser bookingUser = bookingUserMap.get(bookingUserId);
        if(bookingUser == null) {
            throw new BookingUserRepoException(String.format("Booking user with id: <%s>, don't exist", bookingUser.getUserId()));
        }
        return bookingUser;
    }

}
