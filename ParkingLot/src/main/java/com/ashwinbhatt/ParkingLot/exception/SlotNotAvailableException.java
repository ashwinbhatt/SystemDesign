package com.ashwinbhatt.ParkingLot.exception;

public class SlotNotAvailableException extends RuntimeException{
    public SlotNotAvailableException(String message){
        super(message);
    }
}
