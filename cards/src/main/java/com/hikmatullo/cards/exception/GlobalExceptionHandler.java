package com.hikmatullo.cards.exception;

import com.hikmatullo.cards.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CustomNotFoundException.class)
    public ErrorResponseDto notFound(CustomNotFoundException c) {
        return new ErrorResponseDto(c.getMessage(), HttpStatus.NOT_FOUND, LocalDateTime.now());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(InternalErrorException.class)
    public ErrorResponseDto internalError(InternalErrorException c) {
        return new ErrorResponseDto(c.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CustomBadRequestException.class)
    public ErrorResponseDto badRequest(CustomBadRequestException e) {
        return new ErrorResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(CustomAlreadyException.class)
    public ErrorResponseDto invalidToken(CustomAlreadyException e) {
        return new ErrorResponseDto(e.getMessage(), HttpStatus.CONFLICT, LocalDateTime.now());
    }



}
