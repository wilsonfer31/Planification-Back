package com.planification.wf.exceptions;

import com.planification.wf.interceptors.MyExceptionHandler;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;




/**
 * Il s'agit d'une classe Java qui implémente l'interface AuthenticationEntryPoint et envoie une réponse d'erreur 403
 * lorsque l'authentification est refusée.
 */
@Component
public class JWTAuthenticationEntryPoint implements AuthenticationEntryPoint {
    public JWTAuthenticationEntryPoint( @Qualifier("handlerExceptionResolver") HandlerExceptionResolver resolver, MyExceptionHandler handler) {
        this.resolver = resolver;
    }
    private final HandlerExceptionResolver resolver;
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.sendError(403, "Authentification  denied");

    }



}
