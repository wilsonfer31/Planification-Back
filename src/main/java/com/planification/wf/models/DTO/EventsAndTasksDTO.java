package com.planification.wf.models.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventsAndTasksDTO implements Serializable {
    private Long id;
    private String title;
    private String start;
    private  String end;
    private boolean allDay;
    private String url;
    private List<TasksDto> tasks;

}
