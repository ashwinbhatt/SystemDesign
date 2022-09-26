package com.ashwinbhatt.ParkingLot.pojo;

import com.ashwinbhatt.ParkingLot.exception.SlotNotOccupiedException;
import com.ashwinbhatt.ParkingLot.exception.SlotOccupiedException;

public class Slot {
    Integer slotNumber;
    boolean isOccupied;
    Vehicle occupyingVehicle;

    public Slot(Integer slotNumber){
        this.slotNumber= slotNumber;
        isOccupied= false;
        occupyingVehicle = null;
    }

    public Integer getSlotNumber(){
        return slotNumber;
    }

    public boolean isOccupied(){
        return this.isOccupied;
    }

    public void occupySlot(Vehicle vehicle){
        if(this.isOccupied()){
            throw new SlotOccupiedException(String.format("Slot %s already occupied", slotNumber));
        }
        isOccupied= true;
        occupyingVehicle= vehicle;
    }

    public Vehicle getOccupyingVehicle(){
        if(!this.isOccupied()){
            throw new SlotNotOccupiedException(String.format("Slot %s not occupied", slotNumber));
        }
        return occupyingVehicle;
    }

    public void unOccupySlot(){
        if(!this.isOccupied()){
            throw new SlotNotOccupiedException(String.format("Slot %s not occupied", slotNumber));
        }
        this.isOccupied= false;
        this.occupyingVehicle= null;
    }
}
