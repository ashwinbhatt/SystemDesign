package com.ashwinbhatt.ParkingLot.executor;

import com.ashwinbhatt.ParkingLot.exception.InvalidSlotNumberException;
import com.ashwinbhatt.ParkingLot.pojo.Command;
import com.ashwinbhatt.ParkingLot.pojo.Vehicle;
import com.ashwinbhatt.ParkingLot.service.ParkingLotService;
import com.ashwinbhatt.ParkingLot.utils.IntegerValidator;

public class LeaveCommandExecutor extends CommandExecutor{
    public final static String COMMAND_NAME= "leave";

    public LeaveCommandExecutor(ParkingLotService parkingLotService) {
        super(parkingLotService);
    }

    @Override
    public boolean validate(Command command) {
        if(!command.getCommand().equals(COMMAND_NAME)){
            return false;
        }
        if(command.getCommandArguments().size() != 1 &&
                !IntegerValidator.isInteger(command.getCommandArguments().get(0))){
            return false;
        }
        return true;
    }

    @Override
    public void execute(Command command) {
        Integer slotNo= Integer.parseInt(command.getCommandArguments().get(0));
        try{
            Vehicle vehicle= parkingLotService.unParkVehicle(slotNo);
            System.out.println("Vehile unparked, registration number: "+ vehicle.getRegistrationNumber());
        }catch (InvalidSlotNumberException exception){
            System.out.println("Invalid slot number "+slotNo);
        }
    }
}
