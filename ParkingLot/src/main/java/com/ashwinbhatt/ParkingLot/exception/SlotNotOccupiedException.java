package com.ashwinbhatt.ParkingLot.exception;

public class SlotNotOccupiedException extends RuntimeException{

    public SlotNotOccupiedException(String message){
        super(message);
    }
}
