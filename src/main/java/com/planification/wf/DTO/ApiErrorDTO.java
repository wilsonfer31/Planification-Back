package com.planification.wf.DTO;


import lombok.Data;

import java.io.Serializable;

@Data
public class ApiErrorDTO implements Serializable {
    private int errorCode;
    private String message;
    private String path;
}
