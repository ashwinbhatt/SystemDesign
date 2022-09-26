package com.ashwinbhatt.ParkingLot.executor;

import com.ashwinbhatt.ParkingLot.pojo.Command;
import com.ashwinbhatt.ParkingLot.service.ParkingLotService;

import java.util.List;

public class RegistrationNumberWithColorCommandExecutor extends CommandExecutor{
    public final static String COMMAND_NAME= "registration_number_with_color";

    public RegistrationNumberWithColorCommandExecutor(ParkingLotService parkingLotService) {
        super(parkingLotService);
    }

    @Override
    public boolean validate(Command command) {
        if(!command.getCommand().equals(COMMAND_NAME)){
            return false;
        }
        if(command.getCommandArguments().size()!=1){
            return false;
        }
        return true;
    }

    @Override
    public void execute(Command command) {
        List<String> registrationNumbers= parkingLotService.getRegistrationNumberWithColor(command.getCommandArguments().get(0));
        for(String registrationNumber: registrationNumbers){
            System.out.println(registrationNumber);
        }
    }
}
