package com.javatpoint.service;

import com.javatpoint.dataHandlers.ReservationManagementController;
import com.javatpoint.dataHandlers.UserManagementController;
import pojos.Reservation;
import pojos.SearchInfo;
import pojos.User;
import pojos.ViewInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class ReservationManagementService {

    public Reservation bookingSlot(Reservation bookingInfo) {
        ReservationManagementController controller = new ReservationManagementController();
        return controller.createOrUpdateSlot(bookingInfo);
    }

    public Reservation updateSlot(Reservation bookingInfo) {
        ReservationManagementController controller = new ReservationManagementController();
        return controller.createOrUpdateSlot(bookingInfo);
    }

    public Integer deleteReservation(Reservation bookingInfo) {
        ReservationManagementController controller = new ReservationManagementController();
        return controller.deleteBookingInfo(bookingInfo);
    }

    public List<Reservation> showBookingInfo() {
        ReservationManagementController controller = new ReservationManagementController();
        return controller.showBookingInfo();
    }

    public List<Reservation> showBookingInfo(SearchInfo searchInfo) {
        ReservationManagementController controller = new ReservationManagementController();
        List<Reservation> reservations = controller.showBookingInfo();
        List<Reservation> myList = new ArrayList<>();

        reservations.removeIf(o -> {
            AtomicReference<Boolean> statusCu = new AtomicReference<>(false);
            AtomicReference<Boolean> statusBa = new AtomicReference<>(false);

            if (!searchInfo.getCustomername().isEmpty()) {
                UserManagementController userManagementController = new UserManagementController();
                ViewInfo viewInfo = new ViewInfo();
                viewInfo.setShowType(2);

                List<User> users = userManagementController.showInfo(viewInfo);
                users.forEach(user -> {
                    if (searchInfo.getCustomername().equalsIgnoreCase(user.getUsername()) && user.getId() == o.getCustomerId()) {
                        statusCu.set(true);
                    }
                });

                return !statusCu.get();
            } else if (!searchInfo.getBarbername().isEmpty()) {
                UserManagementController userManagementController = new UserManagementController();
                ViewInfo viewInfo = new ViewInfo();
                viewInfo.setShowType(1);

                List<User> users = userManagementController.showInfo(viewInfo);
                users.forEach(user -> {
                    if (searchInfo.getCustomername().equalsIgnoreCase(user.getUsername()) && user.getId() == o.getCustomerId()) {
                        statusBa.set(true);
                    }
                });

                return !statusBa.get();

            } else if (searchInfo.getStartdate() != null) {

                if (!o.getDate().after(searchInfo.getStartdate())) {
                    return true;
                }

            } else if (searchInfo.getEnddate() != null) {

                if (!o.getDate().before(searchInfo.getEnddate())) {
                    return true;
                }

            }
            return false;

        });

        return myList;
    }
}
