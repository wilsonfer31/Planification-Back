package com.planification.wf.repository;

import com.planification.wf.entity.Events;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Events, Long> {

    @Query("FROM Events e WHERE e.user.id =:id ")
    List<Events> getEventsByUserId(@Param("id") long id);


}
