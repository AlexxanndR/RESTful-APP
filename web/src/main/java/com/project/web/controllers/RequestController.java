package com.project.web.controllers;

import com.project.web.logger.MyLogger;
import com.project.web.services.RequestCounter;
import org.apache.logging.log4j.Level;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/request")
public class RequestController {
    @GetMapping(value = "/counter")
    public ResponseEntity<String> getCounter() {
        MyLogger.log(Level.INFO, "Getting request counter...");
        return new ResponseEntity<>("Requests number: " + RequestCounter.getCounter(), HttpStatus.OK);
    }
}
