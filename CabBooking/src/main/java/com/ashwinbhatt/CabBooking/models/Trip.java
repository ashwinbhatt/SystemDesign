package com.ashwinbhatt.CabBooking.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@RequiredArgsConstructor
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String tripId;
    private final Location startPoint, endPoint;
    private final Driver driver;
    private final Rider rider;
    private final Double price;
    @Setter
    private Boolean isComplete;
}
