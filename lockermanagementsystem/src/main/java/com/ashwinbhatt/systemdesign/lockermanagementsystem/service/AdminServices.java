package com.ashwinbhatt.systemdesign.lockermanagementsystem.service;

import com.ashwinbhatt.systemdesign.lockermanagementsystem.exceptions.LockerRepoException;
import com.ashwinbhatt.systemdesign.lockermanagementsystem.exceptions.LockerSlotRepoException;
import com.ashwinbhatt.systemdesign.lockermanagementsystem.models.Locker;
import com.ashwinbhatt.systemdesign.lockermanagementsystem.models.LockerSlot;
import com.ashwinbhatt.systemdesign.lockermanagementsystem.repositories.BookingUserRepo;
import com.ashwinbhatt.systemdesign.lockermanagementsystem.repositories.DeliveryUserRepo;
import com.ashwinbhatt.systemdesign.lockermanagementsystem.repositories.LockerRepo;

import java.util.UUID;

public class AdminServices {

    private final DeliveryUserRepo deliveryUserRepo;
    private final BookingUserRepo bookingUserRepo;
    private final LockerRepo lockerRepo;

    public AdminServices(DeliveryUserRepo deliveryUserRepo, BookingUserRepo bookingUserRepo, LockerRepo lockerRepo) {
        this.deliveryUserRepo = deliveryUserRepo;
        this.bookingUserRepo = bookingUserRepo;
        this.lockerRepo = lockerRepo;
    }

    public Locker createLocker()  {
        Locker locker = null;
        while(locker == null) {
            String genId = UUID.randomUUID().toString();
            locker = new Locker(genId);
            try {
                lockerRepo.addLocker(locker);
            } catch (LockerRepoException e) {
                locker = null;
            }
        }
        return locker;
    }

    public LockerSlot createLockerSlot(String lockerId) throws LockerRepoException, LockerSlotRepoException {
        Locker locker = lockerRepo.getLocker(lockerId);
        int lockerSz = lockerRepo.getLockerSlotCount(lockerId);
        LockerSlot lockerSlot = new LockerSlot(lockerSz, locker);
        lockerRepo.addLockerSlot(lockerSlot);
        return lockerSlot;
    }
}
