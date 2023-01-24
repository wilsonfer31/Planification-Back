package com.planification.wf.service;


import com.planification.wf.DTO.EventsDTO;
import com.planification.wf.entity.Events;
import com.planification.wf.entity.User;
import com.planification.wf.exceptions.EventAlreadyExists;
import com.planification.wf.exceptions.EventNotFound;
import com.planification.wf.exceptions.UserNotFoundException;
import com.planification.wf.mappers.EventMapper;
import com.planification.wf.repository.EventRepository;
import com.planification.wf.repository.UserRepository;
import jakarta.transaction.Transactional;

import jdk.jfr.Event;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class EventService {

    private final EventRepository repository;
    private final EventMapper eventMapper;
    private final UserRepository userRepository;


    public List<EventsDTO> getEvents(){
        List<Events> events = repository.getEventsByUserId(Authentication.getCurrentUser().getId());
        return eventMapper.toListEventsDto(events);
    }
    public EventsDTO updateEvent(EventsDTO eventdto){
        Optional<Events> eventInDataBase = repository.findById(eventdto.getId());

        if(eventInDataBase.isPresent()){
            Optional<User> user = userRepository.findById(eventdto.getUserId());

            if(user.isEmpty()) {
                throw new UserNotFoundException();
            }
            var eventValue = Events.builder().
                    id(eventdto.getId()).
                    end(eventdto.getEnd()).
                    start(eventdto.getStart()).
                    title(eventdto.getTitle()).
                    date(eventdto.getDate()).
                    allDay(eventdto.isAllDay()).
                    url(eventdto.getUrl()).
                    user(user.get()).build();
            return eventMapper.toEventsDto(repository.save(eventValue));

        }else{
            throw new EventNotFound();
        }

    }
    public EventsDTO saveEvent(EventsDTO eventdto){


       var eventValue = Events.builder().
                end(eventdto.getEnd()).
                start(eventdto.getStart()).
                title(eventdto.getTitle()).
                date(eventdto.getDate()).
                allDay(eventdto.isAllDay()).
                url(eventdto.getUrl()).
                user(Authentication.getCurrentUser()).build();

        return eventMapper.toEventsDto(repository.save(eventValue));
    }



    public Events deleteEvent(long id){

        Events event = repository.findById(id).orElseThrow(EventNotFound::new);
         repository.delete(event);
         return event;
    }






}
