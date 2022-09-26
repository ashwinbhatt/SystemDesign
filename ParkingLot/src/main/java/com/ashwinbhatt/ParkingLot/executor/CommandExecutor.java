package com.ashwinbhatt.ParkingLot.executor;

import com.ashwinbhatt.ParkingLot.pojo.Command;
import com.ashwinbhatt.ParkingLot.service.ParkingLotService;

public abstract class CommandExecutor {
    protected ParkingLotService parkingLotService;

    public CommandExecutor(final ParkingLotService parkingLotService){
        this.parkingLotService= parkingLotService;
    }

    public abstract boolean validate(Command command);

    public abstract void execute(Command command);
}
