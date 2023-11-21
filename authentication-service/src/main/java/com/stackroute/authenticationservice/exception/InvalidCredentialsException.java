package com.stackroute.authenticationservice.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)

public class InvalidCredentialsException extends Throwable {
    public InvalidCredentialsException(String message) {
        super(message);
    }
}
