package com.javatpoint.dataHandlers;


import pojos.Reservation;

import java.util.ArrayList;

import java.util.List;

import static com.javatpoint.dataHandlers.Config.*;

public class ReservationManagementController {
    public Reservation createOrUpdateSlot(Reservation bookingInfo) {
        List<Reservation> allReservations = new ArrayList<>(system_reservation);
        for (Reservation reservation : allReservations) {
            if (bookingInfo.getId() == reservation.getId()) {
                Config.system_reservation.remove(reservation);
                Config.system_reservation.add(bookingInfo);
            }
        }
        return null;
    }

    public Integer deleteBookingInfo(Reservation bookingInfo) {
        int status = 0;
        List<Reservation> allReservations = new ArrayList<>(system_reservation);
        for (Reservation reservation : allReservations) {
            if (bookingInfo.getId() == reservation.getId()) {
                Config.system_reservation.remove(reservation);
                status = 1;
            }
        }
        return status;
    }

    public List<Reservation> showBookingInfo() {
        List<Reservation> allReservations = new ArrayList<>(system_reservation);
        return new ArrayList<>(allReservations);
    }
}
