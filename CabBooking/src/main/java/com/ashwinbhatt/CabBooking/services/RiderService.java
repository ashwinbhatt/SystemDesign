package com.ashwinbhatt.CabBooking.services;

import com.ashwinbhatt.CabBooking.exceptions.RiderException;
import com.ashwinbhatt.CabBooking.models.Rider;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RiderService {
    private final List<Rider> ridersRepository;

    public String addRider(@NotNull String name){
        Rider rider= new Rider(name);
        ridersRepository.add(rider);
        return rider.getRiderId();
    }

    public Rider findRider(@NotNull String riderId) throws RiderException {
        Optional<Rider> riderOptional= ridersRepository.stream()
                .filter(rider -> rider.getRiderId().equals(riderId))
                .findAny();
        if(!riderOptional.isPresent()){
            throw new RiderException("Rider with Rider ID: "+riderId+" not found");
        }
        return riderOptional.get();
    }
}
