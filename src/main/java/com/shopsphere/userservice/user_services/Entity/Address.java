package com.shopsphere.userservice.user_services.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name="address_table")
public class Address {

    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    private UUID id;

        private String street;
        private String city;
        private String state;
        private String zipCode;
        private String country;
}