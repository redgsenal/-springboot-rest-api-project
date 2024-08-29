package com.exam.project.reservation.controller.rest;

import com.exam.project.reservation.entity.Customer;
import com.exam.project.reservation.entity.Reservation;
import com.exam.project.reservation.repository.CustomerRepository;
import com.exam.project.reservation.repository.ReservationRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class ReservationAPIController {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ReservationRepository reservationRepository;

    @PostMapping("/customers/{customerId}/reservations")
    public Reservation createReservation(@PathVariable Long customerId, @RequestBody String data) {
        if (data == null || "".equalsIgnoreCase(data)) {
            throw new RuntimeException("Invalid data");
        }
        JSONObject jsonData = new JSONObject(data);
        Optional<Customer> result = customerRepository.findById(customerId);
        if (result.isEmpty()) {
            throw new RuntimeException("Invalid employee id");
        }
        Customer customer = result.get();
        Reservation reservation = new Reservation(jsonData);
        reservation.setCustomer(customer);
        reservationRepository.save(reservation);
        return reservation;
    }
}
