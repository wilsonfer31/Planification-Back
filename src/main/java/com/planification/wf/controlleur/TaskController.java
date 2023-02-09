package com.planification.wf.controlleur;


import com.planification.wf.DTO.EventsDTO;
import com.planification.wf.DTO.TasksDto;
import com.planification.wf.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService service;


    @GetMapping("/notValidated")
    public ResponseEntity<List<TasksDto>> getAllTasksNotValidated(){
        return ResponseEntity.ok(service.getAllTasksNotValidated());
    }

    @GetMapping("/validated")
    public ResponseEntity<List<TasksDto>> getAllTasksValidated(){
        return ResponseEntity.ok(service.getAllTasksValidated());
    }
}