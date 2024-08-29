package com.exam.project.reservation.controller.rest;

import com.exam.project.reservation.entity.Customer;
import com.exam.project.reservation.entity.Reservation;
import com.exam.project.reservation.exception.CustomerNotFound;
import com.exam.project.reservation.exception.InvalidReservationParameters;
import com.exam.project.reservation.repository.CustomerRepository;
import com.exam.project.reservation.repository.ReservationRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Reservation> createReservation(@PathVariable Long customerId, @RequestBody String data) {
        if (data == null || "".equalsIgnoreCase(data)) {
            throw new InvalidReservationParameters("Invalid Request Parameters");
        }
        JSONObject jsonData = new JSONObject(data);
        Optional<Customer> result = customerRepository.findById(customerId);
        if (result.isEmpty()) {
            throw new CustomerNotFound("Bad Customer ID");
        }
        Customer customer = result.get();
        Reservation reservation = new Reservation(jsonData);
        reservation.setCustomer(customer);
        Reservation createdReservation = reservationRepository.save(reservation);
        return new ResponseEntity<>(createdReservation, HttpStatus.CREATED);
    }

    @ExceptionHandler(CustomerNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ResponseEntity<String> customerNotFound(CustomerNotFound exception) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(InvalidReservationParameters.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    ResponseEntity<String> invalidReservationParameters(InvalidReservationParameters exception) {
        return ResponseEntity.noContent().build();
    }
}
