package com.planification.wf.service;


import com.planification.wf.DTO.EventsAndTasksDTO;
import com.planification.wf.DTO.EventsDTO;
import com.planification.wf.DTO.TasksDto;
import com.planification.wf.entity.Events;
import com.planification.wf.entity.Tasks;
import com.planification.wf.exceptions.EventNotFound;
import com.planification.wf.mappers.EventMapper;
import com.planification.wf.mappers.TasksMapper;
import com.planification.wf.repository.EventRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class EventService {

    private final EventRepository repository;
    private final EventMapper eventMapper;
    private final TasksMapper tasksMapper;


    public EventsAndTasksDTO getEventsById(Long id){
        return eventMapper.toEventsAndTasksDto(repository.findById(id).orElseThrow(EventNotFound::new));

    }

    public List<EventsDTO> getEventsByActualUser(){
        List<Events> events = repository.getEventsByUserId(Authentication.getCurrentUser().getId());
        return eventMapper.toListEventsDto(events);
    }
    public EventsAndTasksDTO updateEvent(EventsAndTasksDTO eventDto){
        Optional<Events> eventInDataBase = repository.findById(eventDto.getId());
        if(eventInDataBase.isPresent()){
            return getEventsAndTasksDTO(eventDto);
        }else{
            throw new EventNotFound();
        }
    }
    public EventsAndTasksDTO saveEvent(EventsAndTasksDTO eventDto){

        if(eventDto.getTasks() != null) {
            return getEventsAndTasksDTO(eventDto);
        }else{
            var eventValue = eventMapper.toEvents(eventDto);
            eventValue.setUser(Authentication.getCurrentUser());
            return eventMapper.toEventsAndTasksDto(repository.save(eventValue));
        }
    }

    public Events deleteEvent(long id){
        Events event = repository.findById(id).orElseThrow(EventNotFound::new);
         repository.delete(event);
         return event;
    }

    private EventsAndTasksDTO getEventsAndTasksDTO(EventsAndTasksDTO eventDto) {
        var eventValue = eventMapper.toEvents(eventDto);
        eventValue.setUser(Authentication.getCurrentUser());
        eventValue.setTasksList(new ArrayList<>());

        for (TasksDto taskDto : eventDto.getTasks()) {

            Tasks task = tasksMapper.toTasks(taskDto);
            task.setEvents(eventValue);
            task.setCreated(eventValue.getStart());
            task.setEventName(eventValue.getTitle());
            eventValue.addTask(task);
        }

        return eventMapper.toEventsAndTasksDto(repository.save(eventValue));
    }






}
