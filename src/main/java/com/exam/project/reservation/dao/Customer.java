package com.exam.project.reservation.dao;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="customer")
@Data
public class Customer {
    // TODO: try to custom generate this
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="customer_id")
    private Long customerId;

    @NonNull
    @Column(name="first_name")
    private String firstName;

    @NonNull
    @Column(name="last_name")
    private String lastName;

    @NonNull
    @Column(name="email")
    private String email;

    @NonNull
    @Column(name="phone_number")
    private String phoneNumber;

    @OneToMany(mappedBy = "customer")
    private Set<Reservation> reservation = new HashSet<>();
}
