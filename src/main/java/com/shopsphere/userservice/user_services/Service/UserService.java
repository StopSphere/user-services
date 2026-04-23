package com.shopsphere.userservice.user_services.Service;

import com.shopsphere.userservice.user_services.Dto.request.CreateUserRequestDTO;
import com.shopsphere.userservice.user_services.Dto.request.LoginRequestDTO;
import com.shopsphere.userservice.user_services.Dto.request.UpdatePasswordRequestDTO;
import com.shopsphere.userservice.user_services.Dto.request.UpdateUserRequestDTO;
import com.shopsphere.userservice.user_services.Dto.response.UserResponseDTO;

import java.util.List;
import java.util.UUID;

public interface UserService {
    // Learning : if i am using ResponseEntity there then Business logic mixed the object with http logiv
    public List<UserResponseDTO> getAllUsers();

    public UserResponseDTO getUserById(UUID id);

    public UserResponseDTO getUserByEmail(String email);

    public UserResponseDTO createUser(CreateUserRequestDTO dto);

    public UserResponseDTO updateUser(UUID id, UpdateUserRequestDTO dto);

    public Void updatePassword(UUID id, UpdatePasswordRequestDTO requestDto);

    public String login(LoginRequestDTO dto);

    public void deleteUser(UUID id);

}

