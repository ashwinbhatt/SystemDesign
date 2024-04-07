package com.ashwinbhatt.systemdesign.lockermanagementsystem.service;

import com.ashwinbhatt.systemdesign.lockermanagementsystem.exceptions.BookingUserRepoException;
import com.ashwinbhatt.systemdesign.lockermanagementsystem.models.Booking;
import com.ashwinbhatt.systemdesign.lockermanagementsystem.repositories.BookingRepository;

public class OperationsService {

    private final BookingRepository bookingRepository;
    private final UserService userService;

    public OperationsService(BookingRepository bookingRepository, UserService userService) {
        this.bookingRepository = bookingRepository;
        this.userService = userService;
    }

    public boolean openLocker(Booking booking, String otp) throws BookingUserRepoException {
        Booking savedBooking = bookingRepository.getBooking(booking.getBookingId());
        if(savedBooking.getOtp().equals(otp)) {
            return true;
        }
        return false;
    }
}
