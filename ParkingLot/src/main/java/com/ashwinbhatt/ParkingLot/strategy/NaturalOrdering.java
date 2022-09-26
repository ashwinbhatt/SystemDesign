package com.ashwinbhatt.ParkingLot.strategy;

import com.ashwinbhatt.ParkingLot.exception.SlotNotAvailableException;

import java.util.HashMap;
import java.util.Map;

public class NaturalOrdering extends ParkingStrategy {

    Map<Integer, Boolean> slotAvailability;

    public NaturalOrdering(Integer maxCapacity){
        super(maxCapacity);
        slotAvailability = new HashMap<>();
        for(int i=0;i<maxCapacity;i++){
            slotAvailability.put(i, true);
        }
    }

    @Override
    public Integer getAvailableSlot() {
        Integer freeSlot= null;
        for(int i=0;i<MAX_CAPACITY;i++){
            if(slotAvailability.get(i)){
                slotAvailability.put(i, false);
                freeSlot= i;
                break;
            }
        }
        if(freeSlot == null){
            throw new SlotNotAvailableException("No slot available");
        }
        return  freeSlot;
    }

    @Override
    public void freeSLot(Integer slotNo) {
        slotAvailability.put(slotNo, true);
    }
}
