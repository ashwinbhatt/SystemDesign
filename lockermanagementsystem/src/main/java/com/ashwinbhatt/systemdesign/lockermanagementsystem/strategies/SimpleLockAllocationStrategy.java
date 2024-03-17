package com.ashwinbhatt.systemdesign.lockermanagementsystem.strategies;

import com.ashwinbhatt.systemdesign.lockermanagementsystem.exceptions.LockerAllocationStrategyException;
import com.ashwinbhatt.systemdesign.lockermanagementsystem.models.Item;
import com.ashwinbhatt.systemdesign.lockermanagementsystem.models.LockerSlot;

import java.util.*;

public class SimpleLockAllocationStrategy extends ILockerAllocationStrategy {

    private final List<LockerSlot> lockerSlots;
    private final Map<Integer, Map<LockerSlot, String>> dayWiseSlotMapping;

    protected SimpleLockAllocationStrategy(String lockerId, int lockerExpDays) {
        super(lockerId, lockerExpDays);
        this.lockerSlots = new ArrayList<LockerSlot>();
        this.dayWiseSlotMapping = new HashMap<Integer, HashMap<LockerSlot, String>>();
    }

    public Set<LockerSlot> getAvailableSlotsSet(Item item, Integer startDay, Integer endDay) {
        Set<LockerSlot> availableSlots = new HashSet<LockerSlot>(lockerSlots);
        for(int i = startDay;i<endDay;i++) {
            if(!dayWiseSlotMapping.containsKey(i)) {
                continue;
            }
            for(LockerSlot busySlots : dayWiseSlotMapping.get(i).keySet()) {
                availableSlots.remove(busySlots);
            }
        }
        return availableSlots;
    }

    public synchronized List<LockerSlot> getAvailableSlots(Item item, Integer startDay) throws LockerAllocationStrategyException {
        int endDay = startDay + LOCKER_EXP_DAYS;
        Set<LockerSlot> availableSlots = getAvailableSlotsSet(item, startDay, endDay);
        return new ArrayList<LockerSlot>(availableSlots);
    }

    public synchronized LockerSlot allocateSlot(Item item, Integer startDay, LockerSlot lockerSlot, String userId) throws LockerAllocationStrategyException {
        int endDay = startDay + LOCKER_EXP_DAYS;
        Set<LockerSlot> availableSlots = getAvailableSlotsSet(item, startDay, endDay);
        if (!availableSlots.contains(lockerSlot)) {
            throw new LockerAllocationStrategyException(String.format("Locker <%s>, cannot book slot <%s>. It's unavailable", lockerId, lockerSlot.getLockerNumber()));
        }
        for(int i=startDay;i<endDay;i++) {
            if(!dayWiseSlotMapping.containsKey(i)) {
                dayWiseSlotMapping.put(i, new HashMap<LockerSlot, String>());
            }
            dayWiseSlotMapping.get(i).put(lockerSlot, userId);
        }
        return lockerSlot;
    }

    public synchronized void deallocateSlot(Integer startDay, LockerSlot lockerSlot, String userId) throws LockerAllocationStrategyException {
        int endDay = startDay + LOCKER_EXP_DAYS;
        // check this user has booked slot for given days
        boolean hasBooked = true;
        for(int i=startDay;i<endDay;i++) {
            if(!dayWiseSlotMapping.containsKey(i)) {
                hasBooked = false;
            }
            if(!(dayWiseSlotMapping.get(i).containsKey(lockerSlot) && userId.equals(dayWiseSlotMapping.get(i).get(lockerSlot)))) {
                hasBooked = false;
            }
            if(!hasBooked) {
                break;
            }
        }
        if(!hasBooked) {
            throw new LockerAllocationStrategyException(String.format("Locker <%s>, cannot deallocate slot <%s>. It's not booked", lockerId, lockerSlot.getLockerNumber()))
        }
        // deallocate slots
        for(int i=startDay;i<endDay;i++) {
            dayWiseSlotMapping.get(i).remove(lockerSlot);
        }
    }

}
