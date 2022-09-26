package com.ashwinbhatt.ParkingLot.service;

import com.ashwinbhatt.ParkingLot.exception.ParkingLotException;
import com.ashwinbhatt.ParkingLot.pojo.ParkingLot;
import com.ashwinbhatt.ParkingLot.pojo.Vehicle;
import com.ashwinbhatt.ParkingLot.strategy.ParkingStrategy;

import java.util.List;

public class ParkingLotService {
    ParkingLot parkingLot;
    ParkingStrategy strategy;

    public void createParkingLot(final ParkingLot parkingLot, final ParkingStrategy parkingStrategy){
        if(this.parkingLot != null){
            throw new ParkingLotException("Parking log already exists.");
        }
        this.parkingLot= parkingLot;
        this.strategy= parkingStrategy;
    }

    public Integer parkVehicle(Vehicle vehicle){
        Integer availableSlot= strategy.getAvailableSlot();
        parkingLot.parkVehicle(availableSlot, vehicle);
        return availableSlot;
    }

    public Vehicle unParkVehicle(Integer slotNo){
        Vehicle vehicle= parkingLot.unParkVehicle(slotNo);
        strategy.freeSLot(slotNo);
        return vehicle;
    }

    public Integer[] parkingLotStatus(){
        Integer parkCount= parkingLot.getParkVehicleCount();
        Integer totalSlot= parkingLot.getMAX_CAPACITY();
        return new Integer[]{parkCount, totalSlot};
    }

    public List<String> getRegistrationNumberWithColor(String color){
        return parkingLot.getRegistrationNumberParticularColor(color);
    }

    public Integer getSlotWithRegistrationNumber(String registrationNumber){
        return parkingLot.getSlotNumberOfVehicle(registrationNumber);
    }

    public List<Integer> getSlotWithColor(String color){
        return parkingLot.getSlotNumberOfVehicleWithColor(color);
    }
}
