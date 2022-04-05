package com.example.spa.common.exception;

public class NotMyItemException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public NotMyItemException(){}

    public NotMyItemException(String message){
        super(message);
    }
}
