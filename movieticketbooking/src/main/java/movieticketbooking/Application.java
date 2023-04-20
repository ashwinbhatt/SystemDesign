package movieticketbooking;

import movieticketbooking.command.CommandFactory;
import movieticketbooking.command.CommandInterface;
import movieticketbooking.service.BookingManagementService;
import movieticketbooking.service.TheaterManagementService;

import java.util.Scanner;

public class Application {

	public static void main(String[] args) {
		System.out.println("Welcome to Movie ticket booking app.");
		System.out.println("Enter command \"q\" to quit application anytime");

		Scanner in= new Scanner(System.in);

		TheaterManagementService theaterManagementService= new TheaterManagementService();
		BookingManagementService bookingManagementService= new BookingManagementService();

		while (true){
			String commandStr= in.nextLine();
			if(commandStr.equals("q")) {
				break;
			}
			try {
				CommandInterface commandInterface= CommandFactory.getCommand(theaterManagementService,
						bookingManagementService,
						commandStr);
				System.out.println(commandInterface.executeCommand());
			} catch (Exception e) {
				System.out.println("ERROR while running command, "+ e.getMessage());
			}
		}
		in.close();
	}

}
