package com.planification.wf.exceptions;

public class EmailAlreadyExistsException extends RuntimeException{

    public EmailAlreadyExistsException() {
        super("L'email existe deja en base de données");
    }

    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}
