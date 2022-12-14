package com.aov2099.rest.webservices.restfulwebservices.user;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.*;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String message){
        super(message);
    }

}
