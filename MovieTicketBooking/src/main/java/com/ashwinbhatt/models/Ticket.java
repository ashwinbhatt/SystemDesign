package com.ashwinbhatt.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
public class Ticket {
    private final String ticketIt;
    private final String showId;
    private final Integer price;
    private final List<Integer> seatNumbers;

    public Ticket(String showId, Integer price, List<Integer> seatNumbers){
        ticketIt= UUID.randomUUID().toString();
        this.showId= showId;
        this.price= price;
        this.seatNumbers= seatNumbers;
    }
}
