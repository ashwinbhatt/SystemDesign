package com.ashwinbhatt.ParkingLot.exception;

public class RegistrationNumberNotFound extends RuntimeException{
    public RegistrationNumberNotFound(String message){
        super(message);
    }
}
