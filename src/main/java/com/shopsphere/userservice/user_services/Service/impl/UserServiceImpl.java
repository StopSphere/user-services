package com.shopsphere.userservice.user_services.Service.impl;

import com.shopsphere.userservice.user_services.Dto.request.CreateUserRequestDTO;
import com.shopsphere.userservice.user_services.Dto.request.LoginRequestDTO;
import com.shopsphere.userservice.user_services.Dto.request.UpdatePasswordRequestDTO;
import com.shopsphere.userservice.user_services.Dto.request.UpdateUserRequestDTO;
import com.shopsphere.userservice.user_services.Dto.response.UserResponseDTO;
import com.shopsphere.userservice.user_services.Entity.User;
import com.shopsphere.userservice.user_services.Exception.UserNotFoundException;
import com.shopsphere.userservice.user_services.Mapper.UserMapper;
import com.shopsphere.userservice.user_services.Repository.UserRepository;
import com.shopsphere.userservice.user_services.Security.Jwt.JwtUtil;
import com.shopsphere.userservice.user_services.Service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;


    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toUserResponse)
                .toList();
    }

    @Override
    public UserResponseDTO getUserById(UUID id) {

        return userRepository.findById(id)
                .map(userMapper::toUserResponse)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }

    @Override
    public UserResponseDTO getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(userMapper::toUserResponse)
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " ));
    }

    @Override
    public UserResponseDTO createUser(CreateUserRequestDTO dto) {
        User user = userMapper.toEntity(dto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);
        return userMapper.toUserResponse(savedUser);
    }

    @Override
    public UserResponseDTO updateUser(UUID id, UpdateUserRequestDTO dto) {
        var user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        userMapper.updateUserFromDto(dto, user);
        var updatedUser = userRepository.save(user);
        return userMapper.toUserResponse(updatedUser);
    }

    @Override
    public Void updatePassword(UUID id, UpdatePasswordRequestDTO request) {
        var user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid old password");
        }
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);

        return null;
    }

    @Override
    public String login(LoginRequestDTO dto) {
        User user=userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + dto.getEmail()));
        if(!passwordEncoder.matches(dto.getPassword(),user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return jwtUtil.generateToken(user.getEmail());
    }

    @Override
    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }

}
