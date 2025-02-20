package menus;

import models.CoworkingSpace;
import models.Reservation;
import services.ReservationService;
import services.SpaceService;

import java.util.List;
import java.util.Scanner;

import static java.lang.System.*;

public class AdminMenu {
    private SpaceService spaceService;
    private ReservationService reservationService;
    private Scanner scanner;

    public AdminMenu(SpaceService spaceService, ReservationService reservationService) {
        this.spaceService = spaceService;
        this.reservationService = reservationService;
        this.scanner = new Scanner(in);
    }

    public void showMenu() {
        while (true) {
            out.println("\nAdmin Menu");
            out.println("1. Add a new coworking space");
            out.println("2. Remove a coworking space");
            out.println("3. View all reservations");
            out.println("4. Return to main menu");
            out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addSpace();
                    break;
                case 2:
                    removeSpace();
                    break;
                case 3:
                    viewAllReservations();
                    break;
                case 4:
                    return;
                default:
                    out.println("Invalid option. Please try again.");
            }
        }
    }

    private void addSpace() {
        out.print("Enter space ID: ");
        String id = scanner.nextLine();
        out.print("Enter space type (open space, private, room, etc.): ");
        String type = scanner.nextLine();
        out.print("Enter price per hour: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        CoworkingSpace space = new CoworkingSpace(id, type, price, true);
        spaceService.addSpace(space);
        out.println("Space " + id + " added successfully!");
    }

    private void removeSpace() {
        out.print("Enter space ID to remove: ");
        String id = scanner.nextLine();
        spaceService.removeSpace(id);
        out.println("Space " + id + " removed successfully!");
    }

    private void viewAllReservations() {
        List<Reservation> reservations = reservationService.getAllReservations();
        if (reservations.isEmpty()) {
            out.println("No reservations found.");
        } else {
            out.println("\nAll Reservations:");
            for (Reservation reservation : reservations) {
                out.println("Reservation ID: " + reservation.getId() + ", Space ID: " + reservation.getSpaceId() +
                        ", Customer: " + reservation.getCustomerName() + ", Date: " + reservation.getDate() +
                        ", Time: " + reservation.getStartTime() + " - " + reservation.getEndTime());
            }
        }
    }
}