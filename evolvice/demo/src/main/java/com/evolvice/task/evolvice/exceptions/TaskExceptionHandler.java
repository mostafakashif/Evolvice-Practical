/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evolvice.task.evolvice.exceptions;

import java.util.Date;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 *
 * @author mostafa.kashif
 */
@ControllerAdvice
public class TaskExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CarServiceException.class)
    public final ResponseEntity<TaskErrorModel> handleServiceException(CarServiceException ex, WebRequest request) {
        TaskErrorModel errorDetails = new TaskErrorModel(new Date(), "General Error!",
                request.getDescription(false), ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException tme, HttpHeaders headers, HttpStatus status, WebRequest request) {
        TaskErrorModel errorDetails = new TaskErrorModel(new Date(), "Error in input parameters types",
                request.getDescription(false), tme.getMessage());
        return new ResponseEntity<>(errorDetails, status);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        TaskErrorModel errorDetails = new TaskErrorModel(new Date(), "Error in request parameter values",
                request.getDescription(false), ex.getMessage());
        return new ResponseEntity<>(errorDetails, status);
    }

}
