package com.ashwinbhatt.systemdesign.movieticketbooking.strategies;

import com.ashwinbhatt.systemdesign.movieticketbooking.models.Booking;
import com.ashwinbhatt.systemdesign.movieticketbooking.models.Show;

public interface PricingStrategy {

    public Double getPricing(Booking booking);
}
