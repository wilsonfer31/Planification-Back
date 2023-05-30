package com.planification.wf.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data @Entity @Builder @NoArgsConstructor @AllArgsConstructor
public class Events {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String start;
    private  String end;
    private boolean allDay;
    private String url;
    @ManyToOne
    private User user;


    @OneToMany(mappedBy ="events", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tasks> tasksList;

    public void addTask(Tasks task) {
        this.tasksList.add(task);
        task.setEvents(this);
    }

    public void removeTask(Tasks task) {
        this.tasksList.remove(task);
        task.setEvents(null);
    }

}
