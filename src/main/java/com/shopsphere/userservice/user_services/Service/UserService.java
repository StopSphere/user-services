package com.shopsphere.userservice.user_services.Service;

import com.shopsphere.userservice.user_services.Dto.request.CreateUserRequestDTO;
import com.shopsphere.userservice.user_services.Dto.request.UpdateUserRequestDTO;
import com.shopsphere.userservice.user_services.Dto.response.UserResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

public interface UserService {
    // Learning : if i am using ResponseEntity there then Business logic mixed the object with http logiv
    public List<UserResponse> getAllUsers();

    public UserResponse getUserById(UUID id);

    public UserResponse getUserByEmail(String email);

    public UserResponse createUser(CreateUserRequestDTO dto);

    public UserResponse updateUser(UUID id, UpdateUserRequestDTO dto);
}
