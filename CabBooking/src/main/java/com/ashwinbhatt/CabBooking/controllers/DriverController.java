package com.ashwinbhatt.CabBooking.controllers;

import com.ashwinbhatt.CabBooking.models.Driver;
import com.ashwinbhatt.CabBooking.models.Location;
import com.ashwinbhatt.CabBooking.models.Rider;
import com.ashwinbhatt.CabBooking.models.Trip;
import com.ashwinbhatt.CabBooking.services.CabAppService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class DriverController {

    public final CabAppService cabAppService;

    public DriverController(CabAppService cabAppService){
        this.cabAppService= cabAppService;
    }

    @RequestMapping(path = "/rider", method = RequestMethod.POST)
    public Rider addRider(@RequestBody Rider rider){
        Rider newRider= cabAppService.registerRider(rider.getName());
        return newRider;
    }

    @RequestMapping(path = "/driver", method = RequestMethod.POST)
    public Driver addDriver(@RequestBody Driver driver){
        Driver newDriver= cabAppService.registerDriver(driver.getName(), driver.getCabRegistrationNumber());
        return newDriver;
    }

    @RequestMapping(path = "/driver/location", method = RequestMethod.POST)
    public boolean updateDriverLocation(@RequestParam(value = "driverId", required = true) String driverId, @RequestBody Location location){
        boolean isLocationUpdate= cabAppService.updateDriverLocation(driverId, location);
        return isLocationUpdate;
    }

    @RequestMapping(path = "/driver/availability", method = RequestMethod.PUT)
    public boolean updateDriverAvailability(@RequestParam(value = "driverId", required = true) String driverId, @RequestParam(value = "availability", required = true) boolean availability){
        if(availability){
            return cabAppService.makeDriverAvailable(driverId);
        }else{
            return cabAppService.makeDriverUnavailable(driverId);
        }
    }

    @RequestMapping(path = "/bookcab", method = RequestMethod.POST)
    public Trip bookCab(
            @RequestParam(value = "riderId", required = true) String riderId,
            @RequestBody Location[] locations){
        Trip trip= cabAppService.createTrip(riderId, locations[0], locations[1]);
        return trip;
    }

    @RequestMapping(path = "/rider/history", method = RequestMethod.GET)
    public List<Trip> riderHistory(
            @RequestParam(value = "riderId", required = true) String riderId
    ){
        List<Trip> trips= cabAppService.fetchRiderHistory(riderId);
        return trips;
    }

    @RequestMapping(path = "/bookcab", method = RequestMethod.DELETE)
    public boolean bookCab(@RequestParam(value = "tripId", required = true) String tripId){
        return cabAppService.endTrip(tripId);
    }





    }
