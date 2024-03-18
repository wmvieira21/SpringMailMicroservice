package com.microservice.mail.email.controllers.exceptions;

import com.microservice.mail.email.exceptions.NoDataFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerEmail {

    @ExceptionHandler(NoDataFound.class)
    public ResponseEntity<String> notDataFoundException(NoDataFound ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
