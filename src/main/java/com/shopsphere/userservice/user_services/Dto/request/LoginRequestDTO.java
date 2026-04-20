package com.shopsphere.userservice.user_services.Dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDTO {
    // in login we required only username and password
    private String username;
    private String password;

}
