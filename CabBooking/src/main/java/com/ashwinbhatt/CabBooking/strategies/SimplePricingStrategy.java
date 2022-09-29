package com.ashwinbhatt.CabBooking.strategies;

import com.ashwinbhatt.CabBooking.models.Location;
import org.springframework.stereotype.Component;

@Component
public class SimplePricingStrategy extends PricingStrategy{

    private final Integer ratePerKilometer= 10;

    @Override
    public Double findTripPrice(Location startPoint, Location endPoint) {
        return startPoint.euclidDistance(endPoint)*ratePerKilometer;
    }
}
