package com.planification.wf.exceptions;

public class TaskNotFound extends RuntimeException{

    public TaskNotFound(String message) {
        super(message);
    }
    public TaskNotFound() {
        super("La tache recherchée n'existe pas");
    }
}
