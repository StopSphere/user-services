package com.shopsphere.userservice.user_services.Dto.response;
import com.shopsphere.userservice.user_services.Entity.UserRole;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
public class UserResponseDTO {
    //json return id,name,email
    private UUID id;
    private String username;
    private String email;
    private String phone;
    private AddressDto address;
    private UserRole role;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
}
