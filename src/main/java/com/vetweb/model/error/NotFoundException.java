package com.vetweb.model.error;

//@author renan.rodrigues@metasix.com.br

public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NotFoundException(String message) {
        super(message);
    }
    
}
