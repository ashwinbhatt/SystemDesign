package com.ashwinbhatt.CabBooking.strategies;

import com.ashwinbhatt.CabBooking.models.Location;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class EuclidienFindStrategy extends DriverFindStrategy{
    private Map<String, Location> driverLocations;

    public EuclidienFindStrategy(){
        driverLocations= new HashMap<>();
    }

    @Override
    public String findNearestDriver(Location location) {
        String nearestDriverId= null;
        double nearest= Double.MAX_VALUE;
        for(String driverId: driverLocations.keySet()){
            double distance= location.euclidDistance(driverLocations.get(driverId));
            if(distance<nearest){
                nearest= distance;
                nearestDriverId= driverId;
            }
        }
        return nearestDriverId;
    }

    @Override
    public void addDriver(String driverId, Location location) {
        driverLocations.put(driverId, location);
    }

    @Override
    public void removeDriver(String driverId) {
        driverLocations.remove(driverId);
    }
}
