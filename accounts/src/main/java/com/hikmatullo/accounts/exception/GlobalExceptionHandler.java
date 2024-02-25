package com.hikmatullo.accounts.exception;

import com.hikmatullo.accounts.payload.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CustomNotFoundException.class)
    public ApiErrorResponse notFound(CustomNotFoundException c) {
        return new ApiErrorResponse(c.getMessage(), HttpStatus.NOT_FOUND, LocalDateTime.now());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(InternalErrorException.class)
    public ApiErrorResponse internalError(InternalErrorException c) {
        return new ApiErrorResponse(c.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CustomBadRequestException.class)
    public ApiErrorResponse badRequest(CustomBadRequestException e) {
        return new ApiErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(CustomAlreadyException.class)
    public ApiErrorResponse invalidToken(CustomAlreadyException e) {
        return new ApiErrorResponse(e.getMessage(), HttpStatus.CONFLICT, LocalDateTime.now());
    }



}
