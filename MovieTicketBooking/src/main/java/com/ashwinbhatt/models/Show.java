package com.ashwinbhatt.models;

import com.ashwinbhatt.strategies.PaymentStrategy;
import com.ashwinbhatt.strategies.ShowSeatStrategy;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;
import java.util.UUID;

@Getter
public class Show {
    private final String showId;
    private final Movie movie;
    private final Screen screen;
    private final ShowSeatStrategy showSeatStrategy;
    private final PaymentStrategy paymentStrategy;
    private final Date startTime;
    private final Date endTime;
    

    public Show(Movie movie, Screen screen, ShowSeatStrategy showSeatStrategy, PaymentStrategy paymentStrategy, Date startTime, Date endTime){
        showId= UUID.randomUUID().toString();
        this.movie= movie;
        this.screen= screen;
        this.showSeatStrategy= showSeatStrategy;
        this.paymentStrategy= paymentStrategy;
        this.startTime= startTime;
        this.endTime= endTime;
    }
}
