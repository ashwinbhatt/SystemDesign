package com.ashwinbhatt.ParkingLot.strategy;

public abstract class ParkingStrategy {

    protected final Integer MAX_CAPACITY;

    public ParkingStrategy(Integer maxCapacity){
        this.MAX_CAPACITY= maxCapacity;
    }
    abstract public Integer getAvailableSlot();
    abstract public void freeSLot(Integer slotNo);
}
