package com.planification.wf.repository;

import com.planification.wf.models.entity.Message;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message,Long> {
    @Query("select m from Message m order by m.date DESC ")
    List<Message> findByOrderByDateDesc(Pageable pageable);




}
