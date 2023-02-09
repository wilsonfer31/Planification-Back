package com.planification.wf.service;

import com.planification.wf.DTO.TasksDto;
import com.planification.wf.entity.Tasks;
import com.planification.wf.mappers.TasksMapper;
import com.planification.wf.repository.TaskRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final TasksMapper tasksMapper;

    public List<TasksDto> getAllTasksNotValidated(){
        List<Tasks> tasks = this.taskRepository.getTasksNotValidated().stream().filter((task) -> LocalDate.parse(task.getEvents().getEnd()).isBefore(LocalDate.now())).collect(Collectors.toList());
        return tasksMapper.tasksListToTasksListDto(tasks);
    }

    public List<TasksDto> getAllTasksValidated(){
        return tasksMapper.tasksListToTasksListDto(this.taskRepository.getTasksValidated());
    }
}
