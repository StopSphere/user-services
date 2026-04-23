package com.shopsphere.userservice.user_services.Dto.request;

import com.shopsphere.userservice.user_services.Dto.response.AddressDto;
import com.shopsphere.userservice.user_services.Entity.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserRequestDTO {

    @NotBlank
    private String username;

    private String password;

    @Email
    @NotBlank // double check because i checked this in service layer as well
    private String email;
    private String phone;
    private AddressDto address;
    private UserRole role=UserRole.CUSTOMER; // default role is customer

}
