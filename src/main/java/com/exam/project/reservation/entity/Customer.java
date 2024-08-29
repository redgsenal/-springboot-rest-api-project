package com.exam.project.reservation.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "customer")
@Data
public class Customer {
    // TODO: try to custom generate this
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customerId;

    @NotNull
    @NotEmpty
    @Column(name = "first_name", length = 100)
    private String firstName;

    @NotNull
    @NotEmpty
    @Column(name = "last_name", length = 100)
    private String lastName;

    @NotNull
    @NotEmpty
    @Column(name = "email", length = 100)
    @Email
    private String email;

    @NotNull
    @NotEmpty
    @Column(name = "phone_number", length= 30)
    private String phoneNumber;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Reservation> reservationList;

    public Customer() {
        // required no-args constructor
    }
}
