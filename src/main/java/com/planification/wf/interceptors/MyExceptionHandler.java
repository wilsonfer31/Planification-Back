package com.planification.wf.interceptors;

import com.planification.wf.DTO.ApiErrorDTO;
import com.planification.wf.exceptions.*;
import io.jsonwebtoken.MalformedJwtException;
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
public class MyExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {RuntimeException.class})
    protected ResponseEntity<?> handleConflict(Exception ex, WebRequest request) {

        ApiErrorDTO error = new ApiErrorDTO();
        error.setErrorCode(500);
        error.setMessage(ex.getMessage());
        error.setPath(((ServletWebRequest) request).getRequest().getRequestURI());

        return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);

    }


    @ExceptionHandler(value = {EmailNotFoundException.class})
    protected ResponseEntity<?> emailException(Exception ex, WebRequest request) {

        ApiErrorDTO error = new ApiErrorDTO();
        error.setErrorCode(401);
        error.setMessage(ex.getMessage());
        error.setPath(((ServletWebRequest) request).getRequest().getRequestURI());

        return handleExceptionInternal(ex, error, new HttpHeaders(),
                HttpStatus.UNAUTHORIZED, request);

    }


    @ExceptionHandler(value = {EmailAlreadyExistsException.class})
    protected ResponseEntity<?> emailAlreadyExists(Exception ex, WebRequest request) {

        ApiErrorDTO error = new ApiErrorDTO();
        error.setErrorCode(401);
        error.setMessage(ex.getMessage());
        error.setPath(((ServletWebRequest) request).getRequest().getRequestURI());

        return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);

    }

    @ExceptionHandler(value = {EventAlreadyExists.class})
    protected ResponseEntity<?> eventAlreadyExistsException(Exception ex, WebRequest request) {

        ApiErrorDTO error = new ApiErrorDTO();
        error.setErrorCode(401);
        error.setMessage(ex.getMessage());
        error.setPath(((ServletWebRequest) request).getRequest().getRequestURI());

        return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);

    }


    @ExceptionHandler(value = {EventNotFound.class})
    protected ResponseEntity<?> EventNotFoundException(Exception ex, WebRequest request) {

        ApiErrorDTO error = new ApiErrorDTO();
        error.setErrorCode(401);
        error.setMessage(ex.getMessage());
        error.setPath(((ServletWebRequest) request).getRequest().getRequestURI());

        return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);

    }

    @ExceptionHandler(value = {UserNotFoundException.class})
    protected ResponseEntity<?> UserNotFoundException(Exception ex, WebRequest request) {

        ApiErrorDTO error = new ApiErrorDTO();
        error.setErrorCode(401);
        error.setMessage(ex.getMessage());
        error.setPath(((ServletWebRequest) request).getRequest().getRequestURI());

        return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);

    }

    @ExceptionHandler({AuthenticationException.class})
    public ResponseEntity<ApiErrorDTO> handleAuthenticationException(Exception ex) {

        ApiErrorDTO error = new ApiErrorDTO();
        error.setErrorCode(401);
        error.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

    @ExceptionHandler({MalformedJwtException.class})
    public ResponseEntity<ApiErrorDTO> malFormedException(MalformedJwtException ex) {

        ApiErrorDTO error = new ApiErrorDTO();
        error.setErrorCode(466);
        error.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }


}
