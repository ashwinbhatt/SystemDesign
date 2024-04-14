package com.ashwinbhatt.systemdesign.movieticketbooking.service;

import com.ashwinbhatt.systemdesign.movieticketbooking.exceptions.AdminServiceException;
import com.ashwinbhatt.systemdesign.movieticketbooking.exceptions.BookingServiceException;
import com.ashwinbhatt.systemdesign.movieticketbooking.exceptions.UserServiceException;
import com.ashwinbhatt.systemdesign.movieticketbooking.models.Booking;
import com.ashwinbhatt.systemdesign.movieticketbooking.models.Show;
import com.ashwinbhatt.systemdesign.movieticketbooking.models.User;
import com.ashwinbhatt.systemdesign.movieticketbooking.strategies.IPaymentObject;
import com.ashwinbhatt.systemdesign.movieticketbooking.strategies.IPaymentStrategy;
import com.ashwinbhatt.systemdesign.movieticketbooking.strategies.IShowBookingStrategy;
import lombok.NonNull;

import java.util.List;

public class BookingService {

    private final UserService userService;
    private final AdminService adminService;
    private final IPaymentStrategy paymentStrategy;

    public BookingService(UserService userService, AdminService adminService, IPaymentStrategy paymentStrategy) {
        this.userService = userService;
        this.adminService = adminService;
        this.paymentStrategy = paymentStrategy;
    }

    public boolean checkAvailability(@NonNull List<Integer> seats, @NonNull Show show) throws AdminServiceException, BookingServiceException {
        IShowBookingStrategy showBookingStrategy = adminService.getShowBookingStrategy(show);

        return showBookingStrategy.checkSeatAvailability(seats);
    }

    public Booking doBooking(@NonNull User user, @NonNull List<Integer> seats, @NonNull Show show, @NonNull IPaymentObject paymentObject) throws AdminServiceException, BookingServiceException, UserServiceException {
        IShowBookingStrategy showBookingStrategy = adminService.getShowBookingStrategy(show);
        userService.getUser(user.getUserId());

        boolean isAvailable = showBookingStrategy.checkSeatAvailability(seats);
        if(!isAvailable) {
            throw new BookingServiceException(String.format("Cannot books seats: %s, already booked", seats));
        }
        Booking tempBooking = showBookingStrategy.allocateBooking(user, seats);
        paymentStrategy.doPayment(tempBooking, paymentObject);
        return showBookingStrategy.book(tempBooking);
    }

}
