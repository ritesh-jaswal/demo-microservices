package com.project.user.service.UserService.payload;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse
{
    private String message;
    private boolean success;
}
