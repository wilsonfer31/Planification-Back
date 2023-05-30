package com.planification.wf.models.DTO;

import lombok.Data;

import java.io.Serializable;

@Data
public class TasksDto implements Serializable {
    private final Long id;
    private final String task;
    private final Long eventsId;
    private final boolean validated;
    private final String created;
    private final String eventName;
}
