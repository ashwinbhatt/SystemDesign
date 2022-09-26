package com.ashwinbhatt.ParkingLot.exception;

public class InvalidCommandException extends RuntimeException{
    public InvalidCommandException(String message){
        super(message);
    }
}
