package com.io.buildings.controllers;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *  Maps exceptions: returns HTTP 404 status and logs
 */
@ControllerAdvice
public class ExceptionMapper {

    /**
     * Logs exception
     * @param e Cached ResourceNotFoundException
     */
    @ResponseBody
    @ExceptionHandler({ResourceNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleResourceNotFoundException(ResourceNotFoundException e) {
        Logger logger = LoggerFactory.getLogger("resourceNotFound");
        logger.error(e.getMessage());
    }
}
