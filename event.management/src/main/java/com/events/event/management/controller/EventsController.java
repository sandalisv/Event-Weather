/*
 * Copyright (c) 10/10/22, 2:21 AM Created by Sandali Vithanage.
 */

package com.events.event.management.controller;

import com.events.event.management.entity.Event;
import com.events.event.management.exception.CustomApiException;
import com.events.event.management.exception.CustomDateException;
import com.events.event.management.exception.CustomDbException;
import com.events.event.management.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The type Events controller.
 */
@Controller
@RequestMapping("eventManagement")
public class EventsController {

    @Autowired
    private EventService service;

    /**
     * Gets events.
     *
     * @return the events
     * @throws CustomDbException the custom db exception
     */
    @GetMapping("events")
    public ResponseEntity<List<Event>> getEvents() throws CustomDbException {
        List<Event> events = service.getEvents();
        return new ResponseEntity<List<Event>>(events, HttpStatus.OK);
    }

    /**
     * Gets events.
     *
     * @param id the event id
     * @return the event
     * @throws CustomDbException the custom db exception
     */
    @GetMapping("events/{id}")
    public ResponseEntity<Event> getEvents(@PathVariable("id") int id) throws CustomDbException {
        Event event = service.getEvent(id);
        return new ResponseEntity<Event>(event, HttpStatus.OK);
    }

    /**
     * Create book response entity.
     *
     * @param event the event
     * @return the response entity
     * @throws CustomApiException  the custom api exception
     * @throws CustomDbException   the custom db exception
     * @throws CustomDateException the custom date exception
     */
    @PostMapping("events")
    public ResponseEntity<Event> createEvent(@RequestBody Event event) throws CustomApiException, CustomDbException, CustomDateException {
        Event e = service.createEvent(event);
        return new ResponseEntity<Event>(e, HttpStatus.OK);

    }

    /**
     * Delete event response entity.
     *
     * @param id the event id
     * @return the response entity
     * @throws CustomDbException the custom db exception
     */
    @DeleteMapping("events/{id}")
    public ResponseEntity<String> deleteEvent(@PathVariable("id") int id) throws CustomDbException {
        boolean isDeleted = service.deleteEvent(id);
        if (isDeleted) {
            String response = "Event has been deleted successfully";
            return new ResponseEntity<String>(response, HttpStatus.OK);
        } else {
            String error = "Error while deleting event from database";
            return new ResponseEntity<String>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
