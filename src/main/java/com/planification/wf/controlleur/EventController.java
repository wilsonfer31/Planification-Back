package com.planification.wf.controlleur;

import com.planification.wf.DTO.EventsDTO;
import com.planification.wf.entity.Events;

import com.planification.wf.service.EventService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService service;

    @GetMapping()
    public ResponseEntity<List<EventsDTO>> getAllEvents(){

        return ResponseEntity.ok(service.getEvents());
    }

    @PostMapping()
    public ResponseEntity<EventsDTO> save(@RequestBody EventsDTO eventDto){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.saveEvent(eventDto));
    }


    @PutMapping()
    public ResponseEntity<EventsDTO> update(@RequestBody EventsDTO eventDto){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.updateEvent(eventDto));
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<Events> delete(@PathVariable(name ="id")long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.deleteEvent(id));

    }



}
