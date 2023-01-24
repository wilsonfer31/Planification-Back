package com.planification.wf.mappers;

import com.planification.wf.DTO.EventsDTO;
import com.planification.wf.entity.Events;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


import java.util.List;

@Mapper(componentModel = "spring")
public interface EventMapper {

    @Mapping(target = "userId" , source = "user.id")
    EventsDTO toEventsDto(Events events);

    List<EventsDTO> toListEventsDto(List <Events> eventsList);
}
