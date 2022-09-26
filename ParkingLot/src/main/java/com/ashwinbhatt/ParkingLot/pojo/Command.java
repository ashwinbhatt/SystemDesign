package com.ashwinbhatt.ParkingLot.pojo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Command {
    String command;
    List<String> commandArguments;

    public Command(String completeCommand){
        String[] splitCommand= completeCommand.split(" ");
        command= splitCommand[0];
        commandArguments= Arrays.asList(splitCommand);
        commandArguments= commandArguments.subList(1, commandArguments.size());
    }

    public String getCommand() {
        return command;
    }

    public List<String> getCommandArguments() {
        return commandArguments;
    }

}
