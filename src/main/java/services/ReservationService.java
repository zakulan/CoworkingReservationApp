package services;

import models.Reservation;
import java.util.ArrayList;
import java.util.List;

public class ReservationService {
    private List<Reservation> reservations = new ArrayList<>();
    private int reservationIdCounter = 1;

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    public void cancelReservation(int id) {
        reservations.removeIf(reservation -> reservation.getId() == id);
    }

    public List<Reservation> getReservationsByCustomer(String customerName) {
        List<Reservation> userReservations = new ArrayList<>();
        for (Reservation reservation : reservations) {
            if (reservation.getCustomerName().equals(customerName)) {
                userReservations.add(reservation);
            }
        }
        return userReservations;
    }

    public List<Reservation> getAllReservations() {
        return reservations;
    }

    public int getNextReservationId() {
        return reservationIdCounter++;
    }
}