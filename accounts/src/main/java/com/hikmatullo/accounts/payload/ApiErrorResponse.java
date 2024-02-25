package com.hikmatullo.accounts.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class ApiErrorResponse {
    private String message;
    private HttpStatus status;
    private LocalDateTime timestamp;
}
