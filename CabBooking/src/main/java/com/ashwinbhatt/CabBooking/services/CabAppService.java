package com.ashwinbhatt.CabBooking.services;

import com.ashwinbhatt.CabBooking.exceptions.DriverException;
import com.ashwinbhatt.CabBooking.exceptions.RiderException;
import com.ashwinbhatt.CabBooking.exceptions.TripException;
import com.ashwinbhatt.CabBooking.models.Driver;
import com.ashwinbhatt.CabBooking.models.Location;
import com.ashwinbhatt.CabBooking.models.Rider;
import com.ashwinbhatt.CabBooking.models.Trip;
import com.ashwinbhatt.CabBooking.strategies.DriverFindStrategy;
import com.ashwinbhatt.CabBooking.strategies.EuclidienFindStrategy;
import com.ashwinbhatt.CabBooking.strategies.PricingStrategy;
import com.ashwinbhatt.CabBooking.strategies.SimplePricingStrategy;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CabAppService {
    public final DriverService driverService;
    public final RiderService riderService;
    public final TripService tripService;
    public final DriverFindStrategy driverFindStrategy;
    public final PricingStrategy pricingStrategy;

    public Trip createTrip(String riderId, Location riderLocation, Location destinationLocation) {
        Rider rider;
        Driver driver;
        try {
            rider = riderService.findRider(riderId);
            String driverId= driverFindStrategy.findNearestDriver(riderLocation);
            driver= driverService.findDriver(driverId);
        } catch (RiderException | DriverException e) {
            System.out.println(e.getMessage());
            return null;
        }
        Double price= pricingStrategy.findTripPrice(riderLocation, destinationLocation);
        String tripId= tripService.createNewTrip(rider, driver, riderLocation, destinationLocation, price);
        Trip trip= null;
        try {
            trip = tripService.findTrip(tripId);
        } catch (TripException e) {
            System.out.println(e.getMessage());
            return null;
        }
        makeDriverUnavailable(driver.getDriverId());
        return trip;
    }

    public Rider registerRider(String name) {
        String riderId= riderService.addRider(name);
        try {
            return riderService.findRider(riderId);
        } catch (RiderException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Driver registerDriver(String name, String registrationNumber){
        String driverId= driverService.addDriver(name, registrationNumber);
        try {
            return driverService.findDriver(driverId);
        } catch (DriverException driverException) {
            System.out.println(driverException.getMessage());
            return null;
        }
    }

    public boolean updateDriverLocation(String driverId, Location updatedLocation) {
        try{
            driverService.setDriverLocation(driverId, updatedLocation);
            driverFindStrategy.addDriver(driverId, updatedLocation);
            return true;
        }catch (DriverException driverException){
            System.out.println(driverException.getMessage());
            return false;
        }
    }

    public boolean makeDriverAvailable(String driverId){
        try {
            driverService.changeDriverAvailabilityStatus(driverId, true);
        } catch (DriverException driverException) {
            System.out.println(driverException.getMessage());
            return false;
        }
        return true;
    }

    public boolean makeDriverUnavailable(String driverId){
        try {
            driverService.changeDriverAvailabilityStatus(driverId, false);
            driverFindStrategy.removeDriver(driverId);
        } catch (DriverException driverException) {
            System.out.println(driverException.getMessage());
            return false;
        }
        return true;
    }

    public List<Trip> fetchRiderHistory(String riderId){
        try {
            riderService.findRider(riderId);
        } catch (RiderException e) {
            System.out.println(e.getMessage());
        }

        return tripService.riderHistory(riderId);
    }

    public boolean endTrip(String tripId){
        try {
            tripService.endTrip(tripId);
            Trip trip= tripService.findTrip(tripId);
            makeDriverAvailable(trip.getDriver().getDriverId());
            return true;
        } catch (TripException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
