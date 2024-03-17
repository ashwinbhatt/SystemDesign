package com.ashwinbhatt.systemdesign.lockermanagementsystem.service;

import com.ashwinbhatt.systemdesign.lockermanagementsystem.exceptions.BookingUserRepoException;
import com.ashwinbhatt.systemdesign.lockermanagementsystem.exceptions.DeliveryUserRepoException;
import com.ashwinbhatt.systemdesign.lockermanagementsystem.models.BookingUser;
import com.ashwinbhatt.systemdesign.lockermanagementsystem.models.DeliveryUser;
import com.ashwinbhatt.systemdesign.lockermanagementsystem.repositories.BookingUserRepo;
import com.ashwinbhatt.systemdesign.lockermanagementsystem.repositories.DeliveryUserRepo;

import java.util.UUID;

public class UserService {

    private final BookingUserRepo bookingUserRepo;
    private final DeliveryUserRepo deliveryUserRepo;

    public UserService(BookingUserRepo bookingUserRepo, DeliveryUserRepo deliveryUserRepo) {
        this.bookingUserRepo = bookingUserRepo;
        this.deliveryUserRepo = deliveryUserRepo;
    }

    public BookingUser createBookingUser(String userName) throws BookingUserRepoException {
        String genUserId = UUID.randomUUID().toString();
        BookingUser bookingUser = new BookingUser(genUserId, userName);

        bookingUserRepo.addBookingUser(bookingUser);
        return bookingUser;
    }

    public DeliveryUser createDeliveryUser(String userName) throws DeliveryUserRepoException {
        String genUserId = UUID.randomUUID().toString();
        DeliveryUser deliveryUser = new DeliveryUser(genUserId, userName);

        deliveryUserRepo.addDeliveryUser(deliveryUser);
        return deliveryUser;
    }

    public BookingUser getBookingUser(String userId) throws BookingUserRepoException {
        return bookingUserRepo.getBookingUser(userId);
    }

    public DeliveryUser getDeliveryUser(String userId) throws  DeliveryUserRepoException {
        return deliveryUserRepo.getDeliveryUser(userId);
    }
}
