package com.ashwinbhatt.CabBooking.strategies;

import com.ashwinbhatt.CabBooking.models.Location;
import org.springframework.stereotype.Component;


public abstract class PricingStrategy {

    public abstract Double findTripPrice(Location startPoint, Location endPoint);
}
