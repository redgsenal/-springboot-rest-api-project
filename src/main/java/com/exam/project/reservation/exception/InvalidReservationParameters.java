package com.exam.project.reservation.exception;

public class InvalidReservationParameters extends IllegalArgumentException {

    public InvalidReservationParameters(String errorMessage) {
        super(errorMessage);
    }
}
