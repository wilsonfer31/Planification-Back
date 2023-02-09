package com.planification.wf.mappers;

import com.planification.wf.DTO.EventsAndTasksDTO;
import com.planification.wf.DTO.EventsDTO;
import com.planification.wf.entity.Events;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


import java.util.List;


@Mapper(componentModel = "spring", uses = {TasksMapper.class})
public interface EventMapper {

    @Mapping(source = "tasks", target = "tasksList")
    Events toEvents(EventsAndTasksDTO eventsDto);

    @Mapping(ignore = true, target = "tasksList")
    Events toEvents(EventsDTO eventsDTO);



    @Mapping(source = "tasksList", target = "tasks")
    EventsAndTasksDTO toEventsAndTasksDto(Events events);


    EventsDTO toEventsDto(Events events);

   List<EventsAndTasksDTO> toListEventsAndTasksDto(List <Events> eventsList);

    List<EventsDTO> toListEventsDto(List <Events> eventsList);




}
