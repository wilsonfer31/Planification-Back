package com.planification.wf.service;

import com.planification.wf.models.DTO.TasksDto;
import com.planification.wf.models.entity.Tasks;
import com.planification.wf.exceptions.TaskNotFound;
import com.planification.wf.mappers.TasksMapper;
import com.planification.wf.repository.TaskRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final TasksMapper tasksMapper;

    /**
     * Cette fonction récupère une liste des tâches qui n'ont pas été validées et les filtre en fonction de leur date de
     * début et de l'ID de l'utilisateur actuel avant de les renvoyer sous forme de liste de DTO de tâche.
     *
     * @return Une liste d'objets TasksDto qui correspondent à des tâches qui n'ont pas été validées, dont la date de début
     * est antérieure à la date d'aujourd'hui et qui sont associées à l'utilisateur actuel.
     */
    public List<TasksDto> getAllTasksNotValidated() {

        List<Tasks> tasks = this.taskRepository.getTasksNotValidated().stream()
                .filter((task) -> LocalDate.parse(task.getEvents().getStart()).isBefore(LocalDate.now()))
                .filter((task) -> Objects.equals(task.getEvents().getUser().getId(), AuthenticationGetter.getCurrentUser().getId()))
                .collect(Collectors.toList());
        return tasksMapper.tasksListToTasksListDto(tasks);
    }

    public List<TasksDto> getAllTasksValidated() {
        return tasksMapper.tasksListToTasksListDto(this.taskRepository.getTasksValidated().stream().filter((task) -> Objects.equals(task.getEvents().getUser().getId(), AuthenticationGetter.getCurrentUser().getId()))
                .collect(Collectors.toList()));
    }

    public TasksDto setTaskValidated(long id) {
        Tasks tasks = this.taskRepository.findById(id).orElseThrow(TaskNotFound::new);
        tasks.setValidated(true);
        return tasksMapper.tasksToTasksDto(taskRepository.save(tasks));
    }

    public TasksDto removeTask(long id){
        Tasks task = this.taskRepository.findById(id).orElseThrow(TaskNotFound::new);
        this.taskRepository.delete(task);
        return tasksMapper.tasksToTasksDto(task);
    }
}
