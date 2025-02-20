package main;

import menus.AdminMenu;
import menus.UserMenu;
import services.ReservationService;
import services.SpaceService;
import java.util.Scanner;

import static java.lang.System.*;

public class Main {
    public static void main(String[] args) {
        SpaceService spaceService = new SpaceService();
        ReservationService reservationService = new ReservationService();
        AdminMenu adminMenu = new AdminMenu(spaceService, reservationService);
        UserMenu userMenu = new UserMenu(spaceService, reservationService);
        Scanner scanner = new Scanner(in);

        while (true) {
            out.println("\nWelcome to Coworking Space Reservation System!");
            out.println("1. Admin Login");
            out.println("2. User Login");
            out.println("3. Exit");
            out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    adminMenu.showMenu();
                    break;
                case 2:
                    userMenu.showMenu();
                    break;
                case 3:
                    out.println("Thank you for using the system. Goodbye!");
                    return;
                default:
                    out.println("Invalid option. Please try again.");
            }
        }
    }
}