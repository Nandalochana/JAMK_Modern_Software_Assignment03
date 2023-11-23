package com.javatpoint.controllers;

import com.javatpoint.service.ReservationManagementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pojos.Reservation;
import pojos.SearchInfo;

import java.util.List;

@RestController
public class ReservationManagement {

    @RequestMapping(value = "/reservation/createreservation")
    public ResponseEntity<Reservation> createReservation(Reservation bookingInfo) {
        ReservationManagementService service = new ReservationManagementService();
        return ResponseEntity.ok(service.bookingSlot(bookingInfo));
    }

    @RequestMapping(value = "/reservation/updatereservation")
    public ResponseEntity<Reservation> updateReservation(Reservation bookingInfo) {
        ReservationManagementService service = new ReservationManagementService();
        return ResponseEntity.ok(service.updateSlot(bookingInfo));
    }

    @RequestMapping(value = "/reservation/deletereservation")
    public ResponseEntity<Integer> deleteReservation(Reservation bookingInfo) {
        ReservationManagementService service = new ReservationManagementService();
        return ResponseEntity.ok(service.deleteReservation(bookingInfo));
    }

    @RequestMapping(value = "/reservation/showreservation")
    public ResponseEntity<List<Reservation>> showReservation() {
        ReservationManagementService service = new ReservationManagementService();
        return ResponseEntity.ok(service.showBookingInfo());
    }

    @RequestMapping(value = "/reservation/showreservationbycriteria")
    public ResponseEntity<List<Reservation>> showreservationbycriteria(SearchInfo searchInfo) {
        ReservationManagementService service = new ReservationManagementService();
        return ResponseEntity.ok(service.showBookingInfo(searchInfo));
    }


}
