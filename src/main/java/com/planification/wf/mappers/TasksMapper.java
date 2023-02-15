package com.planification.wf.mappers;

import com.planification.wf.DTO.TasksDto;
import com.planification.wf.entity.Tasks;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TasksMapper {


    @Mapping(target = "events.id", source = "eventsId")
    Tasks toTasks(TasksDto tasksDto);


    @Mapping(source = "validated", target ="isValidated" )
    @Mapping(source = "events.id", target = "eventsId")
    TasksDto tasksToTasksDto(Tasks tasks);

    List<TasksDto> tasksListToTasksListDto(List<Tasks> tasks);

}
