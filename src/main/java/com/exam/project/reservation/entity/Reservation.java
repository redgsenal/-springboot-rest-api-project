package com.exam.project.reservation.entity;

import com.exam.project.reservation.utility.AppUtils;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.extern.java.Log;
import org.hibernate.annotations.CreationTimestamp;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;

@Log
@Entity
@Table(name = "reservation")
@Data
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Long reservationId;

    @NotNull
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
    @Column(name = "notify_method", length = 10)
    private String notifyMethod;

    @NotNull
    @Column(name = "has_notify")
    private boolean hasNotified;

    @NotNull
    @CreationTimestamp
    @Column(name = "create_date", nullable = false)
    private LocalDateTime createDate;

    public Reservation(JSONObject jsonData) {
        if (jsonData == null) {
            return;
        }
        this.timeSlot = extractTimeSlot(jsonData);
        this.hasNotified = extractHasNotified(jsonData);
        this.guestCount = extractGuestCount(jsonData);
        this.timeSlot = extractTimeSlot(jsonData);
        this.notifyMethod = extractNotifyMethod(jsonData);
        this.createDate = extractCreateDate();
    }

    private LocalDateTime extractCreateDate() {
        DateTimeFormatter formatter = AppUtils.createDateFormatter();
        String currentTimeStamp = LocalDateTime.now().format(formatter);
        return LocalDateTime.parse(currentTimeStamp, formatter);
    }

    private String extractNotifyMethod(JSONObject jsonData) {
        return AppUtils.extractJSONStringValue(jsonData, "notifyMethod");
    }

    private boolean extractHasNotified(JSONObject jsonData) {
        return AppUtils.extractJSONBooleanValue(jsonData, "hasNotified");
    }

    private int extractGuestCount(JSONObject jsonData) {
        int count = AppUtils.extractJSONIntegerValue(jsonData, "guestCount");
        return (count < 1) ? 2 : count;
    }

    private LocalDateTime extractTimeSlot(JSONObject jsonData) {
        return AppUtils.extractJSONDateValue(jsonData, "timeSlot");
    }

    // send notification after a new reservation is created
    @PostPersist
    public void afterReservationCreated() {
        log.log(Level.INFO, ">>> new reservation created");
        sendNotification("Reservation created");
    }

    @PostRemove
    public void afterReservationDeleted() {
        log.log(Level.INFO, ">>> cancelled reservation");
        sendNotification("Reservation cancelled");
    }

    @PostUpdate
    public void afterReservationUpdated() {
        log.log(Level.INFO, ">>> update reservation");
        sendNotification("Reservation updated");
    }

    private void sendNotification(String message) {
        log.log(Level.INFO, ">>> send notification to user id: " + customer.getCustomerId());
        log.log(Level.INFO, ">>> method: " + this.getNotifyMethod());
        log.log(Level.INFO, ">>> message: " + message);
        String notifyMessage = "sms".equalsIgnoreCase(this.getNotifyMethod()) ? "SMS sent to: " + customer.getPhoneNumber() : "Email sent to: " + customer.getEmail();
        log.log(Level.INFO, ">>> " + notifyMessage);
    }
}
