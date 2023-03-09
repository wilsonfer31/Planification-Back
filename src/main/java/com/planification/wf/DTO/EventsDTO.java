package com.planification.wf.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

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
