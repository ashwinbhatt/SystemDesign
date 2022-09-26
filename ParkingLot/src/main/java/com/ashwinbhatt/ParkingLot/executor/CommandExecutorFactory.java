package com.ashwinbhatt.ParkingLot.executor;

import com.ashwinbhatt.ParkingLot.exception.InvalidCommandException;
import com.ashwinbhatt.ParkingLot.pojo.Command;
import com.ashwinbhatt.ParkingLot.service.ParkingLotService;

import java.util.HashMap;
import java.util.Map;

public class CommandExecutorFactory {
    private Map<String, CommandExecutor> commands= new HashMap<>();

    public CommandExecutorFactory(final ParkingLotService parkingLotService){
        commands.put(CreateParkingLotCommandExecutor.COMMAND_NAME, new CreateParkingLotCommandExecutor(parkingLotService));
        commands.put(LeaveCommandExecutor.COMMAND_NAME, new LeaveCommandExecutor(parkingLotService));
        commands.put(ParkVehicleCommand.COMMAND_NAME, new ParkVehicleCommand(parkingLotService));
        commands.put(RegistrationNumberWithColorCommandExecutor.COMMAND_NAME, new RegistrationNumberWithColorCommandExecutor(parkingLotService));
        commands.put(SlotWithRegistrationNumberCommandExecutor.COMMAND_NAME, new SlotWithRegistrationNumberCommandExecutor(parkingLotService));
        commands.put(SlotWithVehicleOfColor.COMMAND_NAME, new SlotWithVehicleOfColor(parkingLotService));
        commands.put(StatusCommandExecutor.COMMAND_NAME, new StatusCommandExecutor(parkingLotService));
    }

    public CommandExecutor getCommandExecutor(final Command command){
        final CommandExecutor commandExecutor= commands.get(command.getCommand());
        if(commandExecutor == null){
            throw new InvalidCommandException("Command: "+command.getCommand()+" not found");
        }
        return commandExecutor;
    }
}
