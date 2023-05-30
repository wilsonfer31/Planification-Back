package com.planification.wf.service;

import com.planification.wf.models.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


public class AuthenticationGetter {
    /**
     * Cette fonction renvoie l'utilisateur actuellement authentifié.
     *
     * @return La méthode renvoie l'utilisateur actuellement authentifié en tant qu'objet User.
     */
    public static User getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();

    }
}
