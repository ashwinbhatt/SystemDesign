package com.ashwinbhatt.systemdesign.lockermanagementsystem.models;

import lombok.Getter;


@Getter
public class LockerSlot {

    private final Integer lockerNumber;
    private final Locker containingLocker;

    public LockerSlot(Integer lockerNumber, Locker containingLocker) {
        this.lockerNumber = lockerNumber;
        this.containingLocker = containingLocker;
    }
}
