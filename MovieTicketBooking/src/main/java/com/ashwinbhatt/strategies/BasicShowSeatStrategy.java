package com.ashwinbhatt.strategies;

import java.util.*;
import java.util.stream.Collectors;

public class BasicShowSeatStrategy extends ShowSeatStrategy{

    private final Set<Integer> availableSeats;
    private final Map<Integer, String> temporarilyUnavailableSeats;
    private final Map<Integer, Long> temporarilyUnavailableSeatsLockTimestamps;
    private final Map<Integer, String> permanentlyUnavailableSeats;

    public BasicShowSeatStrategy(int numberOfSeats, Long maxLockTimePeriod){
        super(maxLockTimePeriod);
        availableSeats= new HashSet<>();
        temporarilyUnavailableSeats= new HashMap<>();
        permanentlyUnavailableSeats= new HashMap<>();
        temporarilyUnavailableSeatsLockTimestamps= new HashMap<>();
        for(int i=1;i<=numberOfSeats;i++){
            availableSeats.add(i);
        }
    }

    public boolean checkNReleaseTemporaryLock(Integer seatNumber) {
        if(temporarilyUnavailableSeats.containsKey(seatNumber) && checkIfTemporaryLockReleased(temporarilyUnavailableSeatsLockTimestamps.get(seatNumber))){
            temporarilyUnavailableSeats.remove(seatNumber);
            temporarilyUnavailableSeatsLockTimestamps.remove(seatNumber);
            availableSeats.add(seatNumber);
            return true;
        }
        return false;
    }

    public boolean checkIfAvailable(List<Integer> seatNumbers){
        for(Integer seatNumber: seatNumbers){
            if(!availableSeats.contains(seatNumber) || temporarilyUnavailableSeats.containsKey(seatNumber) && checkNReleaseTemporaryLock(seatNumber)) {
                return false;
            }
        }
        return true;
    }

    public boolean checkIfTempLockByUser(List<Integer> seatNumbers, String userId){
        for(Integer seatNumber: seatNumbers){
            if(!temporarilyUnavailableSeats.containsKey(seatNumber) ||
                    (temporarilyUnavailableSeats.get(seatNumber).equals(userId) && !checkNReleaseTemporaryLock(seatNumber))){
                return false;
            }
        }
        return true;
    }

    public boolean checkIfPermLock(List<Integer> seatNumbers){
        for(Integer seatNumber: seatNumbers){
            if(!permanentlyUnavailableSeats.containsKey(seatNumber)){
                return false;
            }
        }
        return true;
    }

    @Override
    public List<Integer> getAvailableSeats() {
        for(Integer seatNumber: temporarilyUnavailableSeats.keySet()){
            checkIfTemporaryLockReleased(seatNumber);
        }
        return new ArrayList<>(availableSeats);
    }

    @Override
    public boolean checkInSeats(List<Integer> seatNumbers, String userId) {
        if(checkIfAvailable(seatNumbers)){
            for(Integer seatNumber: seatNumbers){
                availableSeats.remove(seatNumber);
                temporarilyUnavailableSeats.put(seatNumber, userId);
                temporarilyUnavailableSeatsLockTimestamps.put(seatNumber, System.currentTimeMillis());
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean checkOutSeats(List<Integer> seatNumbers, String userId) {
        if(checkIfTempLockByUser(seatNumbers, userId)){
            for(Integer seatNumber: seatNumbers){
                temporarilyUnavailableSeats.remove(seatNumber);
                permanentlyUnavailableSeats.put(seatNumber, userId);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean unlockSeats(List<Integer> seatNumbers) {
        if(checkIfPermLock(seatNumbers)){
            for(Integer seatNumber: seatNumbers){
                permanentlyUnavailableSeats.remove(seatNumber);
                availableSeats.add(seatNumber);
            }
            return true;
        }
        return false;
    }
}
