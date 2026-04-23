package com.shopsphere.userservice.user_services.Dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdatePasswordRequestDTO {
    private String oldPassword;
    private String newPassword;
}
