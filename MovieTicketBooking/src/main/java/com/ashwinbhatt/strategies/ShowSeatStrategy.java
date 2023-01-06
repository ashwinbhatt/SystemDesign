package com.ashwinbhatt.strategies;

import java.util.List;

public abstract class ShowSeatStrategy {
    private final Long TEMPORARY_LOCK_MAX_WAIT_TIME;

    public ShowSeatStrategy(Long maxLockTimePeriod) {
        TEMPORARY_LOCK_MAX_WAIT_TIME = null;
    }

    public boolean checkIfTemporaryLockReleased(long lockTime){
        if(System.currentTimeMillis()-lockTime>=TEMPORARY_LOCK_MAX_WAIT_TIME){
            return true;
        }
        return false;
    }

    public abstract List<Integer> getAvailableSeats();
    public abstract boolean checkInSeats(List<Integer> seatNumbers, String userId);
    public abstract boolean checkOutSeats(List<Integer> seatNumbers, String userId);
    public abstract boolean unlockSeats(List<Integer> seatNumbers);
    public abstract boolean checkIfTempLockByUser(List<Integer> seatNumbers, String userId);

}
