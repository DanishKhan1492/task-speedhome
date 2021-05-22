package com.speedhome.propertymanagement.controllers;

import com.speedhome.propertymanagement.dtos.ErrorResponseDto;
import com.speedhome.propertymanagement.utils.exceptions.NoPropertyFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Muhammad Danish Khan
 * @created 21/5/21 - 12:29 PM
 */
@ControllerAdvice
public class ExceptionControllerAdvise {
    @ExceptionHandler(value= { BadCredentialsException.class})
    protected ResponseEntity<ErrorResponseDto> handleBadCredentialsException(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Username or Password is incorrect";
        return new ResponseEntity<>(new ErrorResponseDto(bodyOfResponse, HttpStatus.FORBIDDEN.getReasonPhrase()), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value= { NoPropertyFoundException.class})
    protected ResponseEntity<ErrorResponseDto> handleNoRecordFoundException(Exception ex, WebRequest request) {
        return new ResponseEntity<>(new ErrorResponseDto(ex.getMessage(), HttpStatus.NO_CONTENT.getReasonPhrase()), HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleValidationException(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        String message = fieldErrors.stream().map(fieldError -> fieldError.getDefaultMessage()).collect(Collectors.joining(", "));
        return new ResponseEntity<>(new ErrorResponseDto(HttpStatus.BAD_REQUEST.getReasonPhrase(), message), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value= { Exception.class})
    protected ResponseEntity<ErrorResponseDto> genericExceptionHandler(Exception ex, WebRequest request) {
        return new ResponseEntity<>(new ErrorResponseDto(ex.getMessage(), HttpStatus.BAD_REQUEST.getReasonPhrase()), HttpStatus.BAD_REQUEST);
    }
}
