package com.myblog8.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class ResourceNotFoundException extends RuntimeException{

    //This method is called through streamApi in the ServiceImpl class with a exception string message
    public ResourceNotFoundException(String msg)
    {

        super(msg);
    }


}
