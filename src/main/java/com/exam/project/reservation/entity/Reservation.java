package com.exam.project.reservation.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.annotations.CreationTimestamp;

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

    /*@NotNull
    @Column(name = "customer_id")
    private Long customerId;
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Customer customer;

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

    @NotNull
    @CreationTimestamp
    private LocalDateTime creationDate;

    public Reservation() {
        // required no-args constructor
    }

    // send notification after a new reservation is created
    @PostPersist
    public void afterReservationCreated() {
        log.info(">>> new reservation created");
        sendNotification("Reservation created");
    }

    @PostRemove
    public void afterReservationDeleted() {
        log.info(">>> cancelled reservation");
        sendNotification("Reservation cancelled");
    }

    @PostUpdate
    public void afterReservationUpdated() {
        log.info(">>> update reservation");
        sendNotification("Reservation updated");
    }

    private void sendNotification(String message){
        log.info(">>> send notification to user id: " + customer.getCustomerId());
        log.info(">>> method: " + this.getNotifyMethod());
        log.info(">>> message: " + message);
        String notifyMessage = "sms".equalsIgnoreCase(this.getNotifyMethod()) ? "SMS sent to: " + customer.getPhoneNumber() : "Email sent to: " + customer.getEmail();
        log.info(">>> " + notifyMessage);
    }
}
