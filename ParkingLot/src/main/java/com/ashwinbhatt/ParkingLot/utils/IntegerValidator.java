package com.ashwinbhatt.ParkingLot.utils;

public class IntegerValidator {
    public static boolean isInteger(final String val){
        try{
            Integer.parseInt(val);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }
}
