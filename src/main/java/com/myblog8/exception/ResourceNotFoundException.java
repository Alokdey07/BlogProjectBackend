package com.myblog8.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String msg) {

        super(msg);
    }


}
