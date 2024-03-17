package com.ashwinbhatt.systemdesign.lockermanagementsystem.models;

import lombok.Getter;

@Getter
public class Booking {

    private final String bookingId;
    private final BookingUser bookingUser;
    private final DeliveryUser deliveryUser;
    private final Item item;
    private final LockerSlot lockerSlot;
    private final String otp;
    private final Integer startDate;

    public Booking(String bookingId, BookingUser bookingUser, DeliveryUser deliveryUser, Item item, LockerSlot lockerSlot, String otp, Integer startDate) {
        this.bookingId = bookingId;
        this.bookingUser = bookingUser;
        this.deliveryUser = deliveryUser;
        this.item = item;
        this.lockerSlot = lockerSlot;
        this.otp = otp;
        this.startDate = startDate;
    }
}
