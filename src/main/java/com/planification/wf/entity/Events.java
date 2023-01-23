package com.planification.wf.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Events {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private LocalDate start;
    private  LocalDate end;
    private LocalDate date;
    private boolean allDay;
    private String url;
    @ManyToOne
    private User user;
}
