package com.ashwinbhatt.ParkingLot.executor;

import com.ashwinbhatt.ParkingLot.pojo.Command;
import com.ashwinbhatt.ParkingLot.pojo.ParkingLot;
import com.ashwinbhatt.ParkingLot.service.ParkingLotService;
import com.ashwinbhatt.ParkingLot.strategy.NaturalOrdering;
import com.ashwinbhatt.ParkingLot.utils.IntegerValidator;

import java.util.List;

public class CreateParkingLotCommandExecutor extends CommandExecutor {

    public final static String COMMAND_NAME= "create_parking_lot";

    public CreateParkingLotCommandExecutor(final ParkingLotService parkingLotService){
        super(parkingLotService);
    }

    @Override
    public boolean validate(Command command) {
        if(!command.getCommand().equals(COMMAND_NAME)){
            return false;
        }
        final List<String> params= command.getCommandArguments();
        if(params.size() != 1){
            return false;
        }
        return IntegerValidator.isInteger(params.get(0));
    }

    @Override
    public void execute(Command command) {
        final int parkingLotCapacity= Integer.parseInt(command.getCommandArguments().get(0));
        final ParkingLot parkingLot= new ParkingLot(parkingLotCapacity);
        parkingLotService.createParkingLot(parkingLot, new NaturalOrdering(parkingLot.getMAX_CAPACITY()));
        System.out.println("Created a parking log with "+ parkingLot.getMAX_CAPACITY()+ " slots");
    }

}
