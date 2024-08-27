package com.exam.project.reservation.repository;

import com.exam.project.reservation.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query(value = "select u.email from customer u where u.customer_id = ?1", nativeQuery = true)
    String findEmailAddressById(Long id);

    @Query(value = "select u.phone_number from customer u where u.customer_id = ?1", nativeQuery = true)
    String findPhoneNumberById(Long id);
}
