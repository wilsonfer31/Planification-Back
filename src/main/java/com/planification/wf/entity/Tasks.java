package com.planification.wf.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;


@Data @Builder @Entity @NoArgsConstructor @AllArgsConstructor
public class Tasks {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String task;
    @ManyToOne(optional = false)
    @JoinColumn(name="events_id")
    private Events events;
    private boolean isValidated = false;
    private String created;
    private String eventName;

    public void setEvents(Events events) {
        this.events = events;
    }




}
