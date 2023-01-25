package com.planification.wf.DTO;

import com.planification.wf.entity.User;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventsDTO implements Serializable {
    private Long id;
    private String title;
    private String start;
    private  String end;
    private boolean allDay;
    private String url;

}
