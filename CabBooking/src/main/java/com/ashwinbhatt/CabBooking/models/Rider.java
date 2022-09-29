package com.ashwinbhatt.CabBooking.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@RequiredArgsConstructor
public class Rider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String riderId;
    private final String name;
}
