package com.shopsphere.userservice.user_services.Controller;

import com.shopsphere.userservice.user_services.Dto.request.CreateUserRequestDTO;
import com.shopsphere.userservice.user_services.Dto.request.UpdateUserRequestDTO;
import com.shopsphere.userservice.user_services.Dto.response.UserResponse;
import com.shopsphere.userservice.user_services.Mapper.UserMapper;
import com.shopsphere.userservice.user_services.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("v1/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers(){
        List<UserResponse> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable UUID id){
         UserResponse response=userService.getUserById(id);
         return ResponseEntity.ok(response);
    }

    /*
        public ResponseEntity<UserResponse> getUserByEmail(String email){
        UserResponse response=userService.getUserByEmail(email);
        return ResponseEntity.ok(response);
    }*/

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody CreateUserRequestDTO request){
        UserResponse response=userService.createUser(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@RequestBody UpdateUserRequestDTO request , @PathVariable UUID id){
        UserResponse response=userService.updateUser(id,request);
        return ResponseEntity.ok(response);
    }


}
