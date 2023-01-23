package com.planification.wf.exceptions;

public class EmailNotFoundException extends RuntimeException{


    public EmailNotFoundException() {
        super("L'adresse mail n'existe pas en base de données");
    }
    public EmailNotFoundException(String message) {
        super(message);
    }
}
