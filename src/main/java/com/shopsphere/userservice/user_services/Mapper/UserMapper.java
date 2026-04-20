package com.shopsphere.userservice.user_services.Mapper;

import com.shopsphere.userservice.user_services.Dto.request.CreateUserRequestDTO;
import com.shopsphere.userservice.user_services.Dto.request.UpdateUserRequestDTO;
import com.shopsphere.userservice.user_services.Dto.response.UserResponse;
import com.shopsphere.userservice.user_services.Entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel= "spring")
public interface UserMapper {

    User toEntity(CreateUserRequestDTO createUserRequestDTO) ;

    UserResponse toUserResponse(User user);

    // Update
    void updateUserFromDto(UpdateUserRequestDTO dto, @MappingTarget User user);

}
