package com.ashwinbhatt.systemdesign.lockermanagementsystem.strategies;

public class RandomNumOTPGenStrategy implements IOTPGenStrategy{

    private final int OTP_LENGTH;

    public RandomNumOTPGenStrategy(int otpLength) {
        OTP_LENGTH = otpLength;
    }

    public String generateOTP() {
        // assuming OTP_LENGTH precision can be handled by double
        double randomVal = Math.random();
        randomVal *= Math.pow(10, OTP_LENGTH);
        int randomInt = (int) randomVal;
        String ranStr = Integer.toString(randomInt);
        while(ranStr.length() < OTP_LENGTH) {
            ranStr = "0" + ranStr;
        }
        return ranStr;
    }
}
