package com.exam.project.reservation.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;

import java.util.Date;

@Entity
@Table(name = "reservation")
@Data
public class Reservation {
    // TODO: try to custom generate this
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Long reservationId;

    @NonNull
    @Column(name = "timeslot")
    private Date timeSlot;

    @Column(name = "guest_count")
    private int guestCount;

    @NonNull
    @Column(name = "notify_method")
    private String notifyMethod;

    @Column(name = "has_notify")
    private boolean hasNotified;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Reservation() {
        // required no-args constructor
    }
}
