package com.ashwinbhatt.systemdesign.lockermanagementsystem.models;

import lombok.Getter;

@Getter
public class Item {

    private final String itemId;
    private final BookingUser bookingUser;

    public Item(String itemId, BookingUser bookingUser) {
        this.itemId = itemId;
        this.bookingUser = bookingUser;
    }
}
