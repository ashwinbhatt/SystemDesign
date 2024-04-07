package com.ashwinbhatt.systemdesign.lockermanagementsystem.service;

import com.ashwinbhatt.systemdesign.lockermanagementsystem.exceptions.BookingServiceException;
import com.ashwinbhatt.systemdesign.lockermanagementsystem.exceptions.BookingUserRepoException;
import com.ashwinbhatt.systemdesign.lockermanagementsystem.exceptions.DeliveryUserRepoException;
import com.ashwinbhatt.systemdesign.lockermanagementsystem.exceptions.LockerAllocationStrategyException;
import com.ashwinbhatt.systemdesign.lockermanagementsystem.models.*;
import com.ashwinbhatt.systemdesign.lockermanagementsystem.repositories.BookingRepository;
import com.ashwinbhatt.systemdesign.lockermanagementsystem.repositories.ItemRepo;
import com.ashwinbhatt.systemdesign.lockermanagementsystem.strategies.IDeliveryUserAllocator;
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
    private final IDeliveryUserAllocator deliveryUserAllocator;

    public BookingService(UserService userService, Map<String, ILockerAllocationStrategy> lockerAllocationStrategyMap, IOTPGenStrategy iotpGenStrategy, IDeliveryUserAllocator deliveryUserAllocator, ItemRepo itemRepo, BookingRepository bookingRepository) {
        this.userService = userService;
        this.lockerAllocationStrategyMap = lockerAllocationStrategyMap;
        this.iotpGenStrategy = iotpGenStrategy;
        this.deliveryUserAllocator = deliveryUserAllocator;
        this.itemRepo = itemRepo;
        this.bookingRepository = bookingRepository;
    }

    public Booking doBooking(BookingUser bookingUser, Integer startDate, LockerSlot lockerSlot, Item item, BookingTypes bookingTypes) throws BookingUserRepoException, DeliveryUserRepoException, BookingServiceException, LockerAllocationStrategyException {
        userService.getBookingUser(bookingUser.getUserId());
        DeliveryUser deliveryUser = deliveryUserAllocator.getDeliveryUser(startDate, lockerSlot.getContainingLocker(), item);
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
                startDate, bookingTypes);
        bookingRepository.addBooking(booking);
        return booking;
    }

    public Booking undoBooking(Booking booking) throws BookingUserRepoException, DeliveryUserRepoException, BookingServiceException, LockerAllocationStrategyException {
        bookingRepository.getBooking(booking.getBookingId());
        userService.getBookingUser(booking.getBookingUser().getUserId());
        userService.getDeliveryUser(booking.getDeliveryUser().getUserId());
        ILockerAllocationStrategy lockerAllocationStrategy = lockerAllocationStrategyMap.get(booking.getLockerSlot().getContainingLocker().getLockerId());
        if(lockerAllocationStrategy == null) {
            throw new BookingServiceException(String.format("No locker found with id <%s>", booking.getLockerSlot().getContainingLocker().getLockerId()));
        }
        lockerAllocationStrategy.deallocateSlot(booking.getStartDate(), booking.getLockerSlot(), booking.getBookingUser().getUserId());
        return booking;
    }

}
