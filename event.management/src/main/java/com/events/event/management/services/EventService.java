/*
 * Copyright (c) 10/10/22, 2:39 AM Created by Sandali Vithanage.
 */

package com.events.event.management.services;

import com.events.event.management.entity.Event;
import com.events.event.management.exception.CustomApiException;
import com.events.event.management.exception.CustomDateException;
import com.events.event.management.exception.CustomDbException;

import java.util.List;

/**
 * The interface Event service.
 */
public interface EventService {
    /**
     * Gets events.
     *
     * @return the events
     * @throws CustomDbException the custom db exception
     */
    List<Event> getEvents() throws CustomDbException;

    /**
     * Gets event.
     *
     * @param id the id
     * @return the event
     * @throws CustomDbException the custom db exception
     */
    Event getEvent(int id) throws CustomDbException;

    /**
     * Create event event.
     *
     * @param event the event
     * @return the event
     * @throws CustomApiException  the custom api exception
     * @throws CustomDbException   the custom db exception
     * @throws CustomDateException the custom date exception
     */
    Event createEvent(Event event) throws CustomApiException, CustomDbException, CustomDateException;

    /**
     * Delete event boolean.
     *
     * @param id the id
     * @return the boolean
     * @throws CustomDbException the custom db exception
     */
    boolean deleteEvent(int id) throws CustomDbException;

}
