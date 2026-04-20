package com.shopsphere.userservice.user_services.Dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;
@Getter
@Setter
@Builder

public class UserResponse {
    //json return id,name,email
    private UUID id;
    private String username;
    private String email;
}
