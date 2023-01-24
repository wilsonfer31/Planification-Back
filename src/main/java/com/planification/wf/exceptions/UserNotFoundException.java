package com.planification.wf.exceptions;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException() {
        super("L'utilisateur n'existe pas");
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
