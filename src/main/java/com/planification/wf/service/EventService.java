package com.planification.wf.service;


import com.planification.wf.exceptions.EventNotFound;
import com.planification.wf.mappers.EventMapper;
import com.planification.wf.mappers.TasksMapper;
import com.planification.wf.models.DTO.EventsAndTasksDTO;
import com.planification.wf.models.DTO.EventsDTO;
import com.planification.wf.models.DTO.TasksDto;
import com.planification.wf.models.entity.Events;
import com.planification.wf.models.entity.Tasks;
import com.planification.wf.models.enums.DiscordTypeMessage;
import com.planification.wf.repository.EventRepository;
import com.planification.wf.webhook.DiscordWebhookSender;
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
    private final DiscordWebhookSender webhookSender;

    public EventsAndTasksDTO getEventsById(Long id) {
        return eventMapper.toEventsAndTasksDto(repository.findById(id).orElseThrow(EventNotFound::new));

    }
    public List<EventsDTO> getEventsByActualUser() {
        List<Events> events = repository.getEventsByUserId(AuthenticationGetter.getCurrentUser().getId());
        return eventMapper.toListEventsDto(events);
    }
    public EventsAndTasksDTO updateEvent(EventsAndTasksDTO eventDto)  {
        Optional<Events> eventInDataBase = repository.findById(eventDto.getId());
        if (eventInDataBase.isPresent()) {
            return getEventsAndTasksDTO(eventDto);
        } else {
            throw new EventNotFound();
        }
    }
    public EventsAndTasksDTO saveEvent(EventsAndTasksDTO eventDto) throws Exception {

        if (eventDto.getTasks() != null) {
            return getEventsAndTasksDTO(eventDto);
        } else {
            var eventValue = eventMapper.toEvents(eventDto);
            eventValue.setUser(AuthenticationGetter.getCurrentUser());
            webhookSender.sendWebhook("Event as been created by : " + AuthenticationGetter.getCurrentUser().getEmail() + " Event :" + eventDto.getTitle(), DiscordTypeMessage.INFO);

            return eventMapper.toEventsAndTasksDto(repository.save(eventValue));
        }
    }
    public Events deleteEvent(long id) {
        Events event = repository.findById(id).orElseThrow(EventNotFound::new);
        repository.delete(event);
        return event;
    }
    private EventsAndTasksDTO getEventsAndTasksDTO(EventsAndTasksDTO eventDto)  {
        var eventValue = eventMapper.toEvents(eventDto);
        eventValue.setUser(AuthenticationGetter.getCurrentUser());
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
