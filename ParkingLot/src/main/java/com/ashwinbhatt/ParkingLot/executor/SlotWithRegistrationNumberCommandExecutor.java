package com.ashwinbhatt.ParkingLot.executor;

import com.ashwinbhatt.ParkingLot.exception.RegistrationNumberNotFound;
import com.ashwinbhatt.ParkingLot.pojo.Command;
import com.ashwinbhatt.ParkingLot.service.ParkingLotService;

public class SlotWithRegistrationNumberCommandExecutor extends CommandExecutor{
    public final static String COMMAND_NAME= "slot_with_registration_number";

    public SlotWithRegistrationNumberCommandExecutor(ParkingLotService parkingLotService) {
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
        try{
            Integer slotNumber= parkingLotService.getSlotWithRegistrationNumber(command.getCommandArguments().get(0));
            System.out.println("Vehicle found in parking lot, slot number: "+ slotNumber);
        }catch (RegistrationNumberNotFound exception){
            System.out.println("Vehicle not found in parking lot");
        }
    }
}
