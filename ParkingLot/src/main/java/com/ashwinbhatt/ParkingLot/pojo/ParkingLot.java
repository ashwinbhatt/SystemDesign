package com.ashwinbhatt.ParkingLot.pojo;

import com.ashwinbhatt.ParkingLot.exception.InvalidSlotNumberException;
import com.ashwinbhatt.ParkingLot.exception.RegistrationNumberNotFound;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ParkingLot {
    Map<Integer, Slot> slotMap;
    final Integer MAX_CAPACITY;

    public Integer getMAX_CAPACITY(){
        return this.MAX_CAPACITY;
    }

    public ParkingLot(Integer maxCapacity){
        this.MAX_CAPACITY= maxCapacity;
        slotMap= new HashMap<>();
        for(int i=0;i<this.MAX_CAPACITY;i++){
            slotMap.put(i, new Slot(i));
        }
    }

    public Slot getSlot(int slotNumber){
        Slot slot= slotMap.get(slotNumber);
        if(slot == null){
            throw new InvalidSlotNumberException(String.format("Slot Number %s INVALID!", slotNumber));
        }
        return slot;
    }

    public void parkVehicle(int slotNumber, Vehicle vehicle){
        Slot slot= getSlot(slotNumber);
        slot.occupySlot(vehicle);
    }

    public Vehicle unParkVehicle(int slotNumber){
        Slot slot= getSlot(slotNumber);
        Vehicle vehicle= slot.getOccupyingVehicle();
        slot.unOccupySlot();
        return vehicle;
    }

    public List<String> getRegistrationNumberParticularColor(String color){
        List<String> registrationNumberList= slotMap.values().stream()
                .filter(slot -> slot.isOccupied())
                .map(slot -> slot.getOccupyingVehicle())
                .filter(vehicle -> vehicle.getColor().equals(color))
                .map(vehicle -> vehicle.getRegistrationNumber())
                .collect(Collectors.toList());
        return registrationNumberList;
    }

    public Integer getSlotNumberOfVehicle(String registrationNumber){
        for(Slot slot: slotMap.values()){
            if(slot.isOccupied() && slot.getOccupyingVehicle().getRegistrationNumber().equals(registrationNumber)){
                return slot.getSlotNumber();
            }
        }
        throw new RegistrationNumberNotFound(String.format("Vehicle with registration number %s not in parking log.", registrationNumber));
    }

    public List<Integer> getSlotNumberOfVehicleWithColor(String color){
        List<Integer> registrationNumberList= slotMap.values().stream()
                .filter(slot -> slot.isOccupied() && slot.getOccupyingVehicle().getColor().equals(color))
                .map(slot -> slot.getSlotNumber())
                .collect(Collectors.toList());
        return registrationNumberList;
    }

    public Integer getParkVehicleCount(){
        return (int)slotMap.values().stream()
                .filter(slot -> slot.isOccupied())
                .count();
    }
}
