package com.shopsphere.userservice.user_services.Dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class AddressDto {

    private UUID id;
    private String street;
    private String city;
    private String state;
    private String zipCode;
    private String country;
}
