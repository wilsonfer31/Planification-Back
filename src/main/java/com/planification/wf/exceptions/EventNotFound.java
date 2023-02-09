package com.planification.wf.exceptions;

public class EventNotFound extends RuntimeException{
    public EventNotFound(String message) {
        super(message);
    }
    public EventNotFound() {
        super("L'event recherchée n'existe pas");
    }

}
