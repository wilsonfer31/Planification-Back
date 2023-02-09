package com.planification.wf.DTO;

import lombok.Data;

import java.io.Serializable;

@Data
public class TasksDto implements Serializable {
    private final Long id;
    private final String task;
    private final Long eventsId;
    private final boolean isValidated;
}
