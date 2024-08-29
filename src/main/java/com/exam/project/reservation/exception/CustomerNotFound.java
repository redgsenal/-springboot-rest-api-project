package com.exam.project.reservation.exception;

public class CustomerNotFound extends RuntimeException {

    public CustomerNotFound(String errorMessage) {
        super(errorMessage);
    }
}
