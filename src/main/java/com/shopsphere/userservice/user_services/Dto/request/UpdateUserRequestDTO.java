package com.shopsphere.userservice.user_services.Dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.annotation.processing.Generated;
import java.util.UUID;

@Getter
@Setter
public class UpdateUserRequestDTO {

    private UUID id;
    private String username;
    private String email;
}
