package com.planification.wf.repository;

import com.planification.wf.models.entity.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Tasks, Long> {

    @Query("FROM Tasks t JOIN FETCH t.events WHERE t.validated = false")
    List<Tasks> getTasksNotValidated();


    @Query("FROM Tasks t JOIN FETCH t.events WHERE t.validated = true")
    List<Tasks> getTasksValidated();
}
