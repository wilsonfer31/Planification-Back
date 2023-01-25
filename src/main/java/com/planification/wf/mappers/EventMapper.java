package com.planification.wf.mappers;

import com.planification.wf.DTO.EventsDTO;
import com.planification.wf.entity.Events;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


import java.util.List;

@Mapper(componentModel = "spring")
public interface EventMapper {


/*
    @Mapping(target = "start", dateFormat = "yyyy-MM-dd'T'HH:mm:ss")
    @Mapping(target = "end", dateFormat = "yyyy-MM-dd'T'HH:mm:ss")
    */

    EventsDTO toEventsDto(Events events);

    List<EventsDTO> toListEventsDto(List <Events> eventsList);
}
