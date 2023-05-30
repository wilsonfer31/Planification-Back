package com.planification.wf.models.DTO;


import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Builder
@Getter
@Setter
public class ApiErrorDTO implements Serializable {
    private int errorCode;
    private String message;
    private String path;

    @Override
    public String toString() {
        return "Error {" +
            "errorCode=" + errorCode +
            ", message='" + message + '\'' +
            ", path='" + path + '\'' +
            '}';
    }
}
