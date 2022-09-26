package com.ashwinbhatt.ParkingLot.executor;

import com.ashwinbhatt.ParkingLot.pojo.Command;
import com.ashwinbhatt.ParkingLot.service.ParkingLotService;

import java.util.List;

public class SlotWithVehicleOfColor extends CommandExecutor{
    public final static String COMMAND_NAME= "slot_with_vehicle_of_color";

    public SlotWithVehicleOfColor(ParkingLotService parkingLotService) {
        super(parkingLotService);
    }

    @Override
    public boolean validate(Command command) {
        if(!command.getCommand().equals(COMMAND_NAME)){
            return false;
        }
        if(command.getCommandArguments().size() != 1){
            return false;
        }
        return true;
    }

    @Override
    public void execute(Command command) {
        List<Integer> slots= parkingLotService.getSlotWithColor(command.getCommandArguments().get(0));
        for(Integer slot: slots){
            System.out.println(slot);
        }
    }
}
