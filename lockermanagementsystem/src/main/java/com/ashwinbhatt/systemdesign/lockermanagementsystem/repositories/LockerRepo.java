package com.ashwinbhatt.systemdesign.lockermanagementsystem.repositories;

import com.ashwinbhatt.systemdesign.lockermanagementsystem.exceptions.LockerRepoException;
import com.ashwinbhatt.systemdesign.lockermanagementsystem.exceptions.LockerSlotRepoException;
import com.ashwinbhatt.systemdesign.lockermanagementsystem.models.Locker;
import com.ashwinbhatt.systemdesign.lockermanagementsystem.models.LockerSlot;

import java.util.HashMap;
import java.util.Map;

public class LockerRepo {

    private final Map<String, Locker> lockerRepoMap;

    private final Map<String, Map<Integer, LockerSlot>> lockerSlotMaps;

    public LockerRepo() {
        lockerRepoMap = new HashMap();
        lockerSlotMaps = new HashMap();
    }

    public void addLocker(Locker locker) throws LockerRepoException {
        if(lockerRepoMap.containsKey(locker)) {
            throw new LockerRepoException(String.format("Locker with id: <%s>, already exist", locker.getLockerId()));
        }
        lockerRepoMap.put(locker.getLockerId(), locker);
        lockerSlotMaps.put(locker.getLockerId(), new HashMap());
    }

    public Locker getLocker(String lockerId) throws LockerRepoException {
        Locker locker = lockerRepoMap.get(lockerId);
        if(locker == null) {
            throw new LockerRepoException(String.format("Locker with id: <%s>, already exist", lockerId));
        }
        return locker;
    }

    public void addLockerSlot(LockerSlot lockerSlot) throws LockerRepoException, LockerSlotRepoException {
        Locker locker = getLocker(lockerSlot.getContainingLocker().getLockerId());
        Map<Integer, LockerSlot> lockerSlotMap = lockerSlotMaps.get(locker.getLockerId());
        if(lockerSlotMap.containsKey(lockerSlot.getLockerNumber())) {
            throw new LockerSlotRepoException(String.format("Locker Slot with id: <%s>, already exist", lockerSlot.getLockerNumber()));
        }
        lockerSlotMap.put(lockerSlot.getLockerNumber(), lockerSlot);
    }

    public LockerSlot getLockerSlot(String lockerId, Integer lockerSlotNumber) throws LockerRepoException, LockerSlotRepoException {
        Locker locker = getLocker(lockerId);
        Map<Integer, LockerSlot> lockerSlotMap = lockerSlotMaps.get(lockerId);
        if(!lockerSlotMap.containsKey(lockerSlotNumber)) {
            throw new LockerSlotRepoException(String.format("Locker Slot with id: <%s>, don't exist", lockerSlotNumber));
        }
        return lockerSlotMap.get(lockerSlotNumber);
    }

    public int getLockerSlotCount(String lockerId) throws LockerRepoException {
        Locker locker = getLocker(lockerId);
        return lockerSlotMaps.get(lockerId).size();
    }
}
