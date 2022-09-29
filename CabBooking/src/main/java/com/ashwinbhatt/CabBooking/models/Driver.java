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
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String driverId;
    private final String name;
    private final String cabRegistrationNumber;
    @Setter
    private Location location;
    @Setter
    private Boolean availabilityStatus;
}
