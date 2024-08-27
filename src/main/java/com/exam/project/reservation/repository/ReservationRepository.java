package com.exam.project.reservation.repository;

import com.exam.project.reservation.dao.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
