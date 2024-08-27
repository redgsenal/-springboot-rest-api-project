package com.exam.project.reservation.repository;

import com.exam.project.reservation.dao.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
