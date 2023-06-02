package com.planification.wf.interceptors;

import com.planification.wf.models.DTO.ApiErrorDTO;
import com.planification.wf.models.enums.DiscordTypeMessage;
import com.planification.wf.exceptions.*;
import com.planification.wf.webhook.DiscordWebhookSender;
import io.jsonwebtoken.MalformedJwtException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
@AllArgsConstructor
public class MyExceptionHandler extends ResponseEntityExceptionHandler {


    private final DiscordWebhookSender webhookSender;


    @ExceptionHandler(value = {RuntimeException.class})
    protected ResponseEntity<?> handleConflict(Exception ex, WebRequest request) throws Exception {
        return handleExceptionInternal(ex,  getApiErrorDTO(ex, (ServletWebRequest) request , HttpStatus.INTERNAL_SERVER_ERROR.value()), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);

    }



    @ExceptionHandler(value = {EmailNotFoundException.class})
    protected ResponseEntity<?> emailException(Exception ex, WebRequest request) throws Exception {
        return handleExceptionInternal(ex,  getApiErrorDTO(ex, (ServletWebRequest) request , HttpStatus.FORBIDDEN.value()), new HttpHeaders(), HttpStatus.FORBIDDEN, request);

    }


    @ExceptionHandler(value = {EmailAlreadyExistsException.class})
    protected ResponseEntity<?> emailAlreadyExists(Exception ex, WebRequest request) throws Exception {
        return handleExceptionInternal(ex,  getApiErrorDTO(ex, (ServletWebRequest) request , HttpStatus.FORBIDDEN.value()), new HttpHeaders(), HttpStatus.FORBIDDEN, request);

    }

    @ExceptionHandler(value = {EventAlreadyExists.class})
    protected ResponseEntity<?> eventAlreadyExistsException(Exception ex, WebRequest request) throws Exception {
        return handleExceptionInternal(ex,  getApiErrorDTO(ex, (ServletWebRequest) request , HttpStatus.FORBIDDEN.value()), new HttpHeaders(), HttpStatus.FORBIDDEN, request);


    }

    @ExceptionHandler(value = {EventNotFound.class})
    protected ResponseEntity<?> EventNotFoundException(Exception ex, WebRequest request) throws Exception {
        return handleExceptionInternal(ex,  getApiErrorDTO(ex, (ServletWebRequest) request , HttpStatus.FORBIDDEN.value()), new HttpHeaders(), HttpStatus.FORBIDDEN, request);

    }

    @ExceptionHandler(value = {UserNotFoundException.class})
    protected ResponseEntity<?> UserNotFoundException(Exception ex, WebRequest request) throws Exception {
        return handleExceptionInternal(ex,  getApiErrorDTO(ex, (ServletWebRequest) request , HttpStatus.FORBIDDEN.value()), new HttpHeaders(), HttpStatus.FORBIDDEN, request);

    }

    @ExceptionHandler({AuthenticationException.class})
    public ResponseEntity<?> handleAuthenticationException(Exception ex ,  WebRequest request) throws Exception {
        return handleExceptionInternal(ex,  getApiErrorDTO(ex, (ServletWebRequest) request , HttpStatus.FORBIDDEN.value()), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);

    }

    @ExceptionHandler({MalformedJwtException.class})
    public ResponseEntity<?> malFormedException(MalformedJwtException ex ,  WebRequest request) throws Exception {
        return handleExceptionInternal(ex,  getApiErrorDTO(ex, (ServletWebRequest) request , HttpStatus.INTERNAL_SERVER_ERROR.value()), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }


    private ApiErrorDTO getApiErrorDTO(Exception ex, ServletWebRequest request , int errorCode) throws Exception {
        var error = ApiErrorDTO.builder()
            .errorCode(errorCode)
            .message(ex.getMessage())
            .path(request.getRequest().getRequestURI())
            .build();
        if(errorCode != 403){
            webhookSender.sendWebhook(error.toString(), DiscordTypeMessage.ERROR);
        }
        return error;

    }

}
