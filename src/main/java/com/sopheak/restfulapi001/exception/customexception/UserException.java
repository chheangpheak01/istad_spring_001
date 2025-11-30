package com.sopheak.restfulapi001.exception.customexception;

public class UserException extends RuntimeException{
    public UserException(String message){
        super(message);
    }
}
