package com.microservice.mail.email.exceptions;

public class NoDataFound extends RuntimeException {

    public NoDataFound(String message) {
        super(message);
    }
}
