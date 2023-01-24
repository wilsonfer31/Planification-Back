package com.planification.wf.exceptions;

public class EventAlreadyExists extends RuntimeException{

   public EventAlreadyExists(String message){
        super(message);
    }

  public  EventAlreadyExists(){
        super("L'event existe deja en base de données");
    }

}
