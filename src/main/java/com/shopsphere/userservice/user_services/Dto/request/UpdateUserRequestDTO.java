package com.shopsphere.userservice.user_services.Dto.request;

import com.shopsphere.userservice.user_services.Dto.response.AddressDto;
import com.shopsphere.userservice.user_services.Entity.UserRole;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class UpdateUserRequestDTO {

    private String username;
    private String email;
    private String phone;
    private AddressDto address;
    private UserRole role;
}
