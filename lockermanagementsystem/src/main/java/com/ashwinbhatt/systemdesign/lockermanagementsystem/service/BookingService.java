package com.ashwinbhatt.systemdesign.lockermanagementsystem.service;

import com.ashwinbhatt.systemdesign.lockermanagementsystem.exceptions.BookingServiceException;
import com.ashwinbhatt.systemdesign.lockermanagementsystem.exceptions.BookingUserRepoException;
import com.ashwinbhatt.systemdesign.lockermanagementsystem.exceptions.DeliveryUserRepoException;
import com.ashwinbhatt.systemdesign.lockermanagementsystem.exceptions.LockerAllocationStrategyException;
import com.ashwinbhatt.systemdesign.lockermanagementsystem.models.*;
import com.ashwinbhatt.systemdesign.lockermanagementsystem.repositories.BookingRepository;
import com.ashwinbhatt.systemdesign.lockermanagementsystem.repositories.DeliveryUserRepo;
import com.ashwinbhatt.systemdesign.lockermanagementsystem.repositories.ItemRepo;
import com.ashwinbhatt.systemdesign.lockermanagementsystem.strategies.ILockerAllocationStrategy;
import com.ashwinbhatt.systemdesign.lockermanagementsystem.strategies.IOTPGenStrategy;

import java.util.Map;
import java.util.UUID;

public class BookingService {

    private final UserService userService;
    private final Map<String, ILockerAllocationStrategy> lockerAllocationStrategyMap;
    private final IOTPGenStrategy iotpGenStrategy;
    private final ItemRepo itemRepo;
    private final BookingRepository bookingRepository;
    private final DeliveryUserRepo deliveryUserRepo;

    public BookingService(UserService userService, Map<String, ILockerAllocationStrategy> lockerAllocationStrategyMap, IOTPGenStrategy iotpGenStrategy, DeliveryUserRepo deliveryUserRepo, ItemRepo itemRepo, BookingRepository bookingRepository) {
        this.userService = userService;
        this.lockerAllocationStrategyMap = lockerAllocationStrategyMap;
        this.iotpGenStrategy = iotpGenStrategy;
        this.deliveryUserRepo = deliveryUserRepo;
        this.itemRepo = itemRepo;
        this.bookingRepository = bookingRepository;
    }

    public Booking doBooking(BookingUser bookingUser, DeliveryUser deliveryUser, Integer startDate, LockerSlot lockerSlot, Item item) throws BookingUserRepoException, DeliveryUserRepoException, BookingServiceException, LockerAllocationStrategyException {
        userService.getBookingUser(bookingUser.getUserId());
        userService.getDeliveryUser(deliveryUser.getUserId());
        ILockerAllocationStrategy iLockerAllocationStrategy = lockerAllocationStrategyMap.get(lockerSlot.getContainingLocker().getLockerId());
        if(iLockerAllocationStrategy == null) {
            throw new BookingServiceException(String.format("No locker found with id <%s>", lockerSlot.getContainingLocker().getLockerId()));
        }
        itemRepo.addItem(item);
        iLockerAllocationStrategy.allocateSlot(item, startDate, lockerSlot, bookingUser.getUserId());
        Booking booking = new Booking(
                UUID.randomUUID().toString(),
                bookingUser,
                deliveryUser,
                item,
                lockerSlot,
                iotpGenStrategy.generateOTP(),
                startDate);
        return booking;
    }

    public Booking undoBooking(Booking booking) throws BookingUserRepoException, DeliveryUserRepoException, BookingServiceException, LockerAllocationStrategyException {
        userService.getBookingUser(booking.getBookingUser().getUserId());
        deliveryUserRepo.getDeliveryUser(booking.getDeliveryUser().getUserId());
        ILockerAllocationStrategy lockerAllocationStrategy = lockerAllocationStrategyMap.get(booking.getLockerSlot().getContainingLocker().getLockerId());
        if(lockerAllocationStrategy == null) {
            throw new BookingServiceException(String.format("No locker found with id <%s>", booking.getLockerSlot().getContainingLocker().getLockerId()));
        }
        lockerAllocationStrategy.deallocateSlot(booking.getStartDate(), booking.getLockerSlot(), booking.getBookingUser().getUserId());
        return booking;
    }
}
