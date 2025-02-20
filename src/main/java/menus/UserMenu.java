package menus;

import models.CoworkingSpace;
import models.Reservation;
import services.ReservationService;
import services.SpaceService;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.*;

public class UserMenu {
    private SpaceService spaceService;
    private ReservationService reservationService;
    private Scanner scanner;

    public UserMenu(SpaceService spaceService, ReservationService reservationService) {
        this.spaceService = spaceService;
        this.reservationService = reservationService;
        this.scanner = new Scanner(in);
    }

    public void showMenu() {
        while (true) {
            out.println("\nUser Menu");
            out.println("1. Browse available spaces");
            out.println("2. Make a reservation");
            out.println("3. View my reservations");
            out.println("4. Cancel reservation");
            out.println("5. Return to main menu");
            out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    browseSpaces();
                    break;
                case 2:
                    makeReservation();
                    break;
                case 3:
                    viewMyReservations();
                    break;
                case 4:
                    cancelReservation();
                    break;
                case 5:
                    return;
                default:
                    out.println("Invalid option. Please try again.");
            }
        }
    }

    private void browseSpaces() {
        List<CoworkingSpace> availableSpaces = spaceService.getAvailableSpaces();
        if (availableSpaces.isEmpty()) {
            out.println("No available spaces found.");
        } else {
            out.println("\nAvailable Coworking Spaces:");
            for (CoworkingSpace space : availableSpaces) {
                out.println("ID: " + space.getId() + ", Type: " + space.getType() + ", Price: $" + space.getPrice() + "/hour");
            }
        }
    }

    private void makeReservation() {
        browseSpaces();
        out.print("Enter the space ID you want to book: ");
        String spaceId = scanner.nextLine();
        out.print("Enter your name: ");
        String customerName = scanner.nextLine();
        out.print("Enter the date (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        out.print("Enter the start time (HH:MM): ");
        String startTime = scanner.nextLine();
        out.print("Enter the end time (HH:MM): ");
        String endTime = scanner.nextLine();

        CoworkingSpace space = spaceService.getSpaceById(spaceId);
        if (space != null && space.isAvailability()) {
            Reservation reservation = new Reservation(reservationService.getNextReservationId(), spaceId, customerName, date, startTime, endTime);
            reservationService.addReservation(reservation);
            spaceService.updateSpaceAvailability(spaceId, false);
            out.println("Reservation successful! Your reservation ID is " + reservation.getId() + ".");
        } else {
            out.println("Space not available or invalid ID.");
        }
    }

    private void viewMyReservations() {
        out.print("Enter your name: ");
        String customerName = scanner.nextLine();
        List<Reservation> userReservations = reservationService.getReservationsByCustomer(customerName);

        if (userReservations.isEmpty()) {
            out.println("No reservations found for you.");
        } else {
            out.println("\nYour Reservations:");
            for (Reservation reservation : userReservations) {
                out.println("Reservation ID: " + reservation.getId() + ", Space ID: " + reservation.getSpaceId() +
                        ", Date: " + reservation.getDate() + ", Time: " + reservation.getStartTime() + " - " + reservation.getEndTime());
            }
        }
    }

    private void cancelReservation() {
        out.print("Enter the reservation ID to cancel: ");
        int reservationId = scanner.nextInt();
        scanner.nextLine();

        reservationService.cancelReservation(reservationId);
        out.println("Reservation " + reservationId + " canceled successfully.");
    }
}