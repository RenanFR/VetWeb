package com.vetweb.model.error;
// @author Maria Jéssica

public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }
    
}
