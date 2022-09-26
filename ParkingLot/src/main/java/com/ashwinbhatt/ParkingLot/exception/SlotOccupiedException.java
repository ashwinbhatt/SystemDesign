package com.ashwinbhatt.ParkingLot.exception;

public class SlotOccupiedException extends RuntimeException {
    public SlotOccupiedException(String message){
        super(message);
    }
}
