package com.brewery.application.exception;

public class ElementNotFoundException extends RuntimeException{
    public ElementNotFoundException(String message){
        super(message);
    }
}