/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evolvice.task.evolvice.exceptions;

import java.util.Date;

/**
 *
 * @author mostafa.kashif
 */
public class TaskErrorModel {

    private Date timestamp;
    private String message;
    private String details;
    private String detailedErrorMessage;

    public String getDetailsErrorMessage() {
        return detailedErrorMessage;
    }

    public void setDetailsErrorMessage(String detailsErrorMessage) {
        this.detailedErrorMessage = detailsErrorMessage;
    }

    public TaskErrorModel(Date timestamp, String message, String details, String detailsErrorMessage) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
        this.detailedErrorMessage = detailsErrorMessage;
    }

    public TaskErrorModel(Date timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
    
    
}
