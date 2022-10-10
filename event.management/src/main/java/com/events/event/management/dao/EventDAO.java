/*
 * Copyright (c) 10/10/22, 2:29 AM Created by Sandali Vithanage.
 */

package com.events.event.management.dao;

import com.events.event.management.entity.Event;
import com.events.event.management.exception.CustomDbException;

import java.util.List;

/**
 * The interface Event dao.
 */
public interface EventDAO {

    /**
     * Gets events.
     *
     * @return the events
     * @throws CustomDbException the custom db exception
     */

    List<Event> getEvents() throws CustomDbException;

    /**
     * Gets event by id.
     *
     * @param eventId the event id
     * @return the event
     * @throws CustomDbException the custom db exception
     */

    Event getEvent(int eventId) throws CustomDbException;

    /**
     * Create event.
     *
     * @param event the event
     * @return the event
     * @throws CustomDbException the custom db exception
     */

    Event createEvent(Event event) throws CustomDbException;

    /**
     * Delete event.
     *
     * @param id the event id
     * @return true if event is deleted, return false if event is not deleted
     * @throws CustomDbException the custom db exception
     */
    boolean deleteEvent(int id) throws CustomDbException;
}
