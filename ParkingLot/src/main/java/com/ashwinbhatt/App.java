package com.ashwinbhatt;

import com.ashwinbhatt.ParkingLot.executor.CommandExecutor;
import com.ashwinbhatt.ParkingLot.executor.CommandExecutorFactory;
import com.ashwinbhatt.ParkingLot.pojo.Command;
import com.ashwinbhatt.ParkingLot.service.ParkingLotService;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Parking Lot Application !!" );
        Scanner input= new Scanner(System.in);
        ParkingLotService parkingLotService= new ParkingLotService();
        CommandExecutorFactory commandExecutorFactory= new CommandExecutorFactory(parkingLotService);
        while(true){
            String rawCommand= input.nextLine();
            Command command= new Command(rawCommand);
            CommandExecutor commandExecutor= commandExecutorFactory.getCommandExecutor(command);
            if(!commandExecutor.validate(command)){
                System.out.println("Invalid Command !!");
                continue;
            }
            commandExecutor.execute(command);
        }
    }
}
