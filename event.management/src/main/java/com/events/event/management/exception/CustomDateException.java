/*
 * Copyright (c) 10/10/22, 2:37 AM Created by Sandali Vithanage.
 */

package com.events.event.management.exception;

/**
 * The type Custom date exception.
 */
public class CustomDateException extends Exception {
    private static final String INVALID_DATE = "Entered event date is not valid. Please enter a date within 5 days from today";

    /**
     * Instantiates a new Custom date exception.
     *
     * @param message the message
     */
    public CustomDateException(String message) {
        super(INVALID_DATE +" - "+ message);
    }
}
