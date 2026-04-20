package com.shopsphere.userservice.user_services.Dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserRequestDTO {
    private String username;
    private String password;
    private String email;

}
