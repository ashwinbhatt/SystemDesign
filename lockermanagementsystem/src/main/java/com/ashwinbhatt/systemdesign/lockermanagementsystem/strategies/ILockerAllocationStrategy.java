package com.ashwinbhatt.systemdesign.lockermanagementsystem.strategies;

import com.ashwinbhatt.systemdesign.lockermanagementsystem.exceptions.LockerAllocationStrategyException;
import com.ashwinbhatt.systemdesign.lockermanagementsystem.models.Item;
import com.ashwinbhatt.systemdesign.lockermanagementsystem.models.LockerSlot;

import java.util.List;

public abstract class ILockerAllocationStrategy {

    protected final String lockerId;
    protected final int LOCKER_EXP_DAYS;

    protected ILockerAllocationStrategy(String lockerId, int lockerExpDays) {
        this.lockerId = lockerId;
        LOCKER_EXP_DAYS = lockerExpDays;
    }

    public abstract List<LockerSlot> getAvailableSlots(Item item, Integer startDay) throws LockerAllocationStrategyException;

    public abstract LockerSlot allocateSlot(Item item, Integer startDay, LockerSlot lockerSlot, String userId)throws LockerAllocationStrategyException;

    public abstract void deallocateSlot(Integer startDay, LockerSlot lockerSlot, String userId) throws LockerAllocationStrategyException;
}
