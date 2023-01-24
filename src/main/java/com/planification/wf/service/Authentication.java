package com.planification.wf.service;

import com.planification.wf.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;


public class Authentication {
    public static User getCurrentUser(){
        org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();

    }
}
