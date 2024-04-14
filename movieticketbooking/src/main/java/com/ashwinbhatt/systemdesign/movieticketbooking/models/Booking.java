package com.ashwinbhatt.systemdesign.movieticketbooking.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
public class Booking {

    private final String bookingId;
    private final User bookingUser;
    private final List<Integer> seatNumbers;
    private final Show show;
    @Setter
    private Double price;
    @Setter
    private BookingStatus bookingStatus;

    public Booking(String bookingId, User bookingUser, List<Integer> seatNumbers, Show show) {
        this.bookingId = bookingId;
        this.bookingUser = bookingUser;
        this.seatNumbers = seatNumbers;
        this.show = show;
        this.bookingStatus = BookingStatus.TEMP_ALLOCATED;
    }
}
