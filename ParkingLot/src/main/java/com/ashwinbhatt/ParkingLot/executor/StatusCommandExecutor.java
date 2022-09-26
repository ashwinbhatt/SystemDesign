package com.ashwinbhatt.ParkingLot.executor;

import com.ashwinbhatt.ParkingLot.pojo.Command;
import com.ashwinbhatt.ParkingLot.service.ParkingLotService;

public class StatusCommandExecutor extends CommandExecutor{
    public final static String COMMAND_NAME= "status";

    public StatusCommandExecutor(ParkingLotService parkingLotService) {
        super(parkingLotService);
    }

    @Override
    public boolean validate(Command command) {
        if(!command.getCommand().equals(COMMAND_NAME)){
            return false;
        }
        if(command.getCommandArguments().size() != 0){
            return false;
        }
        return true;
    }

    @Override
    public void execute(Command command) {
        Integer[] status= parkingLotService.parkingLotStatus();
        System.out.println(String.format("Occupied slot: %s, total slot: %s", status[0], status[1]));
    }
}
