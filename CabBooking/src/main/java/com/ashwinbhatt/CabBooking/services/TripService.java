package com.ashwinbhatt.CabBooking.services;

import com.ashwinbhatt.CabBooking.exceptions.TripException;
import com.ashwinbhatt.CabBooking.models.Driver;
import com.ashwinbhatt.CabBooking.models.Location;
import com.ashwinbhatt.CabBooking.models.Rider;
import com.ashwinbhatt.CabBooking.models.Trip;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TripService {
    private List<Trip> tripRepository;

    public TripService(){
        tripRepository= new LinkedList<>();
    }

    public Trip findTrip(String tripId) throws TripException {
        Optional<Trip> optionalTrip= tripRepository.stream()
                .filter(trip -> trip.getTripId().equals(tripId))
                .findAny();

        if(!optionalTrip.isPresent()){
            throw new TripException("Trip with Trip ID "+ tripId+ " not found");
        }
        return optionalTrip.get();
    }

    public String createNewTrip( Rider rider,  Driver driver,  Location startPoint,  Location endPoint,  Double price){
        Trip trip= new Trip(startPoint, endPoint, driver, rider, price);
        driver.setAvailabilityStatus(false);
        trip.setIsComplete(false);
        tripRepository.add(trip);
        return trip.getTripId();
    }

    public void endTrip( String tripId) throws TripException {
        Trip trip= findTrip(tripId);
        if(trip.getIsComplete()){
            throw new TripException("Trip with tripId: "+tripId+ "already over");
        }
        trip.getDriver().setAvailabilityStatus(true);
        trip.setIsComplete(true);
    }
    
    public List<Trip> riderHistory(String riderId){
        List<Trip> riderTrips= tripRepository.stream()
                                    .filter(trip -> trip.getRider().getRiderId().equals(riderId))
                                    .collect(Collectors.toList());
        return riderTrips;
    }
}
