package com.ashwinbhatt.CabBooking.services;

import com.ashwinbhatt.CabBooking.exceptions.DriverException;
import com.ashwinbhatt.CabBooking.models.Driver;
import com.ashwinbhatt.CabBooking.models.Location;
import com.sun.istack.NonNull;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class DriverService {
    private final List<Driver> driverRepository;

    public DriverService(){
        driverRepository= new LinkedList<>();
    }

    public String addDriver(@NonNull String name,@NonNull String registrationNumber){
        Driver driver= new Driver(name, registrationNumber);
        driverRepository.add(driver);
        return driver.getDriverId();
    }

    public Driver findDriver(String driverId) throws DriverException {
        Optional<Driver> driverOption = driverRepository.stream()
                .filter(driver -> driver.getDriverId().equals(driverId))
                .findFirst();
        if(!driverOption.isPresent()){
            throw new DriverException("Driver with ID: "+ driverId+" not found");
        }
        return driverOption.get();
    }

    public void setDriverLocation(@NonNull String driverId,@NonNull Location location) throws DriverException {
        Driver driver= findDriver(driverId);
        driver.setLocation(location);
    }

    public void changeDriverAvailabilityStatus(@NonNull String driverId, @NonNull Boolean availabilityStatus) throws DriverException {
        Driver driver= findDriver(driverId);
        driver.setAvailabilityStatus(availabilityStatus);
    }


}
