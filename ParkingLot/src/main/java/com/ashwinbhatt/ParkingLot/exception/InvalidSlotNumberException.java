package com.ashwinbhatt.ParkingLot.exception;

public class InvalidSlotNumberException extends RuntimeException{

    public InvalidSlotNumberException(String message){
        super(message);
    }
}
