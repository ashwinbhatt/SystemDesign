package com.ashwinbhatt.CabBooking.strategies;

import com.ashwinbhatt.CabBooking.models.Location;


public abstract class DriverFindStrategy {

    public abstract String findNearestDriver(Location location);

    public abstract void addDriver(String driverId, Location location);

    public abstract void removeDriver(String driverId);
}
