package com.compassuol.springbootblog.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BlogAPIException extends RuntimeException {

    private HttpStatus status;
    private String message;

    public BlogAPIException(HttpStatus status,String message) {
        this.message =message;
        this.status=status;
    }

    public BlogAPIException(HttpStatus status, String message,String message1) {
        super(message);
        this.status = status;
        this.message = message1;

    }
}
