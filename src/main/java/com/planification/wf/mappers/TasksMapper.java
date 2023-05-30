package com.planification.wf.mappers;

import com.planification.wf.models.DTO.TasksDto;
import com.planification.wf.models.entity.Tasks;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TasksMapper {


    @Mapping(target = "events.id", source = "eventsId")
    Tasks toTasks(TasksDto tasksDto);


    @Mapping(source = "events.id", target = "eventsId")
    TasksDto tasksToTasksDto(Tasks tasks);

    List<TasksDto> tasksListToTasksListDto(List<Tasks> tasks);

}
