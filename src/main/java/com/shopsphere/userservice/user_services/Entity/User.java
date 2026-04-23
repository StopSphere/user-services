package com.shopsphere.userservice.user_services.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "users")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "BINARY(16)", nullable = false, updatable = false)
    private UUID id;


    @Column(name="user_name",nullable = false,updatable = true)
    private String username;

    @Column(name="password", nullable = false)
    private String password;

    @Column(name="email", unique = true,nullable = false,updatable = true)
    private String email;

    private String phone;

    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name="address_id",referencedColumnName = "id")
    private Address address;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private UserRole role=UserRole.CUSTOMER;

    @CreationTimestamp
    private LocalDateTime createdDate;

    @UpdateTimestamp
    private LocalDateTime modifiedDate;

}
