package com.exam.project.reservation.repository;

import com.exam.project.reservation.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    @Query(value = "select u.notify_method from reservation u where u.reservation_id = ?1", nativeQuery = true)
    String findNotifyMethodById(Long id);

    // list of reservations between dates
    @Query(value = "select u.reservation_id from reservation u where u.has_notify = 0 and u.timeslot between ?1 and ?2", nativeQuery = true)
    List<Long> findReservationIDsForNotification(String startDateTime, String endDateTime);
}
