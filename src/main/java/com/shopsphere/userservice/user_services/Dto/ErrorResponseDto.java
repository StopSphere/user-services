package com.shopsphere.userservice.user_services.Dto;

import lombok.Builder;
import lombok.Getter;


import java.time.LocalDateTime;

@Getter
@Builder
public class ErrorResponseDto {

    private LocalDateTime timestamp;
    private int status;
    private String message;
    private String error;
    private String path;
}
