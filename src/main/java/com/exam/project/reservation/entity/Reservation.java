package com.exam.project.reservation.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.time.LocalDateTime;

@Entity
@Table(name = "reservation")
@Data
public class Reservation {
    private static Log log = LogFactory.getLog(Reservation.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Long reservationId;

    @NotNull
    @Column(name = "customer_id")
    private Long customerId;

    @NotNull
    @Column(name = "timeslot")
    @Future
    private LocalDateTime timeSlot;

    @NotNull
    @Column(name = "guest_count")
    @Min(1)
    @Max(20)
    private int guestCount;

    @NotNull
    @Column(name = "notify_method")
    private String notifyMethod;

    @NotNull
    @Column(name = "has_notify")
    private boolean hasNotified;

    public Reservation() {
        // required no-args constructor
    }

    // send notification after a new reservation is created
    @PostPersist
    public void afterReservationCreated() {
        log.info(">>> new reservation created");
        log.info(">>> send notification to user id: " + customerId);
    }
}
