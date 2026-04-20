package com.shopsphere.userservice.user_services.Service.impl;

import com.shopsphere.userservice.user_services.Dto.request.CreateUserRequestDTO;
import com.shopsphere.userservice.user_services.Dto.request.UpdateUserRequestDTO;
import com.shopsphere.userservice.user_services.Dto.response.UserResponse;
import com.shopsphere.userservice.user_services.Exception.UserNotFoundException;
import com.shopsphere.userservice.user_services.Mapper.UserMapper;
import com.shopsphere.userservice.user_services.Repository.UserRepository;
import com.shopsphere.userservice.user_services.Service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final UserMapper userMapper;


    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toUserResponse)
                .toList();
    }

    @Override
    public UserResponse getUserById(UUID id) {

        return userRepository.findById(id)
                .map(userMapper::toUserResponse)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }

    @Override
    public UserResponse getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(userMapper::toUserResponse)
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " ));
    }

    @Override
    public UserResponse createUser(CreateUserRequestDTO dto) {
        var user = userMapper.toEntity(dto);
        var savedUser = userRepository.save(user);
        return userMapper.toUserResponse(savedUser);
    }

    @Override
    public UserResponse updateUser(UUID id, UpdateUserRequestDTO dto) {
        var user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        userMapper.updateUserFromDto(dto, user);
        var updatedUser = userRepository.save(user);
        return userMapper.toUserResponse(updatedUser);
    }
}
