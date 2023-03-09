package com.planification.wf.controlleur;


import com.planification.wf.DTO.TasksDto;
import com.planification.wf.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @PostMapping("validate/{id}")
    public ResponseEntity<TasksDto> setTaskValidated(@PathVariable Long id){
            return ResponseEntity.ok(service.setTaskValidated(id));
    }
}
