package com.shopsphere.userservice.user_services.Controller;

import com.shopsphere.userservice.user_services.Dto.request.CreateUserRequestDTO;
import com.shopsphere.userservice.user_services.Dto.request.LoginRequestDTO;
import com.shopsphere.userservice.user_services.Dto.request.UpdatePasswordRequestDTO;
import com.shopsphere.userservice.user_services.Dto.request.UpdateUserRequestDTO;
import com.shopsphere.userservice.user_services.Dto.response.UserResponseDTO;
import com.shopsphere.userservice.user_services.Service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/api/users")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class UserController {

    private final UserService userService;
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers(){
        List<UserResponseDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable UUID id){
         UserResponseDTO response=userService.getUserById(id);
         return ResponseEntity.ok(response);
    }

    /*
        public ResponseEntity<UserResponse> getUserByEmail(String email){
        UserResponse response=userService.getUserByEmail(email);
        return ResponseEntity.ok(response);
    }*/

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody  @Valid CreateUserRequestDTO request){
        UserResponseDTO response=userService.createUser(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@RequestBody UpdateUserRequestDTO request , @PathVariable UUID id){
        UserResponseDTO response=userService.updateUser(id,request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/password")
    public ResponseEntity<Void>updatePassword(@PathVariable UUID id, @RequestBody UpdatePasswordRequestDTO request){
        userService.updatePassword(id,request);
        return ResponseEntity.ok().build();
    }
    @Operation(security = {})
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDTO request){
        return ResponseEntity.ok(userService.login(request));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id ){
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
