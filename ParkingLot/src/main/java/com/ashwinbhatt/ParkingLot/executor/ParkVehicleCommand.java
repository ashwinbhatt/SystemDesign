package com.ashwinbhatt.ParkingLot.executor;

import com.ashwinbhatt.ParkingLot.exception.SlotNotAvailableException;
import com.ashwinbhatt.ParkingLot.pojo.Command;
import com.ashwinbhatt.ParkingLot.pojo.Vehicle;
import com.ashwinbhatt.ParkingLot.service.ParkingLotService;

public class ParkVehicleCommand extends CommandExecutor{
    public final static String COMMAND_NAME= "park";

    public ParkVehicleCommand(ParkingLotService parkingLotService) {
        super(parkingLotService);
    }

    @Override
    public boolean validate(Command command) {
        if(!command.getCommand().equals(COMMAND_NAME)){
            return false;
        }
        if(command.getCommandArguments().size() != 2){
            return false;
        }
        return true;
    }

    @Override
    public void execute(Command command) {
        Vehicle vehicle= new Vehicle(command.getCommandArguments().get(0), command.getCommandArguments().get(1));
        try{
            final Integer slot= parkingLotService.parkVehicle(vehicle);
            System.out.println("Slot Number: "+ slot);
        }catch (SlotNotAvailableException exception){
            System.out.println("Parking Lot if full!");
        }
    }
}
