import java.util.*;

class CoworkingSpace {
    String id;
    String type;
    double price;
    boolean availability;

    CoworkingSpace(String id, String type, double price, boolean availability) {
        this.id = id;
        this.type = type;
        this.price = price;
        this.availability = availability;
    }
}

class Reservation {
    int id;
    String spaceId;
    String customerName;
    String date;
    String startTime;
    String endTime;

    Reservation(int id, String spaceId, String customerName, String date, String startTime, String endTime) {
        this.id = id;
        this.spaceId = spaceId;
        this.customerName = customerName;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}

public class CoworkingReservationApp {
    static List<CoworkingSpace> spaces = new ArrayList<>();
    static List<Reservation> reservations = new ArrayList<>();
    static int reservationIdCounter = 1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nWelcome to Coworking Space Reservation System!");
            System.out.println("1. Admin Login");
            System.out.println("2. User Login");
            System.out.println("3. Exit");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> adminMenu(scanner);
                case 2 -> userMenu(scanner);
                case 3 -> {
                    System.out.println("Thank you for using the system. Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    static void adminMenu(Scanner scanner) {
        while (true) {
            System.out.println("\nAdmin Menu");
            System.out.println("1. Add a new coworking space");
            System.out.println("2. Remove a coworking space");
            System.out.println("3. View all reservations");
            System.out.println("4. Return to main menu");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addSpace(scanner);
                    break;
                case 2:
                    removeSpace(scanner);
                    break;
                case 3:
                    viewAllReservations();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    static void addSpace(Scanner scanner) {
        System.out.print("Enter space ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter space type (open space, private, room, etc.): ");
        String type = scanner.nextLine();
        System.out.print("Enter price per hour: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        CoworkingSpace space = new CoworkingSpace(id, type, price, true);
        spaces.add(space);
        System.out.println("Space " + id + " added successfully!");
    }

    static void removeSpace(Scanner scanner) {
        System.out.print("Enter space ID to remove: ");
        String id = scanner.nextLine();

        for (CoworkingSpace space : spaces) {
            if (space.id.equals(id)) {
                spaces.remove(space);
                System.out.println("Space " + id + " removed successfully!");
                return;
            }
        }
        System.out.println("Space " + id + " not found.");
    }

    static void viewAllReservations() {
        if (reservations.isEmpty()) {
            System.out.println("No reservations found.");
        } else {
            System.out.println("\nAll Reservations:");
            for (Reservation reservation : reservations) {
                System.out.println("Reservation ID: " + reservation.id + ", Space ID: " + reservation.spaceId +
                        ", Customer: " + reservation.customerName + ", Date: " + reservation.date +
                        ", Time: " + reservation.startTime + " - " + reservation.endTime);
            }
        }
    }

    static void userMenu(Scanner scanner) {
        while (true) {
            System.out.println("\nUser Menu");
            System.out.println("1. Browse available spaces");
            System.out.println("2. Make a reservation");
            System.out.println("3. View my reservations");
            System.out.println("4. Cancel reservation");
            System.out.println("5. Return to main menu");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    browseSpaces();
                    break;
                case 2:
                    makeReservation(scanner);
                    break;
                case 3:
                    viewMyReservations(scanner);
                    break;
                case 4:
                    cancelReservation(scanner);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    static void browseSpaces() {
        System.out.println("\nAvailable Coworking Spaces:");
        for (CoworkingSpace space : spaces) {
            if (space.availability) {
                System.out.println("ID: " + space.id + ", Type: " + space.type + ", Price: $" + space.price + "/hour");
            }
        }
    }

    static void makeReservation(Scanner scanner) {
        browseSpaces();
        System.out.print("Enter the space ID you want to book: ");
        String spaceId = scanner.nextLine();
        System.out.print("Enter your name: ");
        String customerName = scanner.nextLine();
        System.out.print("Enter the date (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        System.out.print("Enter the start time (HH:MM): ");
        String startTime = scanner.nextLine();
        System.out.print("Enter the end time (HH:MM): ");
        String endTime = scanner.nextLine();

        for (CoworkingSpace space : spaces) {
            if (space.id.equals(spaceId) && space.availability) {
                Reservation reservation = new Reservation(reservationIdCounter++, spaceId, customerName, date, startTime, endTime);
                reservations.add(reservation);
                space.availability = false;
                System.out.println("Reservation successful! Your reservation ID is " + reservation.id + ".");
                return;
            }
        }
        System.out.println("Space not available or invalid ID.");
    }

    static void viewMyReservations(Scanner scanner) {
        System.out.print("Enter your name: ");
        String customerName = scanner.nextLine();

        List<Reservation> userReservations = new ArrayList<>();
        for (Reservation reservation : reservations) {
            if (reservation.customerName.equals(customerName)) {
                userReservations.add(reservation);
            }
        }

        if (userReservations.isEmpty()) {
            System.out.println("No reservations found for you.");
        } else {
            System.out.println("\nYour Reservations:");
            for (Reservation reservation : userReservations) {
                System.out.println("Reservation ID: " + reservation.id + ", Space ID: " + reservation.spaceId +
                        ", Date: " + reservation.date + ", Time: " + reservation.startTime + " - " + reservation.endTime);
            }
        }
    }

    static void cancelReservation(Scanner scanner) {
        System.out.print("Enter the reservation ID to cancel: ");
        int reservationId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        for (Reservation reservation : reservations) {
            if (reservation.id == reservationId) {
                for (CoworkingSpace space : spaces) {
                    if (space.id.equals(reservation.spaceId)) {
                        space.availability = true;
                        break;
                    }
                }
                reservations.remove(reservation);
                System.out.println("Reservation " + reservationId + " canceled successfully.");
                return;
            }
        }
        System.out.println("Reservation " + reservationId + " not found.");
    }
}